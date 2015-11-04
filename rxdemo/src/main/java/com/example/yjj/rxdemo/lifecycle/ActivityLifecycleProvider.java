package com.example.yjj.rxdemo.lifecycle;

import com.trello.rxlifecycle.ActivityEvent;

import rx.Observable;

/**
 * @author:YJJ
 * @date:2015/11/3
 * @email:yangjianjun@117go.com
 */
public interface ActivityLifecycleProvider {
    Observable<ActivityEvent> lifecycle();

    <T> Observable.Transformer<T, T> bindUntilEvent(ActivityEvent var1);

    <T> Observable.Transformer<T, T> bindToLifecycle();
}