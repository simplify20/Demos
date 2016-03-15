package com.example.yjj.simple.framework.datasource;

import com.example.yjj.simple.framework.Closeable;
import com.example.yjj.simple.framework.IParameter;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public interface DataSource<S> extends Closeable{
    S getData(IParameter extra, String... values);
}
