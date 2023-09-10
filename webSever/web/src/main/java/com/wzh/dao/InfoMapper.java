package com.wzh.dao;

import com.wzh.model.Info;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(Info record);

    int insertSelective(Info record);

    Info selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Info record);

    int updateByPrimaryKey(Info record);
    List<Info> listMy(String uid);
    List<Info> listMyAll(String uid);

//    查询本周
    Info selectweek(@Param("uid") String uid);

    List<Info> selectrc(@Param("uid") String uid);
    List<Info> selectday(@Param("tid") String tid);


    List<Info> selectWid(@Param("wid") String wid);



    Info selectinfo(@Param("jid") String jid,@Param("quant") String quant);
}
