package org.digevil.sandbox.s.boot3.simple.controller;

import jakarta.annotation.Resource;
import org.digevil.sandbox.s.boot3.simple.dto.TimeDto;
import org.digevil.sandbox.s.boot3.simple.service.TimeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
public class TimeController {

    @Resource
    private TimeService timeService;

    @GetMapping(value = "/current", produces = MediaType.TEXT_PLAIN_VALUE)
    public String current() {
        return timeService.getCurrentTime();
    }

    @GetMapping(value = "/convert/{time}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TimeDto convert(@PathVariable String time) throws Exception {
        long timePosix = timeService.convertToPosix(time);
        return new TimeDto(timePosix);
    }

}
