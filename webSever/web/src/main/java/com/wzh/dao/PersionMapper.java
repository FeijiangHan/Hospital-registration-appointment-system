package com.wzh.dao;

import com.wzh.model.Persion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Persion record);

    int insertSelective(Persion record);

    Persion selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Persion record);

    int updateByPrimaryKey(Persion record);

    List <Persion> getMy(@Param("uid") String uid);
}
