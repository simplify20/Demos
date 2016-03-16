package com.example.yjj.simple.biz;


import com.example.yjj.simple.SimpleApplication;
import com.example.yjj.simple.data.di.common.component.ApplicationComponent;
import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.datasource.DataSource;
import com.example.yjj.simple.framework.repository.impl.BaseRepository;

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
    protected DataSource<Observable<S>> dataSource;
    protected CompositeSubscription subscriptions = new CompositeSubscription();

    private static String TAG = "BaseObservableRepository";
    private Scheduler postScheduler;
    private Scheduler workScheduler;

    public BaseObservableRepository(DataSource<Observable<S>> dataSource) {
        this.dataSource = dataSource;
        ApplicationComponent applicationComponent = SimpleApplication.getApplicationComponent();
        this.postScheduler = applicationComponent.getPostScheduler();
        this.workScheduler = applicationComponent.getWorkScheduler();
    }

    @Override
    public Subscription getData(IParameter extra, String... values) {
        super.getData(extra, values);
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
                        if (callback != null) {
                            callback.onComplete();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callback != null) {
                            callback.onError(e);
                        }
                    }

                    @Override
                    public void onNext(C t) {
                        if (callback != null) {
                            callback.onSuccess(t);
                        }
                    }
                });
        subscriptions.add(subscription);
        return subscriptions;
    }

    @Override
    public void close() {
        super.close();
        if (!subscriptions.isUnsubscribed())
            subscriptions.unsubscribe();
        if (dataSource != null) {
            dataSource.close();
        }
    }

    public abstract C convert(S s);

}
