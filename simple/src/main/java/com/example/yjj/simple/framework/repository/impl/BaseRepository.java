package com.example.yjj.simple.framework.repository.impl;

import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.repository.DataCallback;
import com.example.yjj.simple.framework.repository.Repository;

/**
 * Repository的骨架实现，参数定义同Repository
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public class BaseRepository<C, S> implements Repository<C, S> {

    protected DataCallback<C> callback;

    @Override
    public S getData(IParameter extra, String... values) {
        return null;
    }

    @Override
    public void setCallback(DataCallback<C> callback) {
        this.callback = callback;
    }

    @Override
    public void close() {
    }
}
