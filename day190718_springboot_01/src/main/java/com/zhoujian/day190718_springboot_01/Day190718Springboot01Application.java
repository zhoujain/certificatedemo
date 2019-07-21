package com.zhoujian.day190718_springboot_01;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.zhoujian.controller")
@MapperScan("com.zhoujian.dao")
@ComponentScan("com.zhoujian.service")
@SpringBootApplication
public class Day190718Springboot01Application {

    public static void main(String[] args) {
        SpringApplication.run(Day190718Springboot01Application.class, args);
    }

}
