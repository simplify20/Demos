package com.example.yjj.demoproj;

import android.test.ActivityInstrumentationTestCase2;

import com.example.yjj.demoproj.dagger2.view.UserListActivity;

/**
 * @author:YJJ
 * @date:2015/11/2
 * @email:yangjianjun@117go.com
 */
public class UserListActivityTest extends ActivityInstrumentationTestCase2<UserListActivity> {
    UserListActivity mActivity;

    public UserListActivityTest() {
        super(UserListActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
    }

    public void testInject() throws Exception {

    }
}
