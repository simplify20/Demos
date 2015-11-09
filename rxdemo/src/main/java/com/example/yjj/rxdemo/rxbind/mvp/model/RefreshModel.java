package com.example.yjj.rxdemo.rxbind.mvp.model;

import com.example.yjj.rxdemo.rxbind.bean.User;
import com.example.yjj.rxdemo.rxbind.interactor.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author:YJJ
 * @date:2015/11/9
 * @email:yangjianjun@117go.com
 */
public class RefreshModel {
    public Observable<List<User>> loadUsers() {
        return userService.loadUsers()
                .doOnNext(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> users) {
                        if (new Random().nextInt(10) % 2 == 0) {
                            throw new RuntimeException();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    private UserService userService = new UserService() {
        @Override
        public Observable<List<User>> loadUsers() {
            List<User> users = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                User user = new User("DemoUser" + i, 20 + i);
                users.add(user);
            }
            return Observable.just(users);
        }
    };
}
