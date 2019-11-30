package com.mmt.ossy.demo_3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.mmt.ossy.demo_3.Mapper")
@SpringBootApplication
@EnableScheduling
public class DemoApplication_3 {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication_3.class, args);
    }

}
