package com.example.yjj.demoproj.test.dagger2;

import com.example.yjj.demoproj.dagger2.demo4.DaggerLazyInjectionComponent;
import com.example.yjj.demoproj.dagger2.demo4.LazyInjection;
import com.example.yjj.demoproj.dagger2.demo4.LazyInjectionComponent;

import org.junit.Before;
import org.junit.Test;

/**
 * try make User and LazyInjectionComponent  a singleton
 *
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
public class LazyTest {
    private LazyInjectionComponent lazyInjectionComponent;

    @Test
    public void testLazy() throws Exception {

        LazyInjection lazyInjection1 = lazyInjectionComponent.lazy();
        lazyInjection1.meeting();
        lazyInjection1.setNeedUser(true);
        lazyInjection1.meeting();
        lazyInjection1.meeting();

        //every time get a new LazyInjection and
        LazyInjection lazyInjection2 = lazyInjectionComponent.lazy();
        lazyInjection2.meeting();
        lazyInjection2.setNeedUser(true);
        lazyInjection2.meeting();
        lazyInjection2.meeting();

    }

    @Test
    public void testProvideInjection() throws Exception {

        LazyInjection lazyInjection = lazyInjectionComponent.lazy();
        lazyInjection.generateUsers(20);

    }

    @Before
    public void setUp() throws Exception {
        lazyInjectionComponent = DaggerLazyInjectionComponent
                .builder()
                .build();
    }
}
