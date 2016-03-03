package com.example.yjj.demoproj.test.dagger2;

import com.example.yjj.demoproj.dagger2.demo3.DaggerSingletonComponent;
import com.example.yjj.demoproj.dagger2.demo3.SingletonComponent;
import com.example.yjj.demoproj.dagger2.demo3.SubComponent;

import org.junit.Test;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
public class SubComponentTest {
    private static SingletonComponent singletonComponent = DaggerSingletonComponent
            .builder()
            .build();
    @Test
    public void testSub() throws Exception {

        SubComponent subComponent = singletonComponent.subcomponent();

    }
}
