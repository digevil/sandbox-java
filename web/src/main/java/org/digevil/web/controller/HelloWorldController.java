package org.digevil.web.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangtao729 on 2017/12/14.
 */
@RestController
@EnableAutoConfiguration
public class HelloWorldController {

    @RequestMapping("/")
    String home() {
        return "Hello, World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldController.class, args);
    }
}
