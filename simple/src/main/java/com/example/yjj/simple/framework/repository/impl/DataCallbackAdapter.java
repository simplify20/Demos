package com.example.yjj.simple.framework.repository.impl;

import com.example.yjj.simple.framework.repository.DataCallback;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public class DataCallbackAdapter<T> implements DataCallback<T> {

    private DataCallback<T> actual;

    public DataCallbackAdapter(DataCallback<T> actual) {
        this.actual = actual;
    }

    public DataCallbackAdapter() {
    }

    @Override
    public void onSuccess(T t) {
        if (actual != null)
            actual.onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if (actual != null)
            actual.onError(e);
    }

    @Override
    public void onComplete() {
        if (actual != null)
            actual.onComplete();
    }
}
