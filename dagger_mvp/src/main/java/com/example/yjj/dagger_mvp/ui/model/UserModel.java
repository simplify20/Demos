package com.example.yjj.dagger_mvp.ui.model;

import com.example.yjj.dagger_mvp.data.User;
import com.example.yjj.dagger_mvp.webservice.UserService;

import java.util.List;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
public class UserModel {
    private UserService userService;

    public UserModel(UserService userService) {
        this.userService = userService;
    }

    public User findUser(long id) {
        return userService.getUser(id);
    }

    public List<User> findUsers() {
        return userService.loadUsers();
    }

}
