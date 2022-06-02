package com.object.utils;

import com.object.module.lq.sys.entity.TPurview;
import com.object.module.lq.sys.entity.TRoutingEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限提前工具类
 */
public class PurviewUtile {

    /**
     * @param purview 权限写法：1:add,delete_2:add,delete,update_3:add,update
     * @return
     */
    public static Map<Integer, Map<String,String>> extractPurview(String purview) {
        Map<Integer, Map<String,String>> map = new HashMap();
        if (purview.contains("all")) {
            Map<String,String> list=new HashMap<>();
            list.put("all","all");
            map.put(1,list);
            return map;
        }
        String[] strings = purview.split("_");

        for (String string : strings) {
            int i = Integer.parseInt(string.substring(0, string.lastIndexOf(":")));
            String substring = string.substring(string.indexOf(":") + 1);
            String[] split = substring.split(",");
            Map<String,String> list;
            if(map.get(i)!=null){
                 list=map.get(i);
            }else{
                list = new HashMap<>();
            }
            for (String s : split) {
                list.put(s,s);
            }
            map.put(i, list);
        }
        return map;
    }


    public static List<TPurview> orderPurview(List<TRoutingEntity> list) {
        List<TPurview> purviewList = new ArrayList<>();
        list.forEach(rout -> {
            TPurview purview = new TPurview();
            purview.setName(rout.getMenuName());
            purview.setId(rout.getId()+"");
            purview.setList(orderCha());
           purviewList.add(purview);
        });
        return purviewList;
    }

    private static List<TPurview> orderCha() {
        List<TPurview> purviewList = new ArrayList<>();
        purviewList.add(new TPurview("delete", "删除","delete"));
        purviewList.add(new TPurview("add", "新增","add"));
        purviewList.add(new TPurview("update", "修改","update"));
        return purviewList;
    }
}
