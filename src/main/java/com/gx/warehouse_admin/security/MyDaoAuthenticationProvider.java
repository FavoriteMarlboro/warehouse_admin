package com.gx.warehouse_admin.security;

import com.gx.warehouse_admin.util.MD5Util;
import com.gx.warehouse_admin.vo.UserVo;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

//DaoAuthenticationProvider 身份验证提供器
/*
    该类就是在security框架的基础上自定义的密码校验方法
    在security框架的DaoAuthenticationProvider类的基础上扩展的校验方法
    该类的本质功能是把页面传递的密码通过加盐后，编译成MD5和数据库的密码进行校验
 */
public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {
    /*
        父类 DaoAuthenticationProvider
        passwordEncoder 是父类的成员变量，父类的构造器中给该成员变量设置了初始值
        注意：继承结构中，父类的构造器只能够被调用，而不能被子类继承。 调用父类的构造方法要在子类的构造器中使用super()。
     */

    //userDetailsService 用户信息
    public MyDaoAuthenticationProvider(UserDetailsService userDetailsService) {
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.equals(encodedPassword);
            }
        });
    }

    //身份验证检查
    /*
        userDetails 用户详细信息
        authentication 页面传递的用户名和密码
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            this.logger.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException(this.messages
                    .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        //获取页面传递的password(因为我在该类初始化时就将密码传递到父类中了)
        String presentedPassword = authentication.getCredentials().toString();
        //获取数据库的salt(盐)
        UserVo userVo = (UserVo) userDetails;
        String salt = userVo.getSalt();
        //使用MD5工具类，把页面传递的密码和盐编译MD5
        presentedPassword = MD5Util.getMD5(presentedPassword+salt);
        if (!this.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
            this.logger.debug("Failed to authenticate since password does not match stored value");
            throw new BadCredentialsException(this.messages
                    .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
    }

}
