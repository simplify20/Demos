package com.example.yjj.demoproj.butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Property;
import android.view.View;
import android.widget.Button;

import com.example.yjj.demoproj.R;
import com.example.yjj.demoproj.util.ToastUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author:YJJ
 * @date:2015/11/5
 * @email:yangjianjun@117go.com
 */
public class DemoActivity extends AppCompatActivity {
    //group
    @Bind({R.id.btn1, R.id.btn2, R.id.btn4})
    List<Button> btns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife_demo);
        //must call this to ensure event's binding
        ButterKnife.bind(this);
        for (Button btn : btns) {
            System.out.println(btn);
        }

    }

    /**
     * 事件绑定
     *
     * @param v
     */
    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btn1:
                ToastUtil.toastShortMsg("btn1 clicked");
                break;
            case R.id.btn2:
                ToastUtil.toastShortMsg("btn2 clicked");
                break;
            case R.id.btn3:
                //action
//                ButterKnife.apply(btns, new ButterKnife.Action<Button>() {
//                    @Override
//                    public void apply(Button view, int index) {
//                        view.setEnabled(false);
//                    }
//                });
                //property
//                ButterKnife.apply(btns, View.ALPHA, 0.0f);
                ButterKnife.apply(btns, new Property<Button, Float>(Float.class, "x") {
                    @Override
                    public Float get(Button object) {
                        return object.getX();
                    }

                    @Override
                    public void set(Button object, Float value) {
                        object.setX(get(object) + value);
                    }
                }, 12f);

                //setter
                ButterKnife.apply(btns, new ButterKnife.Setter<Button, Boolean>() {
                    @Override
                    public void set(Button view, Boolean value, int index) {
                        if (index == 1) {
                            view.setEnabled(value);
                        }
                    }
                }, false);
        }
    }

    //view holder binding
    static class ViewHolder {
        @Bind(R.id.btn1)
        Button btn1;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
