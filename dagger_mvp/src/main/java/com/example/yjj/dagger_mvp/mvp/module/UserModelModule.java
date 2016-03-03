package com.example.yjj.dagger_mvp.mvp.module;

import com.example.yjj.dagger_mvp.ActivityScope;
import com.example.yjj.dagger_mvp.mvp.model.UserModel;
import com.example.yjj.dagger_mvp.webservice.UserService;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
@Module
public class UserModelModule {

    @Provides
    @ActivityScope
    public UserModel provideUserModel(UserService userService) {
        return new UserModel(userService);
    }
}
