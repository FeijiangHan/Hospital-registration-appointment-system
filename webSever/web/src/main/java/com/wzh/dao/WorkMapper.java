package com.wzh.dao;

import com.wzh.model.User;
import com.wzh.model.Work;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkMapper {
    int deleteByPrimaryKey(String id);

    int insert(Work record);

    int insertSelective(Work record);

    Work selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Work record);

    int updateByPrimaryKey(Work record);
    List<Work> getnickname(@Param("nickname")String nickname);

    Work select(Work work);

    Work selectkey(@Param("quant") String quant,@Param("date") String date,@Param("uid") String uid);


    List<Work> yy(@Param("type")String type,@Param("nickname") String nickname,@Param("post") String post);

    List<Work> yyzw(@Param("type")String type,@Param("nickname") String nickname,@Param("post") String post);
    List<Work> yyws(@Param("type")String type,@Param("nickname") String nickname,@Param("post") String post);



}
