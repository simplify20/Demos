package com.example.yjj.databinding.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.yjj.databinding.BR;

/**
 * @author:YJJ
 * @date:2016/3/8
 * @email:yangjianjun@117go.com
 */
public class ObservableUser extends BaseObservable {

    private User user;

    @Bindable
    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
        notifyPropertyChanged(BR.user);
    }
}
