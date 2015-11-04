package com.example.yjj.demoproj;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

/**
 * @author:YJJ
 * @date:2015/10/26
 * @email:yangjianjun@117go.com
 */
public class LaunchActivityTest extends ActivityUnitTestCase<MainActivity> {
    private Button mLaunch;
    private MainActivity mMainActivity;
    private Intent mLaunchIntent;

    public LaunchActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        //先打开main
        mLaunchIntent = new Intent(getInstrumentation().getContext(), MainActivity.class);
        startActivity(mLaunchIntent, null, null);

        mMainActivity = getActivity();
        mLaunch = (Button) mMainActivity.findViewById(R.id.launch_next);
    }

    public void testIntent() throws Exception {

        mLaunch.performClick();

        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", launchIntent);
//        assertTrue(isFinishCalled());

        final String payload =
                launchIntent.getStringExtra(NextActivity.EXTRAS_PAYLOAD_KEY);
        assertEquals("Payload is empty", MainActivity.STRING_PAYLOAD, payload);

    }
}
