package com.example.yjj.dagger_mvp.webservice;

import com.example.yjj.dagger_mvp.data.User;

import java.util.List;

/**
 * @author:YJJ
 * @date:2015/11/6
 * @email:yangjianjun@117go.com
 */
public interface UserService {
    User getUser(long id);

    List<User> loadUsers();
}
