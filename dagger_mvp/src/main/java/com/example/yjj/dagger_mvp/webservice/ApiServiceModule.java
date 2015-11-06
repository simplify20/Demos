package com.example.yjj.dagger_mvp.webservice;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
@Module
public class ApiServiceModule {
    @Provides
    public UserService provideUserService() {
        return new UserServiceImpl();
    }
}
