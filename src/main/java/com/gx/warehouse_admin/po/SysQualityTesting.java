package com.gx.warehouse_admin.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//质检管理表
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SysQualityTesting {
    //质检管理id
    private Integer Id;

    //产品id
    private Integer productId;

    //其他入库id
    private Integer otherProductId;

    //单据编号
    private String documentsNumber;

    //质检状态
    private Byte qualityTestingState;

    //质检日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date qualityTestingDate;

    //质检人
    private String qualityTestingPerson;

    //备注
    private String remarks;

    //数量
    private int quantity;
}
