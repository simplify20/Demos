package com.example.yjj.simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yjj.simple.presentation.view.SearchRepoActivity;

/**
 * @author:YJJ
 * @date:2016/3/11
 * @email:yangjianjun@117go.com
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void repo(View v) {
        Intent intent = new Intent(this, SearchRepoActivity.class);
        startActivity(intent);
    }
}
