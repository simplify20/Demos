package com.example.yjj.rxdemo.rxbind.v4_test;

import android.app.Instrumentation;
import android.support.v4.widget.SwipeRefreshLayout;
import android.test.ActivityInstrumentationTestCase2;

import com.example.yjj.rxdemo.rxbind.RxSwipeRefreshLayoutTestActivity;
import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;
import static com.example.yjj.rxdemo.rxbind.util.MotionEventUtil.motionEventAtPosition;
import static com.example.yjj.rxdemo.util.PrintUtil.println;

/**
 * @author:YJJ
 * @date:2015/11/5
 * @email:yangjianjun@117go.com
 */
public class RxRefreshLayoutTest extends ActivityInstrumentationTestCase2<RxSwipeRefreshLayoutTestActivity> {
    SwipeRefreshLayout swipeRefreshLayout;
    Instrumentation instrumentation;

    public RxRefreshLayoutTest() {
        super(RxSwipeRefreshLayoutTestActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        swipeRefreshLayout = getActivity().swipeRefreshLayout;
        instrumentation = getInstrumentation();
    }

    public void testRefresh() throws Exception {

        RxSwipeRefreshLayout.refreshes(swipeRefreshLayout)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        println("do refreshing...");
                    }
                });
        doRefreshGesture();
    }

    private void doRefreshGesture() {
        instrumentation.sendPointerSync(motionEventAtPosition(swipeRefreshLayout, ACTION_DOWN, 50, 0));
        instrumentation.sendPointerSync(motionEventAtPosition(swipeRefreshLayout, ACTION_MOVE, 50, 100));
        instrumentation.sendPointerSync(motionEventAtPosition(swipeRefreshLayout, ACTION_UP, 50, 100));
    }
}
