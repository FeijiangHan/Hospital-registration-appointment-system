package com.wzh.service;


import com.wzh.dao.UserMapper;
import com.wzh.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    public int save(User user) {
        return userMapper.insertSelective(user);
    }
    //    登录
    public User getByUsernametype(String username,String type){
        return userMapper.getByUsernametype(username,type);
    }
    public User getByUsername(String username){
        return userMapper.getByUsername(username);
    }
    //    更新个人信息
    public int updategrxx(String id,String tel,String nickname,String head){
        return userMapper.updategrxx(id, tel, nickname, head);
    }
    //    修改密码
    public int  updatepass( String id,String pass) {
        return userMapper.updatepass(id,pass);
    }

    public List<User> list(User user){
        return userMapper.list(user);
    }

    public User get(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
    public List<User> listAll(String nickname){
        return userMapper.getnickname(nickname);
    }
    public int del(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }
    public int  update( User u) {
        return userMapper.updateByPrimaryKeySelective(u);
    }
    public List<User> listys(String nickname){
        return userMapper.getys(nickname);
    }
    public List<User> ys(){
        return userMapper.ys();
    }
}
