package com.example.yjj.demoproj.dagger2.component;

import com.example.yjj.demoproj.PerActivity;
import com.example.yjj.demoproj.dagger2.module.ActivityModule;
import com.example.yjj.demoproj.dagger2.view.UserListFragment;
import com.example.yjj.demoproj.dagger2.module.UserModule;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2015/11/2
 * @email:yangjianjun@117go.com
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {

    void inject(UserListFragment fragment);

}
