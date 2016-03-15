package com.example.yjj.simple;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.yjj.simple.data.di.common.component.DaggerProductionComponent;
import com.example.yjj.simple.databinding.ActivityMainBinding;
import com.example.yjj.simple.presentation.view.SearchRepoActivity;
import com.example.yjj.simple.presentation.view.common.BaseActivity;

/**
 * @author:YJJ
 * @date:2016/3/11
 * @email:yangjianjun@117go.com
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set binding component
        DataBindingUtil.setDefaultComponent(DaggerProductionComponent.builder().build());
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setMainPage(new MainPageData("http://i.imgur.com/DvpvklR.png"));
    }

    public void repo(View v) {
        Intent intent = new Intent(this, SearchRepoActivity.class);
        startActivity(intent);
    }

    public static class MainPageData{
        public String imgUrl;

        public MainPageData(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
