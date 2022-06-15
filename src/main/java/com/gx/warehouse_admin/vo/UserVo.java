package com.gx.warehouse_admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends com.gx.warehouse_admin.po.SysUser implements UserDetails {
    /**
     * 用户的权限列表
     */
    private List<GrantedAuthority> authorities;


    //==============UserDetails接口的方法

    //返回用户权限列表的信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    //返回用户账号
    @Override
    public String getUsername() {
        return this.getUserName();
    }

    //返回用户密码
    @Override
    public String getPassword() {
        return this.getUserPassword();
    }

    //账号是否没有过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    //账号是否没有锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //账号的认证证书是否没有过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //账号是否有效（返回数据库中用户的状态）
    @Override
    public boolean isEnabled() {
        //如果为1 就为true，账号有效
        return this.getUserStatus() == 1;
    }
}
