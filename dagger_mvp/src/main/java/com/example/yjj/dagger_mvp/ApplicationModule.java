package com.example.yjj.dagger_mvp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
@Module
public class ApplicationModule {

    private DaggerApplication application;

    public ApplicationModule(DaggerApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharePreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

}
