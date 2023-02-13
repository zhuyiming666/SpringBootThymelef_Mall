package com.zym;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zym.mapper")
public class SpringBootThymelefMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootThymelefMallApplication.class, args);
    }

}
