package com.example.yjj.demoproj;

import android.app.Application;

import com.example.yjj.demoproj.dagger2.ApplicationComponent;
import com.example.yjj.demoproj.dagger2.ApplicationModule;
import com.example.yjj.demoproj.dagger2.DaggerApplicationComponent;

/**
 * @author:YJJ
 * @date:2015/11/2
 * @email:yangjianjun@117go.com
 */
public class TestApplication extends Application {

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
