package com.gx.warehouse_admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@MapperScan("com.gx.warehouse_admin.mapper")
public class WarehouseAdminApplication{

    public static void main(String[] args) {
        SpringApplication.run(WarehouseAdminApplication.class, args);
    }

//    //使用ResourceHandlerRegistry 在web中注入静态资源处理程序和位置
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //addResourceHandler 资源映射路径 //addResourceLocations访问的绝对路径
//        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
//        super.addResourceHandlers(registry);
//    }
}
