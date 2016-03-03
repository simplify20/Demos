package com.example.yjj.demoproj.dagger2.demo5;

import dagger.Component;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
@Component(modules = QualifierModule.class)
public interface QualifierComponent {

    CoffeeMaker coffeMaker();
}
