package com.example.yjj.rxdemo.rxbind;

import android.content.Context;
import android.graphics.Color;
import android.test.InstrumentationTestCase;
import android.test.UiThreadTest;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yjj.rxdemo.rxbind.util.TestAdapter;
import com.example.yjj.rxdemo.rxbind.util.UploadService;
import com.example.yjj.rxdemo.rxbind.util.UploadServiceImpl;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxAdapter;
import com.jakewharton.rxbinding.widget.RxProgressBar;
import com.jakewharton.rxbinding.widget.RxTextView;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

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
                .filter(testAdapter -> {
                    int size = testAdapter.getCount();
                    //filter insert data
                    return testAdapter.getData().get(size - 1).startsWith("s");
                })
                .subscribe(testAdapter -> {
                    //do something when dataChange
                    System.out.println(testAdapter.getData());
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
        ProgressBar view = new ProgressBar(context, null, 0);
        UploadService<Integer> uploadService = new UploadServiceImpl();
        uploadService.getUploadInfo("temp.jpg")
                .doOnNext((integer -> {
                    RxView.visibility(view).call(true);
                    System.out.println("upload " + integer + " bytes");
                }))
                .doOnCompleted(() -> RxView.visibility(view).call(false))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(RxProgressBar.incrementProgressBy(view));
    }

    @UiThreadTest
    public void testTextView() throws Exception {
        TextView textView = new TextView(context);

        //events
        textView.setText("init");
        Subscription subscription = RxTextView.textChangeEvents(textView).subscribe(System.out::println);
        textView.setText("H");
        textView.setText("Hit");
        subscription.unsubscribe();

        //txt
        textView.setText("init");
        Subscription subscription1 = RxTextView.textChanges(textView).subscribe(System.out::println);
        textView.setText("H");
        textView.setText("Hit");
        subscription1.unsubscribe();

        //change property
        Observable.just(Color.BLACK, Color.RED, Color.GRAY)
                .doOnNext(System.out::println)
                .subscribe(RxTextView.color(textView));
        Observable.just("black", "red", "gray")
                .doOnNext(System.out::println)
                .subscribe(RxTextView.text(textView));
    }
}
