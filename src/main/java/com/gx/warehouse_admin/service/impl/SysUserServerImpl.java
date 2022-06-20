package com.gx.warehouse_admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gx.warehouse_admin.mapper.UserMapper;
import com.gx.warehouse_admin.po.SysUser;
import com.gx.warehouse_admin.service.ISysUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServerImpl extends ServiceImpl<UserMapper,SysUser> implements ISysUserServer {

}
