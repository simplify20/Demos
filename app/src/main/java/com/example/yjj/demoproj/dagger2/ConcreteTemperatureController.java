package com.example.yjj.demoproj.dagger2;

import javax.inject.Inject;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
public class ConcreteTemperatureController implements TemperatureController {

    @Inject
    public ConcreteTemperatureController() {
    }

    @Override
    public void controlTemp(float temp) {
        System.out.println("control temp:" + temp);
    }
}
