package com.example.yjj.rxdemo.rxbind.interactor;

import rx.Observable;

/**
 * @author:YJJ
 * @date:2015/11/5
 * @email:yangjianjun@117go.com
 */
public interface LoadMoreService<T> {

    Observable<T> loadMore();
}
