package com.gx.warehouse_admin.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 菜单表
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * gmt_create
     */
    private Date gmtCreate;

    /**
     * gmt_modified
     */
    private Date gmtModified;

    /**
     * 父菜单id
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单url
     */
    private String menuUrl;

    /**
     * 菜单排序
     */
    private Integer menuSort;

    /**
     * 菜单类型
     */
    private Byte menuType;

    /**
     * 菜单状态
     */
    private Byte menuStatus;

    /**
     * 权限标识
     */
    private String authorize;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}