package com.example.yjj.demoproj;

import android.app.Application;
import android.content.Context;

import com.example.yjj.demoproj.dagger2.component.ApplicationComponent;
import com.example.yjj.demoproj.dagger2.component.DaggerApplicationComponent;
import com.example.yjj.demoproj.dagger2.module.ApplicationModule;

/**
 * @author:YJJ
 * @date:2015/11/2
 * @email:yangjianjun@117go.com
 */
public class TestApplication extends Application {

    private ApplicationComponent mComponent;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        context = getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }

    public static Context getContext() {
        return context;
    }
}
