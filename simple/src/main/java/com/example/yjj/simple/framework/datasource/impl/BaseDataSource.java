package com.example.yjj.simple.framework.datasource.impl;

import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.datasource.DataSource;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public abstract class BaseDataSource<R, T> implements DataSource<T> {

    protected DataFetcher<R> dataFetcher;

    public BaseDataSource(DataFetcher<R> dataFetcher) {
        this.dataFetcher = dataFetcher;
    }

    @Override
    public T getData(IParameter parameter) {
        return convert(parameter);
    }

    @Override
    public void close() {
        dataFetcher.close();
    }

    public abstract T convert(IParameter requestParameter);
}
