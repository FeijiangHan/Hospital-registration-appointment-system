package com.wzh.dao;

import com.wzh.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    //    登录
    User getByUsername(@Param("username")String username);
    //    更新个人信息
    int updategrxx(@Param("id") String id,
                   @Param("tel") String tel,
                   @Param("nickname") String nickname,
                   @Param("head") String head);
    //    修改密码
    int  updatepass(@Param("id") String id,
                    @Param("pass") String pass);

    User getByUsernametype(@Param("username")String username,@Param("type")String type);

    List<User> list(User user);

    List<User> getnickname(@Param("nickname")String nickname);

    List<User> getys(@Param("nickname")String nickname);

    List<User> ys();

}
