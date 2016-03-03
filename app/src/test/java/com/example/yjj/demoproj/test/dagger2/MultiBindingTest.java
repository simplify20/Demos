package com.example.yjj.demoproj.test.dagger2;

import com.example.yjj.demoproj.dagger2.multibinding.AbstractModel;
import com.example.yjj.demoproj.dagger2.multibinding.DaggerYangComponent;
import com.example.yjj.demoproj.dagger2.multibinding.YangComponent;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * @author:YJJ
 * @date:2016/3/2
 * @email:yangjianjun@117go.com
 */
public class MultiBindingTest {
    private YangComponent yangComponent;

    @Test
    public void testMultiBinding() throws Exception {
        Map<String, AbstractModel> modelMap = yangComponent.getModels();
        for (Map.Entry<String, AbstractModel> entry : modelMap.entrySet()) {
            entry.getValue().action(null);
        }
    }

    @Before
    public void setUp() throws Exception {
        yangComponent = DaggerYangComponent.builder().build();
    }
}
