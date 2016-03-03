package com.example.yjj.demoproj.dagger2.demo3;

import com.example.yjj.demoproj.PerActivity;
import com.example.yjj.demoproj.dagger2.TemperatureController;
import com.example.yjj.demoproj.dagger2.module.TemperatureControllerModule;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
@dagger.Subcomponent(modules = TemperatureControllerModule.class)
@PerActivity
public interface SubComponent {

    TemperatureController tempControl();

    /**
     * bindings are inherited form parent
     * @return
     */
    UploadManager manager();
}
