package com.example.yjj.simple.data.datasource;

import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.datasource.impl.BaseDataSource;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author:YJJ
 * @date:2016/3/14
 * @email:yangjianjun@117go.com
 */
public abstract class BaseDaggerDataSource<T> extends BaseDataSource<ListenableFuture<T>, ListenableFuture<T>> {
    //TODO INJECT
    protected Executor executor = Executors.newSingleThreadExecutor();
    public BaseDaggerDataSource(DataFetcher<ListenableFuture<T>> dataFetcher) {
        super(dataFetcher);
    }

    @Override
    public ListenableFuture<T> handleRequest(IParameter extra, String... values) {
        ListenableFuture<T> result = null;
        try {
            if (dataFetcher != null)
                result = dataFetcher.fetchData(extra, values);//dataFetcher通过Producer实现
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
