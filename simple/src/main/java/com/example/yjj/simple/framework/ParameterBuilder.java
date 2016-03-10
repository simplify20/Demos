package com.example.yjj.simple.framework;

import com.example.yjj.simple.framework.IParameter;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public interface ParameterBuilder {

    IParameter buildParameter(IParameter extra,String... values);
}
