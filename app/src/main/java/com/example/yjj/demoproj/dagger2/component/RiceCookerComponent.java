package com.example.yjj.demoproj.dagger2.component;

import com.example.yjj.demoproj.AirCondition;
import com.example.yjj.demoproj.dagger2.RiceCooker;
import com.example.yjj.demoproj.dagger2.module.RiceCookerModule;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
@Component(modules = RiceCookerModule.class)
public interface RiceCookerComponent {

    RiceCooker cooker();

    AirCondition airCondition();
}
