package com.example.yjj.rxdemo.rxbind.v7_test;

import android.app.Activity;
import android.support.v7.widget.SearchView;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import com.example.yjj.rxdemo.R;
import com.example.yjj.rxdemo.rxbind.RxBindActivity;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;

/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */
public class RxSearchViewTest extends ActivityInstrumentationTestCase2<RxBindActivity> {
    Activity activity;
    private SearchView searchView;

    public RxSearchViewTest() {
        super(RxBindActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        searchView = (SearchView) activity.findViewById(R.id.search_view);
    }

    @UiThreadTest
    public void testQueryTextChanges() throws Exception {

        RxSearchView.queryTextChanges(searchView)
                .subscribe(System.out::println);
        searchView.setQuery("h", false);
        searchView.setQuery("hi", false);
        searchView.setQuery("hitt", false);

    }

    @UiThreadTest
    public void testQueryTextEventNotSubmitted() throws Exception {

        RxSearchView.queryTextChangeEvents(searchView)
                .subscribe(System.out::println);

        searchView.setQuery("haha", false);//set submit be true to see 2 events(txt change and submit event) has been emitted

    }
}
