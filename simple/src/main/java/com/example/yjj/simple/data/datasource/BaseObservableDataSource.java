package com.example.yjj.simple.data.datasource;

import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.datasource.impl.BaseDataSource;

import rx.Observable;
import rx.Subscriber;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public abstract class BaseObservableDataSource<R, S> extends BaseDataSource<R, Observable<S>> {

    public BaseObservableDataSource(DataFetcher<R> dataFetcher) {
        super(dataFetcher);
    }

    @Override
    public Observable<S> handleRequest(final IParameter extra, final String... values) {
        return Observable.create(new Observable.OnSubscribe<S>() {
            @Override
            public void call(Subscriber<? super S> subscriber) {
                try {
                    subscriber.onNext(convert(dataFetcher.fetchData(extra, values)));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }

    public abstract S convert(R r);
}
