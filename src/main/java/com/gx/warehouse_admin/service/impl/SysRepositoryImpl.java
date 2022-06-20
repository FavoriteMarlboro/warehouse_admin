package com.gx.warehouse_admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gx.warehouse_admin.mapper.RepositoryMapper;
import com.gx.warehouse_admin.po.SysRepository;
import com.gx.warehouse_admin.service.ISysRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRepositoryImpl extends ServiceImpl<RepositoryMapper, SysRepository> implements ISysRepositoryService {

    private RepositoryMapper repositoryMapper;

    @Autowired
    public SysRepositoryImpl(RepositoryMapper repositoryMapper) {
        this.repositoryMapper = repositoryMapper;
    }

    @Override
    public Page<SysRepository> selectPage(Page<SysRepository> page) {
        return this.repositoryMapper.selectPage(page);
    }
}
