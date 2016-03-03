package com.example.yjj.demoproj.dagger2.producers;

import com.example.yjj.demoproj.dagger2.demo4.User;
import com.google.common.util.concurrent.ListenableFuture;

import dagger.producers.ProducerModule;
import dagger.producers.Produces;

/**
 * @author:YJJ
 * @date:2016/3/2
 * @email:yangjianjun@117go.com
 */
@ProducerModule(includes = UserModule.class)
public class UserResponseModule {
    /**
     * Not necessary to return ListenableFuture
     *
     * @param user
     * @param userDataSource
     * @return
     */
    @Produces
    ListenableFuture<UserData> retriveUserData(User user, UserDataSource userDataSource) {
        System.out.println("retriveUserData");
        System.out.println(Thread.currentThread());
        return userDataSource.findUserData(user);
    }

    @Produces
    String printUserInfo(UserData userData) {
        System.out.println("printUserInfo");
        System.out.println(Thread.currentThread());
        return userData.toString();
    }
}
