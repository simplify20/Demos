package com.example.yjj.demoproj.dagger2.demo4;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
public class LazyInjection {
    private boolean needUser = false;
    @Inject
    Lazy<User> lazyUser;

    /**
     * can't inject factory??
     */
    @Inject
    Provider<User> userProvider;

    @Inject
    public LazyInjection() {
    }

    public void setNeedUser(boolean needUser) {
        this.needUser = needUser;
    }

    public void meeting() {
        System.out.println("leader speak");
        if (needUser) {
            User user = lazyUser.get();
            System.out.println(user);
            user.speak();
        }
    }

    public void generateUsers(int count) {
        if (count <= 0)
            return;
        List<User> users = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            users.add(userProvider.get());
        }
        System.out.println(users.size());
    }
}
