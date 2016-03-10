package com.example.yjj.simple.framework.datasource;

import com.example.yjj.simple.framework.Closeable;
import com.example.yjj.simple.framework.datasource.impl.RequestParameter;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public interface DataFetcher<T> extends Closeable {

    T fetchData(RequestParameter parameter) throws Exception;
}
