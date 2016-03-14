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

    @Override
    public IParameter buildParameter(IParameter extra, String... values) {
        RequestParameter requestParameter;
        if (extra != null && extra instanceof RequestParameter) {
            requestParameter = (RequestParameter) extra;
        } else {
            requestParameter = new RequestParameter();
        }
        if (values == null || values.length == 0)
            return requestParameter;
        return requestParameter;
    }

    public abstract T convert(IParameter requestParameter);


}
