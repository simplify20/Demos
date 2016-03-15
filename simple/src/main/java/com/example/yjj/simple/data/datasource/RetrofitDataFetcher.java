package com.example.yjj.simple.data.datasource;

import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.ParameterFactory;
import com.example.yjj.simple.framework.datasource.DataFetcher;
import com.example.yjj.simple.framework.datasource.impl.RequestParameter;

import retrofit2.Call;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public abstract class RetrofitDataFetcher<R> implements DataFetcher<R> {
    protected Call<R> call;
    protected ParameterFactory parameterFactory;

    public RetrofitDataFetcher(ParameterFactory parameterFactory) {
        this.parameterFactory = parameterFactory;
    }

    public RetrofitDataFetcher(Call<R> call) {
        this.call = call;
        this.parameterFactory = this;
    }

    public RetrofitDataFetcher() {
        this.parameterFactory = this;
    }

    public Call<R> getCall() {
        return call;
    }

    public void setCall(Call<R> call) {
        this.call = call;
    }

    @Override
    public R fetchData(IParameter extra, String... values) throws Exception {
        return fetchDataImpl((RequestParameter) parameterFactory.create(extra, values));
    }

    /**
     * @param extra
     * @param values
     * @return
     */
    @Override
    public RequestParameter create(IParameter extra, String... values) {
        RequestParameter requestParameter;
        if (extra != null) {
            requestParameter = (RequestParameter) extra;
        } else {
            requestParameter = new RequestParameter();
        }
        putValues(requestParameter, values);
        return requestParameter;
    }

    @Override
    public void close() {
        if (call != null)
            call.cancel();
    }

    public abstract R fetchDataImpl(RequestParameter requestParameter) throws Exception;

    public abstract void putValues(RequestParameter parameter, String... values);
}
