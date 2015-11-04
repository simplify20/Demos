package com.example.yjj.demoproj.dagger2;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.yjj.demoproj.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2015/11/2
 * @email:yangjianjun@117go.com
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity activity);
    Context context();
    SharedPreferences sharePreference();
}
