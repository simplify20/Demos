package com.example.yjj.demoproj.dagger2.module;

import com.example.yjj.demoproj.AirCondition;
import com.example.yjj.demoproj.dagger2.ConcreteHeater;
import com.example.yjj.demoproj.dagger2.Heater;
import com.example.yjj.demoproj.dagger2.TemperatureController;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
@Module(includes = TemperatureControllerModule.class)
public class RiceCookerModule {

    @Provides
    public Heater provideHeater() {
        return new ConcreteHeater();
    }

    @Provides
    public AirCondition provideAirCondition(TemperatureController temperatureController) {
        return new AirCondition(temperatureController);
    }


}
