package com.example.yjj.simple.biz;

import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.datasource.DataSource;
import com.example.yjj.simple.framework.repository.impl.BaseRepository;
import com.example.yjj.simple.framework.repository.impl.DataCallbackAdapter;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
public abstract class BaseDaggerRepository<C, S> extends BaseRepository<C, ListenableFuture<S>> {

    private String TAG = "DaggerBaseRepository";
    private DataSource<ListenableFuture<S>> dataSource;

    public BaseDaggerRepository(DataSource<ListenableFuture<S>> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ListenableFuture<S> getData(IParameter extra, String... values) {
        ListenableFuture<S> listenableFuture = dataSource.getData(extra, values);
        Futures.addCallback(listenableFuture, new FutureCallback<S>() {
            @Override
            public void onSuccess(S result) {
                if (callback != null) {
                    DataCallbackAdapter<C> dataCallbackAdapter = new DataCallbackAdapter<>(callback);
                    dataCallbackAdapter.onSuccess(convert(result));
                    dataCallbackAdapter.onComplete();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (callback != null) {
                    DataCallbackAdapter dataCallbackAdapter = new DataCallbackAdapter<>(callback);
                    dataCallbackAdapter.onError(t);
                }
            }
        });
        return listenableFuture;
    }

    public abstract C convert(S s);

    @Override
    public void close() {
        super.close();
    }
}
