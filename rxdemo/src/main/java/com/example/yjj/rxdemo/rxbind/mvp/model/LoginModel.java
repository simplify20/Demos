package com.example.yjj.rxdemo.rxbind.mvp.model;

import com.example.yjj.rxdemo.rxbind.bean.User;
import com.example.yjj.rxdemo.rxbind.interactor.LoginService;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author:YJJ
 * @date:2015/11/9
 * @email:yangjianjun@117go.com
 */
public class LoginModel {

    public Observable<User> login(String account, String pswd) {
        return loginService.login(account, pswd)
                .doOnNext(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        if (new Random().nextInt(10) % 2 == 0) {
                            throw new RuntimeException();
                        }
                    }
                })
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private LoginService<User> loginService = new LoginService<User>() {
        @Override
        public Observable<User> login(String account, String psd) {
            return Observable.just(new User("yangjianjun", 22));
        }
    };
}
