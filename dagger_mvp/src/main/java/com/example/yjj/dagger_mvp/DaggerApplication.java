package com.example.yjj.dagger_mvp;

import android.app.Application;
import android.content.Context;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
public class DaggerApplication extends Application {
    private static Context context;
    private static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this))
                .build();

    }

    public static Context getContext() {
        return context;
    }

    public static ApplicationComponent getComponent() {
        return component;
    }
}
