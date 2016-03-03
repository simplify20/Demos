package com.example.yjj.demoproj.test.dagger2;

import com.example.yjj.demoproj.dagger2.demo5.CoffeeMaker;
import com.example.yjj.demoproj.dagger2.demo5.DaggerQualifierComponent;
import com.example.yjj.demoproj.dagger2.demo5.QualifierComponent;

import org.junit.Before;
import org.junit.Test;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
public class QualifierTest {
    private QualifierComponent qualifierComponent;

    @Test
    public void testQualifier() throws Exception {

        CoffeeMaker coffeeMaker = qualifierComponent.coffeMaker();
        coffeeMaker.make();

    }

    @Before
    public void setUp() throws Exception {

        qualifierComponent = DaggerQualifierComponent
                .builder()
                .build();

    }
}
