package com.example.yjj.rxdemo.rxbind.util;

import rx.Observable;

/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */
public interface UploadService<T> {

    Observable<T> getUploadInfo(String file);
}
