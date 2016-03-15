package com.example.yjj.simple.biz;


import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.repository.impl.BaseRepository;
import com.example.yjj.simple.framework.repository.impl.DataCallbackAdapter;
import com.example.yjj.simple.framework.datasource.DataSource;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public abstract class BaseObservableRepository<C, S> extends BaseRepository<C, Subscription> {
    private Scheduler postScheduler;
    private Scheduler workScheduler;
    private String TAG = "BaseObservableRepository";

    protected DataSource<Observable<S>> dataSource;
    protected CompositeSubscription subscriptions = new CompositeSubscription();

    public BaseObservableRepository(Scheduler postScheduler, Scheduler workScheduler, DataSource<Observable<S>> dataSource) {
        this.postScheduler = postScheduler;
        this.workScheduler = workScheduler;
        this.dataSource = dataSource;
    }

    @Override
    public Subscription getData(IParameter extra, String... values) {
        Subscription subscription = dataSource.getData(extra, values)
                .subscribeOn(workScheduler)
                .observeOn(postScheduler)
                .map(new Func1<S, C>() {
                    @Override
                    public C call(S s) {
                        return convert(s);
                    }
                })
                .subscribe(new Subscriber<C>() {
                    @Override
                    public void onCompleted() {
                        if (callback != null)
                            new DataCallbackAdapter<>(callback).onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callback != null)
                            new DataCallbackAdapter<>(callback).onError(e);
                    }

                    @Override
                    public void onNext(C t) {
                        if (callback != null)
                            new DataCallbackAdapter<>(callback).onSuccess(t);
                    }
                });
        subscriptions.add(subscription);
        return subscriptions;
    }

    @Override
    public void close() {
        if (!subscriptions.isUnsubscribed())
            subscriptions.unsubscribe();
    }

    public abstract C convert(S s);

}
