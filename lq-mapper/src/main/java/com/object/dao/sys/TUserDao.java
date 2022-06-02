package com.object.dao.sys;

import com.object.module.lq.sys.entity.TUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.object.module.lq.sys.ov.TGoodFriendOv;
import com.object.module.lq.sys.ov.TUserAndAddUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-07-24 15:24:06
 */
@Mapper
public interface TUserDao extends BaseMapper<TUserEntity> {
 @Select("SELECT \n" +
         "t.`ur_name`,t.`ur_avatar`,t.`ur_username`,a.`r_u_remarks`,a.`r_u_time_in`,a.`r_u_in`,a.`r_u_time_out`,a.`r_u_time_out`\n" +
         " FROM\n" +
         "t_add_record a\n" +
         "LEFT JOIN t_user t ON t.`ur_id`=a.`r_u_in`\n" +
         "WHERE a.`r_u_out`=#{userId}")
    public List<TUserAndAddUser> getAddUsers(Integer userId);

 @Select("SELECT \n" +
         "ur.`ur_avatar`,ur.`ur_name`,ur.`ur_username`,ur.`ur_id`,ur.`ur_email`,\n" +
         "gf.`t_name`\n" +
         "FROM \n" +
         "t_good_friend gf\n" +
         "LEFT JOIN t_user ur ON ur.`ur_id`=gf.`t_good_friend`\n" +
         "WHERE gf.`t_own`=#{userId}")
    List<TGoodFriendOv> getFriends(Integer userId);
}
