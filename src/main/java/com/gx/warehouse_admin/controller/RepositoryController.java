package com.gx.warehouse_admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gx.warehouse_admin.po.SysRepository;
import com.gx.warehouse_admin.service.ISysRepositoryService;
import com.gx.warehouse_admin.vo.LayuiTableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/Repository")
public class RepositoryController {

    @Autowired
    private ISysRepositoryService repositoryService;

    /**
     *仓库管理页面
     */
    @RequestMapping("/index")
    public String Repository()
    {
        return "/repository";
    }

    /**
     * 分页查询仓库数据
     * @param page 当前页数的索引
     * @param limit 每页显示数据的条数
     * @return
     */
    @RequestMapping("/selectPageList")
    @ResponseBody
    public LayuiTableData<SysRepository> selectPageList(Integer page, Integer limit)
    {
        //设置分页信息 page当前页数的索引 limit每页显示数据的条数
        Page<SysRepository> pageParameter=new Page<>(page,limit);
        Page<SysRepository> pageResult=repositoryService.selectPage(pageParameter);

        //把Page<SysRepository> 数据转为 List<SysRepository> 数据；
        List<SysRepository>repositoryList=pageResult.getRecords();

        //查询数据的总条数
        int count=(int)this.repositoryService.count();

        return new LayuiTableData<>(count,repositoryList);
    }




}
