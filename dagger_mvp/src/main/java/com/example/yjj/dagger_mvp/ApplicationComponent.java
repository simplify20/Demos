package com.example.yjj.dagger_mvp;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.yjj.dagger_mvp.webservice.ApiServiceModule;
import com.example.yjj.dagger_mvp.webservice.UserService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiServiceModule.class})
public interface ApplicationComponent {
    Context context();

    SharedPreferences preferences();

    UserService userService();
}
