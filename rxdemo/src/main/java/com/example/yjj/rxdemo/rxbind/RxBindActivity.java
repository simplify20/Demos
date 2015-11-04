package com.example.yjj.rxdemo.rxbind;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yjj.rxdemo.R;
import com.example.yjj.rxdemo.util.ToastUtil;
import com.jakewharton.rxbinding.support.v4.view.RxViewPager;
import com.jakewharton.rxbinding.support.v4.widget.RxDrawerLayout;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import rx.android.schedulers.AndroidSchedulers;

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
        RxViewPager.pageSelections(viewPager)
                .filter(integer -> integer == viewPager.getAdapter().getCount() - 1 || integer == 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer1 -> ToastUtil.toastShortMsg(integer1 == 0 ? "已到第一张" : "已是最后一张"));

        RxDrawerLayout.drawerOpen(drawerLayout, RIGHT)
                .subscribe(aBoolean -> System.out.println(aBoolean ? "opened" : "closed"));

        RxTextView.textChanges(textView)
                .subscribe(System.out::println);

        RxView.clicks(btn).subscribe(aVoid -> textView.setText("hello,bob"));

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
