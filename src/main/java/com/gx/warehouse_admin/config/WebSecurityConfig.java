package com.gx.warehouse_admin.config;

import com.gx.warehouse_admin.security.MyDaoAuthenticationProvider;
import com.gx.warehouse_admin.security.UserDetailServiceImpl;
import com.gx.warehouse_admin.security.filer.LoginFiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity //该注解是开启下面重写的三个方法@EnableGlobalMethodSecurity() //开启方法的权限注解
//开启spring方法级安全时
//prePostEnabled 、securedEnabled 和 jsr250Enabled 三种不同的机制
/*
    prePostEnabled 为true时开启两个注解 @PreAuthorize和@PostAuthorize
    这两个注解在需要设置权限的方法上使用；@PreAuthorize 注解会在方法执行前进行权限验证
    而 @PostAuthorize 注解会在方法执行后进行验证。
*/
//securedEnabled - 用来定义业务方法的安全配置。在需要安全[角色/权限等]的方法上指定 @Secured，并且只有那些角色/权限的用户才可以调用该方法。
//jsr250Enabled -启用 JSR-250 安全控制注解，这属于 JavaEE 的安全规范 -@DenyAll： 拒绝所有访问;   @RolesAllowed({"a","b"})： 该方法只要具有指定的任意一种权限就可以访问; @PermitAll： 允许所有访问
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginFiler loginFiler;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    //配置认证信息（用户名、密码、权限等）
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new MyDaoAuthenticationProvider(userDetailService));
    }

    //
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //MyDaoAuthenticationProvider 类，将userDetailService方法获取的用户信息，进行密码MD5的校验
//       auth.authenticationProvider(new MyDaoAuthenticationProvider(userDetailService));
//    }


    //web级的安全规则配置（配置静态资源的安全规则）
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/**/*.js",
                "/**/*.css",
                "/**/*.*",
                "/**/*.html");

    }

    //请求安全规则配置(配置请求的规则，例如哪个方法可以访问，哪个不可以访问)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
         * anyRequest          |   匹配所有请求路径
         * access              |   SpringEl表达式结果为true时可以访问
         * anonymous           |   匿名可以访问
         * denyAll             |   用户不能访问
         * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
         * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
         * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
         * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
         * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
         * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
         * permitAll           |   用户可以任意访问
         * rememberMe          |   允许通过remember-me登录的用户访问
         * authenticated       |   用户登录后（还要权限）方可访问
         */
        http
                //验证码校验  addFilterBefore(参数1,参数2) 在谁之前进行验证；
                //参数1 执行的类（要进行验证的类）； 参数2 在谁之前，在用户校验之前进行验证码校验
                .addFilterBefore(loginFiler, UsernamePasswordAuthenticationFilter.class)
                //authorizeRequests() 授权请求
                .authorizeRequests()
                //"/login"和 "/identity"（登录和验证码）两个功能的方法可以匿名访问anonymous()
                .antMatchers("/login","/identity").anonymous()
                //anyRequest() 如何请求的意思； authenticated() 用户需要权限； 这句代码的意思是如何请求都要权限
                .anyRequest().authenticated()
                .and() //and：和
                .formLogin() //自定义登录页面和处理方式
                .loginPage("/logins") //自定义登录页面，会覆盖Security默认的login页面
                //登录成功的处理  AuthenticationSuccessHandler 登录成功的接口；
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        //通过流
                        PrintWriter out = response.getWriter();
                        out.write("{\"status\":\"200\",\"msg\":\"登录成功，欢迎老铁入坑\"}");
                        out.flush();
                        out.close();
                    }
                })
                //登录失败的处理
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        out.write("{\"status(状态)\":\"fail(状态失败)\",\"msg\":\"登录失败\"}");
                        //exception.printStackTrace(); //异常打印
                        System.out.println(exception);
                        out.flush();
                        out.close();
                    }
                })
                //处理登录的方法 post/login 为Security自带的
                .loginProcessingUrl("/login")
                //自定义用户名和密码字段（参数字段）
                .usernameParameter("userName").passwordParameter("password").permitAll()
                .and().logout().permitAll() //注销的方法，认证的用户可以访问
                .and().csrf().disable() //csrf容易产生csrf攻击（属于跨域相关的攻击）
                //异常处理
                .exceptionHandling()
                //认证过的用户无权限访问的
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        String msg = String.format("请求访问：你无权限访问当前资源",request.getRequestURI());
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        out.write("{\"status(状态)\":\"fail(状态失败)\",\"msg\""+msg+"");
                        out.flush();
                        out.close();
                    }
                })
                //匿名用户访问无权限资源时的异常
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        String msg = String.format("请求访问：%s，认证失败，无法访问系统资源", request.getRequestURI());
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        out.write("{\"status\":\"fail\",\"msg\":\"" + msg + "\"}");
                        out.flush();
                        out.close();
                    }
                });
        //退出登录逻辑
        http.logout()
                .logoutUrl("/loginOut")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        out.write("{\"status(状态)\":\"ok\",\"msg\":\"退出成功\"");
                        out.flush();
                        out.close();
                    }
                })
                //删除cookies  deleteCookies(cookies的名称)
                .deleteCookies("JSESSIONID");
    }
}
