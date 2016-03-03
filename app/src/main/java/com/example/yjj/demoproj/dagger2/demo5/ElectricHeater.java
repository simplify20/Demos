package com.example.yjj.demoproj.dagger2.demo5;

import com.example.yjj.demoproj.dagger2.Heater;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
public class ElectricHeater implements Heater {
    private float temperature;

    public ElectricHeater(float temperature) {
        this.temperature = temperature;
    }

    @Override
    public void heat() {
        System.out.println(this);
    }

    @Override
    public void stopHeat() {

    }

    @Override
    public String toString() {
        return "ElectricHeater{" +
                "temperature=" + temperature +
                '}';
    }
}
