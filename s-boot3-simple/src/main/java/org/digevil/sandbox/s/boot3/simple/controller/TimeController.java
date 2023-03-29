package org.digevil.sandbox.s.boot3.simple.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/time")
public class TimeController {

    @GetMapping("/current")
    public String current() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");
        return sdf.format(new Date());
    }
}
