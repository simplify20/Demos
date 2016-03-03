package com.example.yjj.rxdemo.rxbind.interactor;

import com.example.yjj.rxdemo.rxbind.bean.User;

import java.util.List;

import rx.Observable;

/**
 * @author:YJJ
 * @date:2015/11/9
 * @email:yangjianjun@117go.com
 */
public interface UserService {

    Observable<List<User>> loadUsers();
}
