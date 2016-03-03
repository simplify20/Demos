package com.example.yjj.rxdemo.rxbind.mvp.presenter;

import com.example.yjj.rxdemo.rxbind.bean.User;
import com.example.yjj.rxdemo.rxbind.mvp.model.RefreshModel;
import com.example.yjj.rxdemo.rxbind.mvp.view.RefreshActivity;

import java.util.List;
import java.util.Random;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author:YJJ
 * @date:2015/11/9
 * @email:yangjianjun@117go.com
 */
public class RefreshPresenter {
    private RefreshActivity activity;
    private RefreshModel model;

    public RefreshPresenter(RefreshActivity activity) {
        this.activity = activity;
        model = new RefreshModel();
    }

    public interface RefreshListener {
        public void refreshOk(List<User> users);

        public void refreshError();

        public void refreshFinished();
    }

    public Subscription LoadUsers(Subscriber<List<User>> subscriber) {
        return model.loadUsers()
                .subscribe(subscriber);

    }

    public void LoadUsers1() {
        model.loadUsers()
                .doOnNext(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> users) {
                        if (new Random().nextInt(10) % 2 == 0) {
                            throw new RuntimeException();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new DemoSubscriber());

    }

    private class DemoSubscriber extends Subscriber<List<User>> {

        @Override
        public void onCompleted() {
            activity.refreshFinished();
        }

        @Override
        public void onError(Throwable e) {
            activity.refreshError();
        }

        @Override
        public void onNext(List<User> users) {
            activity.refreshOk(users);
        }
    }

}
