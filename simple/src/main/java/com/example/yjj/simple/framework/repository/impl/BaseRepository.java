package com.example.yjj.simple.framework.repository.impl;

import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.repository.DataCallback;
import com.example.yjj.simple.framework.repository.Repository;
import com.example.yjj.simple.framework.util.RepositoryHook;

/**
 * Repository的骨架实现，参数定义同Repository
 *
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public abstract class BaseRepository<C, S> implements Repository<C, S> {

    protected DataCallback<C> callback;
    private RepositoryHook repositoryHook = RepositoryHook.getInstance();

    public BaseRepository() {
        repositoryHook.onRepositoryConstruct(this);
    }

    @Override
    public void setCallback(DataCallback<C> callback) {
        this.callback = callback;
        repositoryHook.onRepositorySetCallback(this, this.callback);
    }

    @Override
    public void close() {
        repositoryHook.onRepositoryClose(this);
    }

    @Override
    public S getData(IParameter extra, String... values) {
        repositoryHook.onRepositoryGetData(this, extra, values);
        return null;
    }
}
