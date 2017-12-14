package org.digevil.greys;

import org.digevil.greys.exception.WrapException;
import org.digevil.greys.model.ValueObject;
import org.digevil.greys.service.ComputeService;
import org.digevil.greys.service.TextService;

public class Main {

    public static void main(String[] args) {

        ComputeService cs = new ComputeService();
        TextService ts = new TextService();

        while (true) {
            try {
                ValueObject vo = new ValueObject(0, System.currentTimeMillis() + "");
                vo.setText(ts.operate(vo.getText()));
                System.out.println("operated text: " + vo.getText());
            } catch (WrapException e) {
                e.printStackTrace();
            }
        }
    }
}
