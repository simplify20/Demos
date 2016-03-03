package com.example.yjj.demoproj.dagger2.module;

import com.example.yjj.demoproj.dagger2.ConcreteTemperatureController;
import com.example.yjj.demoproj.dagger2.TemperatureController;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
@Module
public class TemperatureControllerModule {
    @Provides
    public TemperatureController provideTemperatureController(ConcreteTemperatureController concreteTemperatureController) {
        return concreteTemperatureController;
    }

}
