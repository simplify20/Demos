package com.example.yjj.simple.framework.repository;

import com.example.yjj.simple.framework.Closeable;
import com.example.yjj.simple.framework.IParameter;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public interface Repository<D,T> extends Closeable {


    T getData(IParameter extra, String... values);

    void setCallback(DataCallback<D> callback);

    DataCallback<D> getCallback();
}
