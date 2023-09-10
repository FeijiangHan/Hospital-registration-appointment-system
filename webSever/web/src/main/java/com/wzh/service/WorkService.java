package com.wzh.service;


import com.wzh.dao.WorkMapper;
import com.wzh.model.User;
import com.wzh.model.Work;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WorkService {
    @Resource
    WorkMapper workMapper;

    public List<Work> listAll(String nickname){
        return workMapper.getnickname(nickname);
    }
    public int del(String id) {
        return workMapper.deleteByPrimaryKey(id);
    }
    public int  update( Work work) {
        return workMapper.updateByPrimaryKeySelective(work);
    }
    public int save(Work work) {
        return workMapper.insertSelective(work);
    }

    public Work gete(Work work){
        return workMapper.select(work);
    }
    public Work get(String id) {
        return workMapper.selectByPrimaryKey(id);
    }

    public Work selectkey(String quant,String date,String uid){
        return workMapper.selectkey(quant, date,uid);
    }
    public List<Work> yy(String type,String nickname,String post){
        return workMapper.yy(type,nickname,post);
    }
    public List<Work> yyzw(String type,String nickname,String post){
        return workMapper.yyzw(type,nickname,post);
    }
    public List<Work> yyws(String type,String nickname,String post){
        return workMapper.yyws(type,nickname,post);
    }
}
