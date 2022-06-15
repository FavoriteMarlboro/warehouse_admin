package com.gx.warehouse_admin.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {

    //用户id
    private Integer userId;

    //角色id
    private Integer roleId;

    //部门id
    private Integer departmentId;

    //用户名
    private String userName;

    //密码
    private String userPassword;

    //密码盐
    private String salt;

    //员工工号
    private String staffWork;

    //员工姓名
    private String staffName;

    //性别
    private Byte gender;

    //出生日期
    private Date birthDate;

    //电话
    private String phone;

    //邮件
    private String email;

    //用户状态
    private byte userStatus;
}
