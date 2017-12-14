package org.digevil.greys.service;

import org.digevil.greys.exception.WrapException;
import org.digevil.greys.model.ValueObject;

/**
 * Created by huangtao729 on 2017/12/14.
 */
public class ServiceWrapper {
    private ComputeProxy cp = new ComputeProxy();
    private ComputeService cs = new ComputeService();
    private TextService ts = new TextService();

    public void execute(ValueObject vo) {
        try {
            // 1
            vo.setText(ts.operate(vo.getText()));
            // 2
        } catch (WrapException e) {
            e.printStackTrace();
        }
    }
}
