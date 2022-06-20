package com.gx.warehouse_admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gx.warehouse_admin.po.SysRepository;

public interface ISysRepositoryService extends IService<SysRepository> {
    /**
     * 分页查询仓库数据
     */
    Page<SysRepository> selectPage(Page<SysRepository> page);
}
