package com.example.yjj.rxdemo.rxbind.util;

import rx.Observable;

/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */
public class UploadServiceImpl implements UploadService<Integer> {
    @Override
    public Observable<Integer> getUploadInfo(String file) {
        return Observable.just(10, 20, 30, 40, 50, 60, 70);
    }
}
