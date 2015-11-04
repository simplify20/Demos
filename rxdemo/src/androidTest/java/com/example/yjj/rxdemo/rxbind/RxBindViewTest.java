package com.example.yjj.rxdemo.rxbind;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.test.InstrumentationTestCase;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;
import static com.example.yjj.rxdemo.rxbind.util.MotionEventUtil.motionEventAtPosition;


/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */
public class RxBindViewTest extends InstrumentationTestCase {

    View view;
    Context context;

    public RxBindViewTest() {
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        context = getInstrumentation().getContext();
        view = new View(context);
    }

    @UiThreadTest
    @SmallTest
    public void testClicks() throws Exception {
        RxView.clicks(view).subscribe(aVoid -> System.out.println(view + " is clicked"));
        view.performClick();
        view.performClick();
    }

    @SmallTest
    @UiThreadTest
    public void testTouches() {
        Subscription subscription = RxView.touches(view)
                .filter(motionEvent -> motionEvent.getAction() == ACTION_DOWN)
                .subscribe(System.out::println);

        view.dispatchTouchEvent(motionEventAtPosition(view, ACTION_DOWN, 0, 50));
        view.dispatchTouchEvent(motionEventAtPosition(view, ACTION_MOVE, 1, 50));
        view.dispatchTouchEvent(motionEventAtPosition(view, ACTION_DOWN, 0, 50));

        view.dispatchTouchEvent(motionEventAtPosition(view, ACTION_MOVE, 1, 50));

        subscription.unsubscribe();

        view.dispatchTouchEvent(motionEventAtPosition(view, ACTION_UP, 1, 50));
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @UiThreadTest
    @SmallTest
    public void testDraws() throws Exception {

        RxView.draws(view).subscribe(aVoid -> System.out.println(view + " is draw"));
        view.getViewTreeObserver().dispatchOnDraw();
    }

    @UiThreadTest
    @SmallTest
    public void testFocusChanges() throws Exception {
        LinearLayout parent = new LinearLayout(context);
        parent.setFocusable(true);
        parent.addView(view);

        view.setFocusable(true);

        Subscription subscription = RxView.focusChanges(view).subscribe(System.out::println);

        view.requestFocus();//output:true
        view.clearFocus();//output:false
        subscription.unsubscribe();
        view.requestFocus();//output:
    }

    @UiThreadTest
    @SmallTest
    public void testGlobalLayouts() throws Exception {
        Subscription subscription = RxView.globalLayouts(view).subscribe(aVoid -> System.out.println("global layout"));

        view.getViewTreeObserver().dispatchOnGlobalLayout();
        subscription.unsubscribe();
        view.getViewTreeObserver().dispatchOnGlobalLayout();
    }

    @SmallTest
    @UiThreadTest
    public void testLayoutChanges() {
        Subscription subscription = RxView.layoutChanges(view).subscribe(aVoid -> System.out.println("layout changes"));

        view.layout(view.getLeft() - 5, view.getTop() - 5, view.getRight(), view.getBottom());

        view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());

        subscription.unsubscribe();
        view.layout(view.getLeft() - 5, view.getTop() - 5, view.getRight(), view.getBottom());
    }

    public void testGroupBy() throws Exception {

        Observable.just(1, 7, 2, 2, 3, 4, 5)
                .groupBy(new Func1<Integer, Object>() {
                    @Override
                    public Object call(Integer integer) {
                        if (integer < 3)
                            return "<3";
                        else
                            return ">=3";
                    }
                })
                .subscribe(new Action1<GroupedObservable<Object, Integer>>() {
                    @Override
                    public void call(GroupedObservable<Object, Integer> groupedObservable) {
                        groupedObservable.subscribe(integer -> System.out.println(groupedObservable.getKey() + ":" + integer));
                    }
                });

    }
}
