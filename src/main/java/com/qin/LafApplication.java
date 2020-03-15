package com.qin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.qin.dao")
public class LafApplication {

    public static void main(String[] args) {
        SpringApplication.run(LafApplication.class, args);
    }

}
