package com.object.module.lq.sys.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.EasyExcel;
import com.object.module.lq.sys.entity.TUserEntity;
import com.object.module.lq.sys.ov.TUserAndAddUser;
import com.object.module.lq.sys.service.TUserService;
import com.object.utils.PasswordEncryp;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.object.utils.PageUtils;
import com.object.utils.Q;

import javax.servlet.http.HttpServletResponse;


/**
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-07-24 15:24:06
 */
@RestController
@RequestMapping("/user")
public class TUserController {
    @Autowired
    private TUserService userService;

    /**
     * 列表
     */
    @ApiOperation("列表")
    @RequestMapping("/list")
    public Q list(@ApiParam(value = "查询用户列表", required = true) @RequestParam(required = false) Map<String, Object> params) {
        PageUtils page = userService.queryPage(params);
        return Q.ok().put("page", page);
    }

    /**
     * 密码修改
     *
     * @param oldpassword 旧密码
     * @param newpassword 新密码
     * @param userId      用户id
     * @return
     */
    @GetMapping("updatePssword")
    public Q updateUserPassword(String oldpassword, String newpassword, String userId) {
        boolean flag = userService.updatePassword(oldpassword, newpassword, userId);
        return flag ? Q.ok() : Q.error();
    }

    /**
     * 登录认证：只有登录之后才能进入该方法
     * 获取当前登录用户的信息
     *
     * @param urId
     * @return
     */

    @RequestMapping("/admin/info/{urId}")
    public Q info(@PathVariable("urId") Integer urId) {
        TUserEntity user = userService.findById(urId);
        return Q.ok().put("user", user);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    public Q save(@RequestBody TUserEntity user) {
        user.setUrPassword(PasswordEncryp.getPaawordEncryp(user.getUrPassword()));
        userService.save(user);
        return Q.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Q update(@RequestBody TUserEntity user) {
        userService.updateById(user);
        return Q.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Q delete(@RequestBody Integer[] urIds) {
        userService.removeByIds(Arrays.asList(urIds));
        return Q.ok();
    }

    /**
     * 数据导出
     *
     * @param urIds    用户id
     * @param response
     * @throws IOException
     */
    @PostMapping("/export")
    public Q export(@RequestBody Integer[] urIds, HttpServletResponse response) throws IOException {
        List<TUserEntity> list = userService.listByIds(Arrays.asList(urIds));
        EasyExcel.write(response.getOutputStream(), TUserEntity.class).sheet("用户信息").doWrite(list);
        return Q.ok().put("data", response.getOutputStream());
    }


    /**
     * 查找用户 根据账号==用户名
     *
     * @param urUsername
     * @return
     */
    @GetMapping("/infourUsername/{urUsername}")
    public Q infoUrUsername(@PathVariable String urUsername) {
        TUserEntity userEntity = userService.infoUrUsername(urUsername);
        return Q.ok().put("user", userEntity);
    }


    /**
     * 添加好友
     *
     * @param userId
     * @param
     * @return
     */
    @GetMapping("/addUser/{userId}/{addUserId}")
    public Q addUser(@PathVariable Integer userId, @PathVariable Integer addUserId) {
        String msg = userService.addUser(userId, addUserId);
        return Q.ok().put("msg", msg);
    }


    /**
     * 获取添加好友的申请
     *
     * @param userId
     * @return
     */
    @GetMapping("/getAddUsers/{userId}")
    public Q getAddUsers(@PathVariable Integer userId) {
        List<TUserAndAddUser> list = userService.getAddUsers(userId);
        return Q.ok().put("data", list);
    }

    /**
     * 同意添加好友
     *
     * @param userId
     * @param agreeUserId
     * @return
     */
    @GetMapping("/agreeUser/{userId}/{agreeUserId}")
    public Q agreeUser(@PathVariable Integer userId,
                       @PathVariable Integer agreeUserId) {
        Q q = userService.agreeUserId(userId, agreeUserId);
        return q;
    }

    /**
     * 获取好友列表
     *
     * @param userId
     * @return
     */
    @GetMapping("/getfriends/{userId}")
    public Q getFriends(@PathVariable Integer userId) {
        Q q = userService.getFriends(userId);
        return q;
    }


}
