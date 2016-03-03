package com.example.yjj.demoproj.dagger2.producers;

import com.example.yjj.demoproj.dagger2.demo4.User;
import com.google.common.util.concurrent.ListenableFuture;

/**
 * @author:YJJ
 * @date:2016/3/2
 * @email:yangjianjun@117go.com
 */
public interface UserDataSource {
    /**
     * Both producer methods are scheduled on the provided executor,
     * so the execution model is entirely user-specified.
     * @param user
     * @return
     */
    ListenableFuture<UserData>  findUserData(User user);
}
