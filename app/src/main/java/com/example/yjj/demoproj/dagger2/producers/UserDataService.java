package com.example.yjj.demoproj.dagger2.producers;

import com.example.yjj.demoproj.dagger2.demo4.User;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/**
 *
 * @author:YJJ
 * @date:2016/3/2
 * @email:yangjianjun@117go.com
 */
public class UserDataService implements UserDataSource {
    @Override
    public ListenableFuture<UserData> findUserData(final User user) {
        final UserData userData = new UserData();
        //模拟同步网络请求
        System.out.println("retriving data...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userData.gender = 0;
        userData.desc = "this is a test of retriving data ";
        userData.name = "YANG";
        return Futures.immediateFuture(userData);
    }
}
