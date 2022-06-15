package com.gx.warehouse_admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gx.warehouse_admin.mapper")
public class WarehouseAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseAdminApplication.class, args);
    }

}
