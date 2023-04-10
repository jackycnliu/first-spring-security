package com.becom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@MapperScan
public class FirstSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstSpringSecurityApplication.class, args);
    }

}
