package com.example.yjj.demoproj.dagger2;

import com.example.yjj.demoproj.PerActivity;

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
