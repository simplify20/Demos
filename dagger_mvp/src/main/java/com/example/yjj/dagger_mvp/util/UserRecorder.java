package com.example.yjj.dagger_mvp.util;

import android.content.SharedPreferences;

import com.example.yjj.dagger_mvp.DaggerApplication;
import com.example.yjj.dagger_mvp.data.User;

import java.util.List;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
public class UserRecorder {
    private static SharedPreferences preferences = DaggerApplication.getComponent().preferences();
    private static final String LAST_SAVED_USERS = "users";

    public String getLastUsers() {
        return preferences.getString(LAST_SAVED_USERS, "no user saved");
    }

    public void setLastUsers(List<User> lastUsers) {
        if (lastUsers == null || lastUsers.size() == 0)
            return;
        preferences.edit().putString(LAST_SAVED_USERS, lastUsers.toString()).commit();
    }

    public void setLastUser(User user) {
        if (user == null)
            return;
        preferences.edit().putString(LAST_SAVED_USERS, user.toString());
    }
}
