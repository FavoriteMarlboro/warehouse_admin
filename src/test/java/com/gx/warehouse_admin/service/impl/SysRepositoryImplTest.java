package com.gx.warehouse_admin.service.impl;

import com.gx.warehouse_admin.po.SysRepository;
import com.gx.warehouse_admin.service.ISysRepositoryService;
import com.gx.warehouse_admin.vo.LayuiTableData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysRepositoryImplTest {

    @Autowired
    private ISysRepositoryService repositoryService;


    @Test
    public void test()
    {
        //设置分页信息(第几页，每页几条数据)
//        PageHelper.startPage(2,2);
        //查询分页数据
        List<SysRepository> users=this.repositoryService.list();//list()查询所有
        for (SysRepository user : users) {
            System.out.println(user);
        }
        //查询数据的总条数
        int count=(int)this.repositoryService.count();
        System.out.println("数据的条数为"+count);

    }
}