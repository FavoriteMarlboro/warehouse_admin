package com.gx.warehouse_admin.mapper;

import com.gx.warehouse_admin.po.SysMenu;
import com.gx.warehouse_admin.po.SysUser;
import com.gx.warehouse_admin.vo.UserVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<SysUser> {

    @Select("select * from sys_user where user_name = #{userName}")
    UserVo selectByName(String userName);

    //根据角色id查询角色权限
    @Select("SELECT sys_menu.id,sys_menu.parent_id,sys_menu.menu_name,sys_menu.menu_icon,sys_menu.menu_url,sys_menu.menu_sort,sys_menu.menu_type,sys_menu.menu_status,sys_menu.authorize,sys_menu.remark FROM sys_menu_authorize  INNER JOIN sys_menu ON sys_menu_authorize.menu_id = sys_menu.id WHERE sys_menu_authorize.role_id = #{roleId,jdbcType=INTEGER}")
    List<SysMenu> selectAuthorizeByRoleId(int roleId);
}
