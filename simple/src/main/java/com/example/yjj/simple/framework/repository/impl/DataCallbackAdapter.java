package com.example.yjj.simple.framework.repository.impl;

import com.example.yjj.simple.framework.repository.DataCallback;

/**
 * 参数的定义同DataCallback
 * 提供了代理的可能
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public class DataCallbackAdapter<C> implements DataCallback<C> {

    private DataCallback<C> actual;

    public DataCallbackAdapter(DataCallback<C> actual) {
        this.actual = actual;
    }

    public DataCallbackAdapter() {
    }

    @Override
    public void onSuccess(C t) {
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
