package com.example.yjj.demoproj.dagger2.demo4;

import javax.inject.Inject;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
public class User {

    private String userName;

    @Inject
    public User() {
        userName = "Bob";
    }

    public void speak(){
        System.out.println(userName+" speak");
    }
}
