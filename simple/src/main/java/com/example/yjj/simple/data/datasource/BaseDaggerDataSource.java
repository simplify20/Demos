package com.example.yjj.simple.data.datasource;

import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.datasource.impl.BaseDataSource;
import com.example.yjj.simple.framework.util.Logger;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
public abstract class BaseDaggerDataSource<R, S> extends BaseDataSource<ListenableFuture<R>, ListenableFuture<S>> {
    private String TAG = "BaseDaggerDataSource";

    public BaseDaggerDataSource(DataFetcher<ListenableFuture<R>> dataFetcher) {
        super(dataFetcher);
    }

    @Override
    public ListenableFuture<S> handleRequest(IParameter extra, String... values) {
        ListenableFuture<S> result = null;
        try {
            if (dataFetcher != null) {
                ListenableFuture<R> fetchData = dataFetcher.fetchData(extra, values);//dataFetcher通过Producer实现
                return Futures.transform(fetchData, new AsyncFunction<R, S>() {
                    @Override
                    public ListenableFuture<S> apply(R input) throws Exception {
                        Logger.d(TAG, "transform thread:" + Thread.currentThread().getName());
                        return Futures.immediateFuture(convert(input));
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public abstract S convert(R input);
}
