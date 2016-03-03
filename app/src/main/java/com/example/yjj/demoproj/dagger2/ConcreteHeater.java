package com.example.yjj.demoproj.dagger2;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
public class ConcreteHeater implements Heater {

    @Override
    public void heat() {
        System.out.println("heat...");
    }

    @Override
    public void stopHeat() {
        System.out.println("stop heat");
    }
}
