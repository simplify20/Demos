package com.example.yjj.demoproj.dagger2;

import javax.inject.Inject;

/**
 * Contrast  method 1 and method 2
 *
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
public class RiceCooker {


//    private Heater heater;
//    private TemperatureController temperatureController;
//
//    //method 1
//    @Inject
//    public RiceCooker(Heater heater, TemperatureController temperatureController) {
//        this.heater = heater;
//        this.temperatureController = temperatureController;
//    }

     @Inject Heater heater;
     @Inject TemperatureController temperatureController;

    //method 2  with member injector
    @Inject
    public RiceCooker() {
    }

    public void cookRice() {
        heater.heat();
        temperatureController.controlTemp(200.f);
    }

}
