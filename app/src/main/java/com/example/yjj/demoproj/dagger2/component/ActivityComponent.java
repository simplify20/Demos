package com.example.yjj.demoproj.dagger2.component;

import android.app.Activity;

import com.example.yjj.demoproj.PerActivity;
import com.example.yjj.demoproj.dagger2.module.ActivityModule;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2015/11/2
 * @email:yangjianjun@117go.com
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();
}
