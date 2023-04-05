package org.digevil.sboot3user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.digevil.sboot3user.mapper")
public class SBoot3UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SBoot3UserApplication.class, args);
    }

}
