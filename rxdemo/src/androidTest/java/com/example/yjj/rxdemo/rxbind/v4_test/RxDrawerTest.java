package com.example.yjj.rxdemo.rxbind.v4_test;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.v4.widget.DrawerLayout;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.RelativeLayout;

import com.example.yjj.rxdemo.R;
import com.example.yjj.rxdemo.rxbind.RxBindActivity;
import com.jakewharton.rxbinding.support.v4.widget.RxDrawerLayout;

import rx.functions.Action1;

import static android.view.Gravity.RIGHT;
import static com.example.yjj.rxdemo.util.PrintUtil.println;

/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */
public class RxDrawerTest extends ActivityInstrumentationTestCase2<RxBindActivity> {
    Activity activity;
    private DrawerLayout drawerLayout;
    private RelativeLayout drawerC;
    private Instrumentation instrumentation;

    public RxDrawerTest() {
        super(RxBindActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer);
        drawerC = (RelativeLayout) activity.findViewById(R.id.drawer_container);
        instrumentation = getInstrumentation();
    }

    //can't work ,see RxBindingActivity.java
    @UiThreadTest
    public void testDrawerOpen() throws Exception {

        RxDrawerLayout.drawerOpen(drawerLayout, RIGHT)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        println(aBoolean ? "opened" : "closed");
                    }
                });
        drawerLayout.openDrawer(RIGHT);
        drawerLayout.closeDrawer(RIGHT);
    }
}
