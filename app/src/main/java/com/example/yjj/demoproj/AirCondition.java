package com.example.yjj.demoproj;

import com.example.yjj.demoproj.dagger2.TemperatureController;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
public class AirCondition {

    private TemperatureController temperatureController;

    public AirCondition(TemperatureController temperatureController) {
        this.temperatureController = temperatureController;
    }

    public void makeHotAir() {
        System.out.println("make hot air");
    }

}
