package org.digevil.greys.service;

import org.digevil.greys.exception.WrapException;

import java.util.Random;

/**
 * Created by huangtao729 on 2017/12/12.
 */
public class ComputeProxy {
    private static final Random random = new Random();

    public int remoteCompute(int origin) throws WrapException {
        try {
            Thread.sleep(200 + random.nextInt(1000));
            return origin + 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new WrapException();
        }
    }
}
