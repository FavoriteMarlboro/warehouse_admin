package com.gx.warehouse_admin.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 用户表
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
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
     * is_deleted
     */
    private Byte isDeleted;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 密码盐值
     */
    private String salt;

    /**
     * 所属部门id
     */
    private Integer departmentId;

    /**
     * 职位id
     */
    private Integer positionId;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 性别
     */
    private Byte gender;

    /**
     * 出身日期
     */
    private Date birthday;

    /**
     * 头像
     */
    private String portrait;

    /**
     * Email
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * QQ
     */
    private String qq;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 用户状态
     */
    private Byte userStatus;

    /**
     * 登录次数
     */
    private Integer loginCount;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}