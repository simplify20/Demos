package com.example.yjj.rxdemo.rxbind;

import junit.framework.TestCase;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.example.yjj.rxdemo.util.PrintUtil.println;


/**
 * @author:YJJ
 * @date:2015/12/30
 * @email:yangjianjun@117go.com
 */
public class RxApiTest extends TestCase {

    private static String TAG = RxApiTest.class.getSimpleName();

    public void testDefaultValue() throws Exception {
        Observable
                .just("Apples", "Bananas")
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        throw new RuntimeException("I don't like: " + s);
                    }
                })
                .onErrorReturn(new Func1<Throwable, String>() {
                    @Override
                    public String call(Throwable throwable) {
                        return "Default fruit";
                    }
                })
                .subscribeOn(Schedulers.immediate())
                .observeOn(Schedulers.immediate())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        println(s);
                    }
                });
    }
}
