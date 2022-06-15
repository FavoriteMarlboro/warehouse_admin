package com.gx.warehouse_admin.service;

import com.gx.warehouse_admin.po.SysUser;

public interface ISysUserServer {

    //登录 - 根据用户名查询
    SysUser selectByName(String userName,SysUser users);
}
