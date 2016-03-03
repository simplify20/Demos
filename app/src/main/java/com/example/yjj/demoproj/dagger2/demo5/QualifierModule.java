package com.example.yjj.demoproj.dagger2.demo5;

import com.example.yjj.demoproj.dagger2.Heater;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
@Module
public class QualifierModule {

    @Provides
    @Named("hot plate")
    Heater provideHotPlateHeater() {
        return new ElectricHeater(70);
    }

    @Provides
    @Named("water")
    Heater provideWaterHeater() {
        return new ElectricHeater(93);
    }
}
