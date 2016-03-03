package com.example.yjj.demoproj;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yjj.demoproj.util.TestUtils;


/**
 * @author:YJJ
 * @date:2015/10/26
 * @email:yangjianjun@117go.com
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private static final long TIMEOUT_IN_MS = 200;
    private MainActivity mMainActivity;
    private TextView mHello;
    private Button mButton;
    private Button sendToReceiverButton;
    private EditText sendText;

    public MainActivityTest() {
        super(MainActivity.class);
    }



    public void testPreconditions() throws Exception {
        assertNotNull("activity is null", mMainActivity);
        assertNotNull("text_view is null", mHello);
        assertNotNull("button_view is null", mButton);
    }

    @MediumTest
    public void testView() throws Exception {

        assertEquals(mMainActivity.getString(R.string.hello_world), mHello.getText().toString());
        mMainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mHello.setText("set in ui thread");
            }
        });
        View decorView = mMainActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView, mButton);
        assertTrue("hello is visible", View.VISIBLE != mHello.getVisibility());
    }

    public void testBehavior() throws Exception {

        TouchUtils.clickView(this, mButton);
        assertTrue("hello is not visible", View.VISIBLE == mHello.getVisibility());
    }

    @MediumTest
    public void testSend() throws Exception {
        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(NextActivity.class.getName(),
                        null, false);
        // Send string input value
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                sendText.requestFocus();
            }
        });
        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Hello Android");
        getInstrumentation().waitForIdleSync();

        // Validate that NextActivity is started
        TouchUtils.clickView(this, sendToReceiverButton);
        Activity receiverActivity = receiverActivityMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);
        assertNotNull("NextActivity is null", receiverActivity);
        assertEquals("Monitor for NextActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                NextActivity.class, receiverActivity.getClass());
        // Validate that ReceiverActivity has the correct data
        assertEquals("not right", "Hello Android", receiverActivity.getIntent().getStringExtra("txt"));
        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

    }

    @UiThreadTest
    public void testUiThread() throws Exception {

        mHello.setText("ui thread set");
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(false);
        //call common test com.example.yjj.demoproj.util api
        TestUtils.log();
        mMainActivity = getActivity();
        mHello = (TextView) mMainActivity.findViewById(R.id.text_hello);
        mButton = (Button) mMainActivity.findViewById(R.id.button);
        sendToReceiverButton = (Button) mMainActivity.findViewById(R.id.send_next);
        sendText = (EditText) mMainActivity.findViewById(R.id.edit_text);
    }
}
