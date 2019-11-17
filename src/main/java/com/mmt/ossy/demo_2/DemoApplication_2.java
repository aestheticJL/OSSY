package com.mmt.ossy.demo_2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.mmt.ossy.demo_2.Mapper")
@SpringBootApplication
@EnableScheduling
public class DemoApplication_2 {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication_2.class, args);
    }

}
