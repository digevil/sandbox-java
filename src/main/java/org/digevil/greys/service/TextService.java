package org.digevil.greys.service;

import org.digevil.greys.exception.WrapException;

import java.util.Random;

/**
 * Created by huangtao729 on 2017/12/12.
 */
public class TextService {

    private static final Random random = new Random();

    public String operate(String origin) throws WrapException {
        try {
            return origin + ", " + operation_1(origin) + ", " + operation_2(origin);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new WrapException();
        }
    }

    private int operation_1(String origin) throws InterruptedException {
        Thread.sleep(300 + random.nextInt(100));
        return origin.length();
    }

    private int operation_2(String origin) throws InterruptedException {
        Thread.sleep(500 + random.nextInt(100));
        return origin.hashCode();
    }
}
