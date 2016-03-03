package com.example.yjj.demoproj.test.dagger2;

import com.example.yjj.demoproj.AirCondition;
import com.example.yjj.demoproj.dagger2.RiceCooker;
import com.example.yjj.demoproj.dagger2.component.DaggerRiceCookerComponent;
import com.example.yjj.demoproj.dagger2.component.RiceCookerComponent;

import org.junit.Before;
import org.junit.Test;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */

public class RiceCookerTest {

    private RiceCookerComponent riceCookerComponent;

    @Test
    public void testRiceCooker() throws Exception {
        RiceCooker riceCooker = riceCookerComponent.cooker();
        riceCooker.cookRice();
    }

    @Test
    public void testAirCondition() throws Exception {

        AirCondition airCondition = riceCookerComponent.airCondition();
        airCondition.makeHotAir();
    }

    @Before
    public void setUp() throws Exception {
        riceCookerComponent = DaggerRiceCookerComponent
                .builder()
                .build();
    }
}
