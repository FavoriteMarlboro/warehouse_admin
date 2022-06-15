package com.gx.warehouse_admin.security;


import com.gx.warehouse_admin.mapper.IUserMapper;
import com.gx.warehouse_admin.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private  IUserMapper userMapper;

    //在这个方法上面获取用户信息及密码
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        UserVo userVo = (UserVo) userMapper.selectByName(username);
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
