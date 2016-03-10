package com.example.yjj.simple.data.datasource;

import com.example.yjj.simple.framework.datasource.DataFetcher;

import retrofit2.Call;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public abstract class RetrofitDataFetcher<T> implements DataFetcher<T> {
    protected Call<T> call;

    public RetrofitDataFetcher(Call<T> call) {
        this.call = call;
    }

    public RetrofitDataFetcher() {
    }

    public Call<T> getCall() {
        return call;
    }

    public void setCall(Call<T> call) {
        this.call = call;
    }

    @Override
    public void close() {
        call.cancel();
    }
}
