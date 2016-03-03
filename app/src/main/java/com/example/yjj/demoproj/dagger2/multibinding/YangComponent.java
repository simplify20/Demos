package com.example.yjj.demoproj.dagger2.multibinding;

import java.util.Map;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2016/3/2
 * @email:yangjianjun@117go.com
 */
@Component(modules = {LinChenModule.class, DongKaiModule.class, ZhanXModule.class})
public interface YangComponent {

    Map<String,AbstractModel> getModels();
}
