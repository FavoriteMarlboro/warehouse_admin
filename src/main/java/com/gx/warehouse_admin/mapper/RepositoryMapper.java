package com.gx.warehouse_admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gx.warehouse_admin.po.SysRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryMapper extends BaseMapper<SysRepository>
{
    /**
     * 分页查询仓库数据
     */
    Page<SysRepository> selectPage(Page<SysRepository> page);
}
