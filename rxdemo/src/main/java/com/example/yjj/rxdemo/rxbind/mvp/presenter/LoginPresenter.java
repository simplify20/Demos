package com.example.yjj.rxdemo.rxbind.mvp.presenter;

import com.example.yjj.rxdemo.rxbind.bean.User;
import com.example.yjj.rxdemo.rxbind.mvp.model.LoginModel;
import com.example.yjj.rxdemo.rxbind.mvp.view.LoginActivity;

import rx.Subscriber;
import rx.Subscription;

/**
 * @author:YJJ
 * @date:2015/11/9
 * @email:yangjianjun@117go.com
 */
public class LoginPresenter {
    private LoginActivity activity;
    private LoginModel loginModel;

    public LoginPresenter(LoginActivity activity) {
        this.activity = activity;
        loginModel = new LoginModel();
    }

    public Subscription login(String account, String pswd, Subscriber<User> subscriber) {
        return loginModel.login(account, pswd)
                .subscribe(subscriber);
    }

}
