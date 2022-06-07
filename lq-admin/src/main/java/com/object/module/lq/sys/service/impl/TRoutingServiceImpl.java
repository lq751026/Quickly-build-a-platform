package com.object.module.lq.sys.service.impl;

import com.object.module.lq.sys.entity.*;
import com.object.module.lq.sys.service.TRoutingService;
import com.object.module.lq.sys.service.TUserService;
import com.object.module.lq.sys.service.TRoleService;
import com.object.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.object.dao.sys.TRoutingDao;


@Service("TRoutingServiceImpl")
public class TRoutingServiceImpl extends ServiceImpl<TRoutingDao, TRoutingEntity> implements TRoutingService {

     @Autowired
     private TUserService userService;
     @Autowired
     private TRoleService roleService;
     @Autowired
     private TRoutingService routingService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TRoutingEntity> page = this.page(
                new Query<TRoutingEntity>().getPage(params),
                new QueryWrapper<TRoutingEntity>()
        );

        return new PageUtils(page);
    }

    /**
     *   根据当前的用户id去查询当前的授权识别  根据我们编写的工具类去解析当前我们权限
     * @param userID
     * @return
     */
      private List<TRoutingEntity> getByIdUser(Integer userID){
          TUserEntity userEntity = userService.getById(userID);
          TRoleEntity roleEntity = roleService.getById(userEntity.getUrReId());
          List<TRoutingEntity> list = routingService.list();
          String authority = roleEntity.getAuthority();
          List<TRoutingEntity> routingEntities = NubersUtile.nuberRouting(authority, list);
          return routingEntities;
      }


    /**
     * 数结构的封装
     * @return
     */
    @Override
    public Q listOrderBy(Integer userid) {
        List<TRoutingEntity> list;
       if(userid!=null){
           list=getByIdUser(userid);
        }else{
            list = list();
        }
        //找出一级分类
        List<TRoutingEntity> collect = list.stream().filter((SortEntity -> {
            return SortEntity.getParentId() == 0;
        })).map(menu->{
            menu.setChildren(getChider(menu,list));
            return menu;
        }).collect(Collectors.toList());

        List<TRouterEntity> routers=new ArrayList<>();
        for (TRoutingEntity tRoutingEntity : list) {
            String pathName = tRoutingEntity.getPath().replaceAll("/", "");
            TRouterEntity routerEntity = new TRouterEntity(tRoutingEntity.getId(),tRoutingEntity.getPath(),pathName);

            routerEntity.setMeta(new TMetaEntity(tRoutingEntity.getMenuName(),true,tRoutingEntity.getIcon(),2));
            List<TRouterEntity> routerEntities=new ArrayList<>();
            for (TRoutingEntity child : tRoutingEntity.getChildren()) {
                String pathName1 = child.getPath().replaceAll("/", "");
                TRouterEntity routerEntity1 = new TRouterEntity(child.getId(),pathName+pathName1,pathName+pathName1);
                routerEntity1.setMeta(new TMetaEntity(child.getId(),child.getMenuName(),true,child.getIcon(),2,"['*']"));
                routerEntities.add(routerEntity1);
            }

            routerEntity.setChildren(routerEntities);
            routers.add(routerEntity);
        }

        return Q.ok().put("data",routers);
    }

    /**
     * 当前路由的权限查询
     * @param userId
     * @param routId
     * @return
     */
    @Override
    public Map<String, String> findUserIByroutId(Integer userId, Integer routId) {
        Map<String,String> map1=new HashMap<>();
        TUserEntity userEntity = userService.getById(userId);
        TRoleEntity roleEntity = roleService.getById(userEntity.getUrReId());
        String purview = roleEntity.getPurview();
        Map<Integer, Map<String,String>> map = PurviewUtile.extractPurview(purview);
        if(map.get(1)!=null&&map.get(1).get("all").equals("all")){
            map1.put("add","add");map1.put("delete","delete");map1.put("update","update");
        }else{
            map1=map.get(routId);
        }
        return map1;
    }

    public List<TRoutingEntity> getChider(TRoutingEntity menu, List<TRoutingEntity> list){
        List<TRoutingEntity> data=list.stream().filter(rout->{
            return rout.getParentId()==menu.getId();
        }).map(rout->{
            //找到子菜单
            rout.setChildren(getChider(rout,list));
            return rout;
        }).collect(Collectors.toList());
        return data;
    }



}
