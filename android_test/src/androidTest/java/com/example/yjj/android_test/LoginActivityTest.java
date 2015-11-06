package com.example.yjj.android_test;

import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author:YJJ
 * @date:2015/11/5
 * @email:yangjianjun@117go.com
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testName() throws Exception {

        onView(withId(R.id.input_name)).perform(typeText("jon"));
        onView(withId(R.id.login_btn)).perform(click());
        onView(withId(R.id.show_msg)).check(matches(withText("hello,jon")));
    }
}
