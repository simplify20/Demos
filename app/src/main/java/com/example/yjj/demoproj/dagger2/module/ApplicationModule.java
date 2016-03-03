package com.example.yjj.demoproj.dagger2.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.yjj.demoproj.TestApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2015/11/2
 * @email:yangjianjun@117go.com
 */
@Module
public class ApplicationModule {
    private final TestApplication application;

    public ApplicationModule(TestApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreference() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
