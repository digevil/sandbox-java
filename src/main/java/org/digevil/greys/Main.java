package org.digevil.greys;

import org.digevil.greys.exception.WrapException;
import org.digevil.greys.model.ValueObject;
import org.digevil.greys.service.ServiceWrapper;

public class Main {

    public static void main(String[] args) {

        ServiceWrapper sw = new ServiceWrapper();

        while (true) {
            ValueObject vo = new ValueObject(0, System.currentTimeMillis() + "");
            sw.execute(vo);
            System.out.println("operated vo: " + vo);
        }
    }
}
