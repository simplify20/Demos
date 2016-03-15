package com.example.yjj.simple.framework.datasource.impl;

import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.datasource.DataSource;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public abstract class BaseDataSource<R, S> implements DataSource<S> {

    protected DataFetcher<R> dataFetcher;

    public BaseDataSource(DataFetcher<R> dataFetcher) {
        this.dataFetcher = dataFetcher;
    }

    @Override
    public S getData(IParameter extra, String... values) {
        return handleRequest(extra, values);
    }

    @Override
    public void close() {
        if (dataFetcher != null)
            dataFetcher.close();
    }

    public abstract S handleRequest(IParameter extra, String... values);

}
