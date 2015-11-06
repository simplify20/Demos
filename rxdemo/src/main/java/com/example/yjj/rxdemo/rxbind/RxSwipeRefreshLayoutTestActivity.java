package com.example.yjj.rxdemo.rxbind;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.example.yjj.rxdemo.rxbind.interactor.LoadMoreService;
import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * @author:YJJ
 * @date:2015/11/5
 * @email:yangjianjun@117go.com
 */
public class RxSwipeRefreshLayoutTestActivity extends AppCompatActivity {
    public SwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreService<String> loadMoreService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setContentView(swipeRefreshLayout);

        loadMoreService = new LoadMoreService<String>() {
            @Override
            public Observable<String> loadMore() {
                return Observable.just("1223", "243234", "3424");
            }
        };

        final Action1<List<String>> doMore = new Action1<List<String>>() {
            @Override
            public void call(List<String> list) {
                swipeRefreshLayout.setRefreshing(false);
                System.out.println(list);
            }
        };
        RxSwipeRefreshLayout.refreshes(swipeRefreshLayout)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        loadMoreService.loadMore()
                                .toList()
                                .delay(3, TimeUnit.SECONDS)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(doMore);
                    }
                });
    }

    private void initView() {
        swipeRefreshLayout = new SwipeRefreshLayout(this);

        ScrollView scrollView = new ScrollView(this);
        ViewGroup.LayoutParams scrollParams = new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        swipeRefreshLayout.addView(scrollView, scrollParams);

        FrameLayout emptyView = new FrameLayout(this);
        ViewGroup.LayoutParams emptyParams = new ViewGroup.LayoutParams(MATCH_PARENT, 100000);
        scrollView.addView(emptyView, emptyParams);
    }
}
