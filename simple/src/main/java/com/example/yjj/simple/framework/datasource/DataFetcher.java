package com.example.yjj.simple.framework.datasource;

import com.example.yjj.simple.framework.Closeable;
import com.example.yjj.simple.framework.IParameter;
import com.example.yjj.simple.framework.ParameterFactory;

/**
 * R-DataFetcher从DB/network/File System 获得的原始数据类型
 * P-Parameter Type
 *
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public interface DataFetcher<R> extends Closeable, ParameterFactory {

    R fetchData(IParameter extra, String... values) throws Exception;
}
