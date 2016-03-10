package com.example.yjj.simple.framework.repository;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public interface DataCallback<T> {

    void onSuccess(T t);
    void onError(Throwable e);
    void onComplete();
}
