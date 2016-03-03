package com.example.yjj.demoproj.dagger2.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * @author:YJJ
 * @date:2016/3/2
 * @email:yangjianjun@117go.com
 */
@Documented
@Target(ElementType.METHOD)
@MapKey(unwrapValue = true)
public @interface StringKey {
    String value();
}
