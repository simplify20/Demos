package com.example.yjj.demoproj.dagger2;

import android.app.Activity;

import com.example.yjj.demoproj.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2015/11/2
 * @email:yangjianjun@117go.com
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}
