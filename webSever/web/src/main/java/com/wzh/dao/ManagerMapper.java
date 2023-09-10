package com.wzh.dao;

import com.wzh.model.Manager;
import org.apache.ibatis.annotations.Param;

public interface ManagerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
    Manager getByUsername(@Param("username")String username);

}
