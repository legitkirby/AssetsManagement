package com.isoftstone.assetsmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.isoftstone.assetsmanagement")
@MapperScan("com.isoftstone.assetsmanagement.mapper")
public class AssetsManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetsManagementApplication.class, args);
    }
}
