package com.example.yjj.demoproj;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.yjj.demoproj.view.RectangleProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author:YJJ
 * @date:2016/2/3
 * @email:yangjianjun@117go.com
 */
public class TranslucentBarActivity extends BaseActivity implements Handler.Callback {

    @Bind(R.id.progress_bar)
    RectangleProgressBar progressBar;
    private int progress;
    private Handler handler = new Handler(this);
    private Timer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(0x66000000);//半透明
            getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_translucent);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x23);
            }
        }, 0, 500);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == 0x23) {
            progress += 10;
            progressBar.setProgress(progress);
            if (progress == 100) {
                timer.cancel();
            }
            return true;
        }
        return false;
    }
}
