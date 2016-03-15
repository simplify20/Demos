package com.example.yjj.simple.framework.repository;

import com.example.yjj.simple.framework.Closeable;
import com.example.yjj.simple.framework.IParameter;

/**
 * C-回调接口的参数类型，一般为客户端需要的数据类型，为JavaBean或者JavaBean的集合
 * S-依赖者需要的返回类型
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public interface Repository<C,S> extends Closeable {


    S getData(IParameter extra, String... values);

    void setCallback(DataCallback<C> callback);
}
