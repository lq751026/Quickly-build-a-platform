package com.object.module.lq.sys.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.object.dao.sys.TUserDao;
import com.object.module.lq.chatroom.entity.TAddRecordEntity;
import com.object.module.lq.chatroom.entity.TGoodFriendEntity;
import com.object.module.lq.chatroom.service.TAddRecordService;
import com.object.module.lq.chatroom.service.TGoodFriendService;
import com.object.module.lq.sys.entity.TUserEntity;
import com.object.module.lq.sys.ov.TGoodFriendOv;
import com.object.module.lq.sys.ov.TUserAndAddUser;
import com.object.module.lq.sys.service.TOnlineListingService;
import com.object.module.lq.sys.service.TRoleService;
import com.object.module.lq.sys.service.TUserService;
import com.object.statuscode.TUserStatusCode;
import com.object.utils.PageUtils;
import com.object.utils.PasswordEncryp;
import com.object.utils.Q;
import com.object.utils.Query;
import lombok.val;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("TUserServiceImpl")
public class TUserServiceImpl extends ServiceImpl<TUserDao, TUserEntity> implements TUserService {

    @Autowired
    private TRoleService roleServicel;
    @Autowired
    private TAddRecordService addRecordService;
    @Autowired
    private TUserDao usserdao;
    @Autowired
    private TGoodFriendService goodFriendService;

    @Autowired
    private TOnlineListingService onlineListingService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<TUserEntity> wrapper = new QueryWrapper<>();
        String id = (String) params.get("id");
        String name = (String) params.get("name");
        String createTime = (String) params.get("createTime");
        String endTiem = (String) params.get("endTiem");
        String status = (String) params.get("status");
        if (StringUtils.isNotEmpty(id)) wrapper.eq("ur_id", id);
        if (StringUtils.isNotEmpty(name)) wrapper.likeRight("ur_username", name);
        if (StringUtils.isNotEmpty(createTime)) wrapper.ge("create_time", createTime);
        if (StringUtils.isNotEmpty(endTiem)) wrapper.le("create_time", endTiem);
        if (StringUtils.isNotEmpty(status)) wrapper.eq("ur_stuats", status);
        IPage<TUserEntity> page = this.page(new Query<TUserEntity>().getPage(params), wrapper);
        for (TUserEntity user : page.getRecords()) {
            user.setRole(roleServicel.getById(user.getUrReId()));
        }

        return new PageUtils(page);
    }


    /**
     * ??????
     *
     * @param user
     * @return
     */
    @Override
    public TUserEntity findLogin(TUserEntity user) {
        QueryWrapper<TUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("ur_username", user.getUrUsername());
        TUserEntity one = getOne(wrapper);
        if (PasswordEncryp.IfPassowrd(user.getUrPassword(), one.getUrPassword())) {
            //????????????
            return one;
        }
        return null;
    }

    /**
     * ????????????
     *
     * @param oldpassword
     * @param newpassword
     * @param userId
     * @return
     */
    @Override
    public boolean updatePassword(String oldpassword, String newpassword, String userId) {
        TUserEntity userEntity = getById(userId);
        if (PasswordEncryp.IfPassowrd(oldpassword, userEntity.getUrPassword())) {
            //?????????????????????????????????????????????      ?????????????????????????????????????????????????????????
            userEntity.setUrPassword(PasswordEncryp.getPaawordEncryp(newpassword));
            //????????????
            boolean b = updateById(userEntity);
            return b;
        }
        return false;
    }

    @Override
    public TUserEntity findById(Integer urId) {
        TUserEntity user = getById(urId);
        user.setRole(roleServicel.getById(user.getUrReId()));
        return user;
    }

    /**
     * ????????????
     *
     * @param urUsername
     * @return
     */
    @Override
    public TUserEntity infoUrUsername(String urUsername) {
        QueryWrapper<TUserEntity> wrapper = new QueryWrapper<TUserEntity>().eq("ur_username", urUsername);
        TUserEntity userEntity = getOne(wrapper);
        return userEntity;
    }

    /**
     * ????????????
     *
     * @param userId
     * @param addUserId
     * @return
     */
    @Override
    @Transactional
    public String addUser(Integer userId, Integer addUserId) {
        QueryWrapper<TAddRecordEntity> wrapper = new QueryWrapper<TAddRecordEntity>().eq("r_u_in", userId).eq("r_u_out", addUserId);
        TAddRecordEntity addRecordEntity1 = addRecordService.getOne(wrapper);
        if (addRecordEntity1 != null) {
            if (addRecordEntity1.getRUTimeOut() != null) {
                //?????????????????? ???????????? ??????????????????????????????????????????
                return "?????????????????????????????????????????????!";
            }
            return "?????????????????????,???????????????";
        }
        TAddRecordEntity addRecordEntity = new TAddRecordEntity();
        addRecordEntity.setRUIn(userId);
        addRecordEntity.setRUOut(addUserId);
        addRecordEntity.setRUTimeIn(new Date());
        val urName = findById(userId).getUrName();
        addRecordEntity.setRURemarks("??????" + urName);
        boolean flag = addRecordService.save(addRecordEntity);
        return flag ? "ok" : "????????????????????????";
    }

    /**
     * ?????????????????????
     * Z
     *
     * @param userId
     * @return
     */
    @Override
    public List<TUserAndAddUser> getAddUsers(Integer userId) {
        List<TUserAndAddUser> list = usserdao.getAddUsers(userId);
        return list;
    }

    /**
     * ????????????
     *
     * @param userId
     * @param agreeUserId
     * @return
     */
    @Override
    @Transactional
    public Q agreeUserId(Integer userId, Integer agreeUserId) {
        //??????????????????
        boolean flag = false;
        QueryWrapper<TAddRecordEntity> wrapper = new QueryWrapper<TAddRecordEntity>().eq("r_u_in", agreeUserId).eq("r_u_out", userId);
        TAddRecordEntity addRecordEntity = new TAddRecordEntity();
        addRecordEntity.setRUTimeOut(new Date());
        addRecordEntity.setRUIn(agreeUserId);
        addRecordEntity.setRUOut(userId);
        flag = addRecordService.update(addRecordEntity, wrapper);

        //????????????????????????
        TGoodFriendEntity goodFriendEntity = new TGoodFriendEntity();
        goodFriendEntity.setTOwn(userId);
        goodFriendEntity.setTGoodFriend(agreeUserId);
        val userEntity = getById(agreeUserId);
        goodFriendEntity.setTName(userEntity.getUrName());
        flag = goodFriendService.save(goodFriendEntity);
        //??????????????????
        goodFriendEntity.setTOwn(agreeUserId);
        goodFriendEntity.setTGoodFriend(userId);
        flag = goodFriendService.save(goodFriendEntity);
        return flag ? Q.ok() : Q.error().put("msg", "????????????????????????");
    }


    /**
     * ??????????????????
     *
     * @param userId
     * @return
     */
    @Override
    public Q getFriends(Integer userId) {
        List<TGoodFriendOv> list = usserdao.getFriends(userId);
        return Q.ok().put("data", list);
    }

    /**
     * ??????
     *
     * @param user
     * @param request
     * @return
     */
    @Override
    public Q login(TUserEntity user, HttpServletRequest request) {
        Q q = null;
        //????????????????????????
        if (PasswordEncryp.IfPassowrd(user.getCaptcha(), user.getCodeEncryption())) {
            TUserEntity user1 = findLogin(user);
            if (user1 != null) {
                //?????????????????????????????????
                if (user1.getUrStuats() != TUserStatusCode.enable.stats) {
                    q = Q.error().put("msg", user1.getUrUsername() + "??????????????????...");
                } else {
                    //????????????
                    //????????????????????????
                    onlineListingService.saveLogin(user1, request);
                    StpUtil.login(user1.getUrId());
                    SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                    q = Q.ok().put("token", tokenInfo).put("userName", user1.getUrName()).put("urAvatar", user1.getUrAvatar());
                }
            } else {
                q = Q.error().put("msg", "???????????????????????????????????????!");
            }
        } else {
            q = Q.error().put("msg", "???????????????!");
        }
        return q;
    }

    @Override
    public void loginOut(String satoken) {
        Integer urId = Integer.parseInt(StpUtil.getLoginIdByToken(satoken).toString());
        StpUtil.logoutByTokenValue(satoken);
        onlineListingService.deleteUserId(urId);
    }


}
