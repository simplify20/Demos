package com.example.yjj.rxdemo.rxbind.v4_test;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import com.example.yjj.rxdemo.R;
import com.example.yjj.rxdemo.rxbind.RxBindActivity;
import com.jakewharton.rxbinding.support.v4.view.RxViewPager;

import rx.functions.Action1;

import static com.example.yjj.rxdemo.util.PrintUtil.println;

/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */
public class RxViewPagerTest extends ActivityInstrumentationTestCase2<RxBindActivity> {
    Activity activity;
    private ViewPager viewPager;

    public RxViewPagerTest() {
        super(RxBindActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        viewPager = (ViewPager) activity.findViewById(R.id.view_pager);
    }

    @UiThreadTest
    public void testPageSelections() throws Exception {

        RxViewPager.pageSelections(viewPager)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        println(integer);
                    }
                });
        viewPager.setCurrentItem(2);
    }
}
