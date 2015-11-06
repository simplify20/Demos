package com.example.yjj.dagger_mvp.data;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
public class AccountManager {
    private static User loginUser;

    public static User getCurrentUser() {
        if (loginUser == null) {
            loginUser = new User(10, "yang");
        }
        return loginUser;
    }
}
