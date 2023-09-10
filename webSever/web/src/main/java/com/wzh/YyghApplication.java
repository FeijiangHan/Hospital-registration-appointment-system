package com.wzh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.wzh.dao"})
public class YyghApplication {

    public static void main(String[] args) {
        SpringApplication.run(YyghApplication.class, args);
    }

}
