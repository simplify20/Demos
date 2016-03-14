package com.example.yjj.simple.framework.repository.impl;

import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.repository.DataCallback;
import com.example.yjj.simple.framework.repository.Repository;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public class BaseRepository<D, T> implements Repository<D, T> {

    protected DataCallback<D> callback;

    @Override
    public T getData(IParameter extra, String... values) {
        return null;
    }

    @Override
    public void setCallback(DataCallback<D> callback) {
        this.callback = callback;
    }

    @Override
    public DataCallback<D> getCallback() {
        return callback;
    }

    @Override
    public void close() {
    }
}
