package com.wzh.service;


import com.wzh.dao.PersionMapper;
import com.wzh.model.Persion;
import com.wzh.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersionService {
    @Resource
    PersionMapper persionMapper;

    public int save(Persion persion) {
        return persionMapper.insertSelective(persion);
    }

    public int  update( Persion p) {
        return persionMapper.updateByPrimaryKeySelective(p);
    }

    public int del(String id) {
        return persionMapper.deleteByPrimaryKey(id);
    }


    public List<Persion> getMy(String uid){
        return persionMapper.getMy(uid);
    }


}
