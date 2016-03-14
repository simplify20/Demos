package com.example.yjj.simple.data.datasource;

import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.datasource.impl.RequestParameter;
import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.datasource.impl.BaseDataSource;

import rx.Observable;
import rx.Subscriber;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public abstract class BaseObservableDataSource<T> extends BaseDataSource<T, Observable<T>> {


    public BaseObservableDataSource(DataFetcher<T> dataFetcher) {
        super(dataFetcher);
    }

    @Override
    public Observable<T> convert(final IParameter requestParameter) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(dataFetcher.fetchData((RequestParameter) requestParameter));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
}
