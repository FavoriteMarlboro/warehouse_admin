package com.gx.warehouse_admin.service.impl;

import com.gx.warehouse_admin.mapper.UserMapper;
import com.gx.warehouse_admin.po.SysUser;
import com.gx.warehouse_admin.service.ISysUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServerImpl implements ISysUserServer {


    private UserMapper userMapper;

    @Autowired
    public SysUserServerImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public SysUser selectByName(String userName,SysUser users) {
        SysUser sysUser = this.userMapper.selectOne(users);
        System.out.println(sysUser);
        System.out.println("---------------------------------------------------");
        return this.userMapper.selectByName(userName);

    }
}
