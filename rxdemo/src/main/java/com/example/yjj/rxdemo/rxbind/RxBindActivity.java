package com.example.yjj.rxdemo.rxbind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yjj.rxdemo.R;
import com.example.yjj.rxdemo.lifecycle.RxLifecycleActivity;
import com.example.yjj.rxdemo.util.ToastUtil;
import com.jakewharton.rxbinding.support.v4.view.RxViewPager;
import com.jakewharton.rxbinding.support.v4.widget.RxDrawerLayout;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

import static android.view.Gravity.RIGHT;

/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */
public class RxBindActivity extends AppCompatActivity {
    private TextView textView;
    private Button btn;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private RelativeLayout drawerContainer;
    private static int i;
    private boolean drawerInit = true;
    private boolean txtInit = true;
    private boolean pagerInit = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bind);
        textView = (TextView) findViewById(R.id.text_view);
        btn = (Button) findViewById(R.id.btn);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerContainer = (RelativeLayout) findViewById(R.id.drawer_container);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        //view pager
        RxViewPager.pageSelections(viewPager)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer == viewPager.getAdapter().getCount() - 1 || integer == 0;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        if (pagerInit) {
                            pagerInit = false;
                            return;
                        }
                        ToastUtil.toastShortMsg(integer == 0 ? "已到第一张" : "已是最后一张");
                    }
                });
        //drawer layout
        RxDrawerLayout.drawerOpen(drawerLayout, RIGHT)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (drawerInit) {
                            drawerInit = false;
                            return;
                        }
                        ToastUtil.toastShortMsg(aBoolean ? "drawer opened" : "drawer closed");
                    }
                });
        //text view
        RxTextView.textChanges(textView)
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        if (txtInit) {
                            txtInit = false;
                            return;
                        }
                        ToastUtil.toastShortMsg("text changed new text is " + charSequence);
                    }
                });

        RxView.clicks(btn).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                i++;
                textView.setText("hello,bob" + i);
            }
        });

    }

    public void lifeCycleTest(View v) {
        Intent intent = new Intent(this, RxLifecycleActivity.class);
        startActivity(intent);
    }

    public void swipeRefreshTest(View v) {
        Intent intent = new Intent(this, RxSwipeRefreshLayoutTestActivity.class);
        startActivity(intent);
    }

    class PageAdapter extends FragmentStatePagerAdapter {
        String[] names = {"page1", "page2", "page3"};

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PagerFragment.instance(names[position]);
        }

        @Override
        public int getCount() {
            return names.length;
        }
    }
}
