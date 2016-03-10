package com.example.yjj.dagger_mvp.mvp.presenter;

import com.example.yjj.dagger_mvp.data.User;
import com.example.yjj.dagger_mvp.mvp.activity.MainActivity;
import com.example.yjj.dagger_mvp.mvp.component.DaggerMainPresenterComponent;
import com.example.yjj.dagger_mvp.mvp.model.UserModel;
import com.example.yjj.dagger_mvp.util.ToastUtil;
import com.example.yjj.dagger_mvp.util.UserRecorder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
public class MainPresenter {
    @Inject
    User loginUser;
    @Inject
    UserRecorder userRecorder;
    private static final String NO_USER_INFO = "no user";
    private MainActivity activity;
    private UserModel userModel;

    private List<User> savedUsers = new ArrayList<>();

    public MainPresenter(MainActivity activity, UserModel userModel) {
        this.activity = activity;
        this.userModel = userModel;
        //初始化组件
        DaggerMainPresenterComponent.builder()
                .mainActivityComponent(activity.getMainActivityComponent())
                .build()
                .inject(this);
    }

    public void findUser(long id) {
        if (loginUser == null) {
            ToastUtil.toastShortMsg("please log in");
            return;
        }
        User user = userModel.findUser(id);
        if (user != null) {
            activity.updateUI(user.toString());
            savedUsers.clear();
            savedUsers.add(user);
        } else {
            activity.updateUI(NO_USER_INFO);
        }
    }

    public void findUsers() {
        if (loginUser == null) {
            ToastUtil.toastShortMsg("please log in");
            return;
        }
        List<User> users = userModel.findUsers();
        if (users != null && users.size() > 0) {
            activity.updateUI(users.toString());
            savedUsers.clear();
            savedUsers.addAll(users);
        } else {
            activity.updateUI(NO_USER_INFO);
        }
    }

    public void saveUsers() {
        userRecorder.setLastUsers(savedUsers);
    }

    public void getLatsUser() {
        activity.updateUI(userRecorder.getLastUsers());
    }
}
