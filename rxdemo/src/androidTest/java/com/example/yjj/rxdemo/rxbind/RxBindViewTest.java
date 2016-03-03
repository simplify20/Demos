package com.example.yjj.rxdemo.rxbind;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.test.InstrumentationTestCase;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.yjj.rxdemo.rxbind.bean.User;
import com.example.yjj.rxdemo.rxbind.interactor.GetWordsService;
import com.example.yjj.rxdemo.rxbind.interactor.LoginService;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.view.ViewLayoutChangeEvent;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;
import static com.example.yjj.rxdemo.rxbind.util.MotionEventUtil.motionEventAtPosition;
import static com.example.yjj.rxdemo.util.PrintUtil.println;


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
        RxView.clicks(view).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                println(view + " is clicked");
            }
        });
        view.performClick();
        view.performClick();
    }

    @SmallTest
    @UiThreadTest
    public void testTouches() {
        Subscription subscription = RxView.touches(view)
                .filter(new Func1<MotionEvent, Boolean>() {
                    @Override
                    public Boolean call(MotionEvent motionEvent) {
                        return motionEvent.getAction() == ACTION_DOWN;
                    }
                })
                .subscribe(new Action1<MotionEvent>() {
                    @Override
                    public void call(MotionEvent motionEvent) {
                        println(motionEvent);
                    }
                });

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

        RxView.draws(view).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                println(view + " is draw");
            }
        });
        view.getViewTreeObserver().dispatchOnDraw();
    }

    @UiThreadTest
    @SmallTest
    public void testFocusChanges() throws Exception {
        LinearLayout parent = new LinearLayout(context);
        parent.setFocusable(true);
        parent.addView(view);

        view.setFocusable(true);

        Subscription subscription = RxView.focusChanges(view).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean hasFocused) {
                println(hasFocused);
            }
        });

        view.requestFocus();//output:true
        view.clearFocus();//output:false
        subscription.unsubscribe();
        view.requestFocus();//output:
    }

    @UiThreadTest
    @SmallTest
    public void testGlobalLayouts() throws Exception {
        Subscription subscription = RxView.globalLayouts(view).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                println("global layout");
            }
        });

        view.getViewTreeObserver().dispatchOnGlobalLayout();
        subscription.unsubscribe();
        view.getViewTreeObserver().dispatchOnGlobalLayout();
    }

    @SmallTest
    @UiThreadTest
    public void testLayoutChanges() {
        //no event
        Subscription subscription = RxView.layoutChanges(view).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                println("layout changes");
            }
        });
        //trigger layout change
        view.layout(view.getLeft() - 5, view.getTop() - 5, view.getRight(), view.getBottom());
        view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        subscription.unsubscribe();
        view.layout(view.getLeft() - 5, view.getTop() - 5, view.getRight(), view.getBottom());

        //event
        RxView.layoutChangeEvents(view).subscribe(new Action1<ViewLayoutChangeEvent>() {
            @Override
            public void call(ViewLayoutChangeEvent viewLayoutChangeEvent) {
                println(viewLayoutChangeEvent);
            }
        });
        //trigger layout change
        view.layout(view.getLeft() - 5, view.getTop() - 5, view.getRight(), view.getBottom());
    }


    @SmallTest
    @UiThreadTest
    public void testVisibility() throws Exception {

        GetWordsService service = new GetWordsService() {
            @Override
            public Observable<String> getWords() {
                return Observable.just("1131334");
            }
        };

        service.getWords()
                .map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !TextUtils.isEmpty(s);
                    }
                })
                .subscribe(RxView.visibility(view));
        assertEquals(View.VISIBLE, view.getVisibility());
    }

    @UiThreadTest
    @SmallTest
    public void testEnabled() throws Exception {
        final Button loginBtn = new Button(context);
        final LoginService<User> loginService = new LoginService<User>() {
            @Override
            public Observable<User> login(String account, String psd) {
                return Observable.just(new User("yangjianjun", 23));
            }
        };
        RxView.clicks(loginBtn)
                .doOnNext(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        loginService.login("1234456", "ssddsds");
                    }
                })
                .map(new Func1<Void, Boolean>() {
                    @Override
                    public Boolean call(Void aVoid) {
                        return false;
                    }
                })
                .subscribe(RxView.enabled(loginBtn));
        loginBtn.performClick();
        assertEquals(false, loginBtn.isEnabled());

    }


    public void testGroupBy() throws Exception {

        Observable.just(1, 7, 2, 2, 3, 4, 5)
                .groupBy(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        if (integer < 3)
                            return "<3";
                        else
                            return ">=3";
                    }
                })
                .subscribe(new Action1<GroupedObservable<String, Integer>>() {
                    @Override
                    public void call(final GroupedObservable<String, Integer> groupedObservable) {
                        groupedObservable.toList().subscribe(new Action1<List<Integer>>() {
                            @Override
                            public void call(List<Integer> integers) {
                                System.out.println(groupedObservable.getKey() + ":" + integers);
                            }
                        });
                    }
                });

    }
}
