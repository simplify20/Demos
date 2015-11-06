package com.example.yjj.rxdemo.rxbind;

import android.content.Context;
import android.graphics.Color;
import android.test.InstrumentationTestCase;
import android.test.UiThreadTest;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yjj.rxdemo.rxbind.interactor.UploadService;
import com.example.yjj.rxdemo.rxbind.interactor.UploadServiceImpl;
import com.example.yjj.rxdemo.rxbind.util.TestAdapter;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxAdapter;
import com.jakewharton.rxbinding.widget.RxProgressBar;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

import static com.example.yjj.rxdemo.util.PrintUtil.println;

/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */
public class RxBindWidgetTest extends InstrumentationTestCase {
    TestAdapter adapter;
    Context context;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        context = getInstrumentation().getContext();
        adapter = new TestAdapter(context);

    }

    @UiThreadTest
    public void testDataChanges() {
        Subscription subscription = RxAdapter.dataChanges(adapter)
                .filter(new Func1<TestAdapter, Boolean>() {
                    @Override
                    public Boolean call(TestAdapter testAdapter) {
                        int size = testAdapter.getCount();
                        //filter insert data
                        return testAdapter.getData().get(size - 1).startsWith("s");
                    }
                })
                .subscribe(new Action1<TestAdapter>() {
                    @Override
                    public void call(TestAdapter testAdapter) {
                        //do something when dataChange
                        println(testAdapter.getData());
                    }
                });

        adapter.add("sss");
        adapter.notifyDataSetChanged();
        adapter.add("svvv");
        adapter.notifyDataSetChanged();
        adapter.add("fsdf");
        adapter.notifyDataSetChanged();
        subscription.unsubscribe();
        adapter.add("ssdf");
        adapter.notifyDataSetChanged();

    }

    public void testProgressBar() throws Exception {
        final ProgressBar view = new ProgressBar(context, null, 0);
        UploadService<Integer> uploadService = new UploadServiceImpl();
        uploadService.getUploadInfo("temp.jpg")
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        RxView.visibility(view).call(true);
                        System.out.println("upload " + integer + " bytes");
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        RxView.visibility(view).call(false);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        RxProgressBar.incrementProgressBy(view).call(integer);
                    }
                });
    }

    @UiThreadTest
    public void testTextView() throws Exception {
        TextView textView = new TextView(context);

        //events
        textView.setText("init");
        Subscription subscription = RxTextView.textChangeEvents(textView).subscribe(new Action1<TextViewTextChangeEvent>() {
            @Override
            public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                println(textViewTextChangeEvent);
            }
        });
        textView.setText("H");
        textView.setText("Hit");
        subscription.unsubscribe();

        //txt
        textView.setText("init");
        Subscription subscription1 = RxTextView.textChanges(textView).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                println(charSequence);
            }
        });
        textView.setText("H");
        textView.setText("Hit");
        subscription1.unsubscribe();

        //change property
        Observable.just(Color.BLACK, Color.RED, Color.GRAY)
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        println(integer);
                    }
                })
                .subscribe(RxTextView.color(textView));
        Observable.just("black", "red", "gray")
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        println(s);
                    }
                })
                .subscribe(RxTextView.text(textView));
    }
}
