package com.example.yjj.simple.framework.datasource;

import com.example.yjj.simple.framework.Closeable;
import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.ParameterBuilder;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public interface DataSource<T> extends Closeable, ParameterBuilder {
    T getData(IParameter parameter);
}
