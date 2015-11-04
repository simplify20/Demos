package com.example.yjj.rxdemo.lifecycle;

import com.trello.rxlifecycle.FragmentEvent;

import rx.Observable;

/**
 * @author:YJJ
 * @date:2015/11/3
 * @email:yangjianjun@117go.com
 */
public interface FragmentLifecycleProvider {
    Observable<FragmentEvent> lifecycle();

    <T> Observable.Transformer<T, T> bindUntilEvent(FragmentEvent var1);

    <T> Observable.Transformer<T, T> bindToLifecycle();
}
