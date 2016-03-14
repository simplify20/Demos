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
public class BaseDaggerRepository<T> extends BaseRepository<T, ListenableFuture<T>> {

    private String TAG = "DaggerBaseRepository";
    private DataSource<ListenableFuture<T>> dataSource;

    public BaseDaggerRepository(DataSource<ListenableFuture<T>> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ListenableFuture<T> getData(IParameter extra, String... values) {
        ListenableFuture<T> listenableFuture = dataSource.getData(dataSource.buildParameter(extra, values));
        Futures.addCallback(listenableFuture, new FutureCallback<T>() {
            @Override
            public void onSuccess(T result) {
                if (callback != null) {
                    DataCallbackAdapter dataCallbackAdapter = new DataCallbackAdapter<>(callback);
                    dataCallbackAdapter.onSuccess(result);
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

    @Override
    public void close() {
        super.close();
    }
}
