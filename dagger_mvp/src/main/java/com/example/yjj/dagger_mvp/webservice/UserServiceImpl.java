package com.example.yjj.dagger_mvp.webservice;

import com.example.yjj.dagger_mvp.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
public class UserServiceImpl implements UserService {
    private static List<User> users = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            User user = new User(i, "user" + i);
            users.add(user);
        }
    }

    @Override
    public User getUser(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> loadUsers() {
        List<User> result = new ArrayList<>();
        result.addAll(users);
        return result;
    }

    @Override
    public User getUser(long id, String name) {
        for (User user : users) {
            if (user.getId() == id && user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

}
