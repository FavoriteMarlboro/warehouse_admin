package com.gx.warehouse_admin.controller;

import com.gx.warehouse_admin.po.SysQualityTesting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/qualityInspection")
public class qualityInspectionController {

    //来料质检
    @GetMapping("/incomingMaterial")
    public String incomingMaterial(){
        return "qualityInspection/incomingMaterial";
    }

    //来料质检——数据表格
    @PostMapping("/selectQualityTestingTable")
    @ResponseBody
    public List<SysQualityTesting> selectQualityTestingTable(String date,String documentsNumber,String page,String limit){


        return null;
    }
}
