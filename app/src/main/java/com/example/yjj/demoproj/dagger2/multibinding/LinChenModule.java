package com.example.yjj.demoproj.dagger2.multibinding;

import com.example.yjj.demoproj.dagger2.util.StringKey;

import dagger.Module;
import dagger.Provides;

/**
 * @author:YJJ
 * @date:2016/3/2
 * @email:yangjianjun@117go.com
 */
@Module
public class LinChenModule {

    @Provides(type = Provides.Type.MAP)
    @StringKey("lin")
    AbstractModel provideLinChenModel() {
        return new LinChenModel();
    }
}
