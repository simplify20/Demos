package com.example.yjj.demoproj.dagger2.producers;

/**
 * @author:YJJ
 * @date:2016/3/2
 * @email:yangjianjun@117go.com
 */
public class UserData {

    public String name;
    public int gender;
    public String desc;

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", desc='" + desc + '\'' +
                '}';
    }
}
