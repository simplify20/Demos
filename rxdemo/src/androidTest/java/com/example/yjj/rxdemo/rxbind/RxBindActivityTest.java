package com.example.yjj.rxdemo.rxbind;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.TextView;

import com.example.yjj.rxdemo.R;

/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */
public class RxBindActivityTest extends ActivityInstrumentationTestCase2<RxBindActivity> {
    RxBindActivity activity;
    Button btn;
    TextView txt;

    public RxBindActivityTest() {
        super(RxBindActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        assertNotNull("activity is null", activity);
        btn = (Button) activity.findViewById(R.id.btn);
        assertNotNull("btn is null", btn);
        txt = (TextView) activity.findViewById(R.id.text_view);
    }

    @UiThreadTest
    public void testText() throws Exception {
        btn.performClick();
    }
}
