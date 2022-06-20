package com.gx.warehouse_admin.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 
 * 仓库表
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SysRepository implements Serializable {
    /**
     * 仓库id
     */
    private Integer repositoryId;

    /**
     * 仓库名称
     */
    private String repositoryName;

    /**
     * 仓库代号
     */
    private String repositoryCode;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 仓库储存货物类型id
     */
    private Integer cargoTypeId;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}