package com.gx.warehouse_admin.security;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gx.warehouse_admin.mapper.UserMapper;
import com.gx.warehouse_admin.po.SysUser;
import com.gx.warehouse_admin.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    //在这个方法上面获取用户信息及密码
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        QueryWrapper<SysUser> wrapper=new QueryWrapper<>();
        wrapper.ge("user_name",username);
        SysUser sysUser=this.userMapper.selectOne(wrapper);
        UserVo userVo=new UserVo();
        userVo.setId(sysUser.getId());
        userVo.setGmtCreate(sysUser.getGmtCreate());
        userVo.setGmtModified(sysUser.getGmtModified());
        userVo.setIsDeleted(sysUser.getIsDeleted());
        userVo.setUserName(sysUser.getUserName());
        userVo.setUserPassword(sysUser.getUserPassword());
        userVo.setSalt(sysUser.getSalt());
        userVo.setDepartmentId(sysUser.getDepartmentId());
        userVo.setPositionId(sysUser.getPositionId());
        userVo.setRoleId(sysUser.getRoleId());
        userVo.setRealName(sysUser.getRealName());
        userVo.setGender(sysUser.getGender());
        userVo.setBirthday(sysUser.getBirthday());
        userVo.setPortrait(sysUser.getPortrait());
        userVo.setEmail(sysUser.getEmail());
        userVo.setMobile(sysUser.getMobile());
        userVo.setQq(sysUser.getQq());
        userVo.setWechat(sysUser.getWechat());
        userVo.setUserStatus(sysUser.getUserStatus());
        userVo.setLoginCount(sysUser.getLoginCount());
        userVo.setRemark(sysUser.getRemark());

        if (userVo == null){
            userVo = new UserVo();
        }else {
//
//            //根据用户的角色id查询权限
//            List<SysMenu> sysMenuList = this.userMapper.selectAuthorizeByRoleId(userVo.getRoleId());
//            //集合，装从用户信息中的权限信息
//            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//            for (SysMenu sysMenu : sysMenuList) {
//                if (sysMenu.getAuthorize() != null && !sysMenu.getAuthorize().isEmpty()){
//                    //
//                    grantedAuthorities.add(new SimpleGrantedAuthority(sysMenu.getAuthorize()));
//                }
//            }
//            userVo.setAuthorities(grantedAuthorities);
        }

        return userVo;
    }
}
