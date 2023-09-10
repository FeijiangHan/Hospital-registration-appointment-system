package com.wzh.service;


import com.wzh.dao.InfoMapper;
import com.wzh.model.Info;
import com.wzh.model.Persion;
import com.wzh.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InfoService {
    @Resource
    InfoMapper infoMapper;
    public int save(Info info) {
        return infoMapper.insertSelective(info);
    }

    public List<Info> listMy(String uid){
        return infoMapper.listMy(uid);
    }
    public int  update( Info info) {
        return infoMapper.updateByPrimaryKeySelective(info);
    }

    public List<Info> listMyAll(String uid){
        return infoMapper.listMyAll(uid);
    }
    public Info selectweek(String uid) {
        return infoMapper.selectweek(uid);
    }

    public List<Info> selectrc(String uid) {
        return infoMapper.selectrc(uid);
    }

    public List<Info> selectday(String tid) {
        return infoMapper.selectday(tid);
    }
    public List<Info> selectwid(String wid) {
        return infoMapper.selectWid(wid);
    }

    public Info selectinfo(String jid,String quant) {
        return infoMapper.selectinfo(jid, quant);
    }


}
