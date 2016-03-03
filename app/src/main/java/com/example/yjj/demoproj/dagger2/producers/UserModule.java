package com.example.yjj.demoproj.dagger2.producers;

import com.example.yjj.demoproj.dagger2.demo4.User;

import dagger.producers.ProducerModule;
import dagger.producers.Produces;

/**
 * @author:YJJ
 * @date:2016/3/2
 * @email:yangjianjun@117go.com
 */
@ProducerModule
public class UserModule {

    @Produces
    User provideUser(){
        System.out.println("getUser");
        System.out.println(Thread.currentThread());
        return new User();
    }

    @Produces
    UserDataSource provideDataSource(){
        System.out.println("getDataSource");
        System.out.println(Thread.currentThread());
        return new UserDataService();
    }
}
