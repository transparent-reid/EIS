package com.spm.eis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.spm.eis.mapper")
public class EisApplication {

    public static void main(String[] args) {
        SpringApplication.run(EisApplication.class, args);
    }

}
