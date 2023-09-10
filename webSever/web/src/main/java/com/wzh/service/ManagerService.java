package com.wzh.service;


import com.wzh.dao.ManagerMapper;
import com.wzh.model.Manager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ManagerService {
    @Resource
    ManagerMapper managerMapper;
    public Manager getByUsername(String username){
        return managerMapper.getByUsername(username);
    }
}
