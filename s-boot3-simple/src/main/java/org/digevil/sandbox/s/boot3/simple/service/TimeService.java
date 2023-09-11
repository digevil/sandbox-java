package org.digevil.sandbox.s.boot3.simple.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TimeService {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");

    public String getCurrentTime() {
        return sdf.format(new Date());
    }

    public long convertToPosix(String time) throws ParseException {
        return sdf.parse(time).getTime();
    }

}
