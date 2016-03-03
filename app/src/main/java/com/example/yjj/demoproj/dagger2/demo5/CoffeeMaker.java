package com.example.yjj.demoproj.dagger2.demo5;

import com.example.yjj.demoproj.dagger2.Heater;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
public class CoffeeMaker {


    @Inject @Named("water") Heater waterHeater;
    @Inject @Named("hot plate") Heater hotPlateHeater;

    @Inject
    public CoffeeMaker() {
    }

    public void make(){
        waterHeater.heat();
        hotPlateHeater.heat();
    }
}
