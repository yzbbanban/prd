package com.pl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.pl.dao")
public class PrdApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrdApplication.class, args);
    }

}

