package com.example.yjj.demoproj.test.rxjava;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observers.SafeSubscriber;
import rx.plugins.RxJavaObservableExecutionHook;
import rx.plugins.RxJavaPlugins;

/**
 * @author:YJJ
 * @date:2016/3/7
 * @email:yangjianjun@117go.com
 */
public class RxTest {
    CountDownLatch countDownLatch;

    @Before
    public void setUp() throws Exception {
        countDownLatch = new CountDownLatch(1);
        RxJavaPlugins.getInstance().registerObservableExecutionHook(new ExcHook());
    }

    @Test
    public void testHook() throws Exception {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello ,rx");
                subscriber.onCompleted();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
    }

    /**
     * See if new Observable refer to the source Observable
     * @throws Exception
     */
    @Test
    public void testLift1() throws Exception {

        Observable<Integer> source = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(23);
                subscriber.onCompleted();
            }
        });
        Observable<String> mapped = source.lift(new MyOperator_Integer2String());
        Field onSub = source.getClass().getDeclaredField("onSubscribe");
        onSub.setAccessible(true);
        onSub.set(source,null);

        mapped.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                countDownLatch.countDown();
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
        countDownLatch.await();

    }

    /**
     * see how lift() works
     * @throws Exception
     */
    @Test
    public void testLift() throws Exception {
        MyOperator_String2Integer Op1 = new MyOperator_String2Integer();
        MyOperator_Integer2String Op2 = new MyOperator_Integer2String();
        MyOperator_String2Integer Op3 = new MyOperator_String2Integer();
        System.out.println("op1:" + Op1.toString() + "\nop2:" + Op2.toString() + "\nop3:" + Op3.toString());
        Observable.just("5")
                .lift(Op1)
                .lift(Op2)
                .lift(Op3)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("finally call,integer:" + integer);
                        countDownLatch.countDown();
                    }
                });
        countDownLatch.await();
    }

    @Test
    public void testCompose() throws Exception {

        Observable.just("11233")
                .compose(new MyTransformer())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onError(Throwable e) {
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer+1);
                    }
                });
        countDownLatch.await();

    }

    /**
     * 回调的嵌套，每次call,都会向前回溯
     */
    private class MyOperator_String2Integer implements Observable.Operator<Integer, String> {

        @Override
        public Subscriber<? super String> call(final Subscriber<? super Integer> subscriber) {
            return new SafeSubscriber<>(new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    subscriber.onCompleted();
                }

                @Override
                public void onError(Throwable e) {
                    subscriber.onError(e);
                }

                @Override
                public void onNext(String s) {
                    System.out.println(MyOperator_String2Integer.this.toString() + "--onNext--str to int:" + s);
                    subscriber.onNext(Integer.valueOf(s) - 1);
                }
            });
        }
    }

    /**
     * 回调的嵌套，每次call,都会向前回溯
     */
    private class MyOperator_Integer2String implements Observable.Operator<String, Integer> {


        @Override
        public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {
            return new SafeSubscriber<>(new Subscriber<Integer>() {
                @Override
                public void onCompleted() {
                    subscriber.onCompleted();
                }

                @Override
                public void onError(Throwable e) {
                    subscriber.onError(e);
                }

                @Override
                public void onNext(Integer integer) {
                    System.out.println(MyOperator_Integer2String.this.toString() + "---onNext---int to str:" + integer);
                    subscriber.onNext(String.valueOf(integer - 1));
                }
            });
        }
    }

    private class MyTransformer implements Observable.Transformer<String,Integer>{

        @Override
        public Observable<Integer> call(Observable<String> stringObservable) {
            return stringObservable.map(new Func1<String, Integer>() {
                @Override
                public Integer call(String s) {
                    return Integer.valueOf(s);
                }
            });
        }
    }

    /**
     * use custom ObservableExecutionHook
     */
    private class ExcHook extends RxJavaObservableExecutionHook {
        @Override
        public <T, R> Observable.Operator<? extends R, ? super T> onLift(Observable.Operator<? extends R, ? super T> lift) {
            System.out.println("TestHook:onLift-" + lift.toString());
            return super.onLift(lift);
        }

        @Override
        public <T> Observable.OnSubscribe<T> onSubscribeStart(Observable<? extends T> observableInstance, Observable.OnSubscribe<T> onSubscribe) {
            System.out.println("TestHook:onSubscribeStart-" + onSubscribe.toString());
            return super.onSubscribeStart(observableInstance, onSubscribe);
        }

    }

}
