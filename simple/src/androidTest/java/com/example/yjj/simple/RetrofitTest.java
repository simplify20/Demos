package com.example.yjj.simple;

import android.support.test.runner.AndroidJUnit4;

import com.example.yjj.simple.framework.repository.DataCallback;
import com.example.yjj.simple.biz.github.ContributorsService;
import com.example.yjj.simple.data.di.github.component.DaggerGitHubComponent;
import com.example.yjj.simple.data.entity.github.Contributor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
@RunWith(AndroidJUnit4.class)
public class RetrofitTest {
    ContributorsService simpleService;
    CountDownLatch countDownLatch;

    @Before
    public void setUp() throws Exception {
        simpleService = DaggerGitHubComponent.builder()
                .build()
                .getContributorsService();
        countDownLatch = new CountDownLatch(1);
    }

    @Test
    public void testGetContributions() throws Exception {
        simpleService.getContributors("square", "retrofit", new DataCallback<List<Contributor>>() {
            @Override
            public void onSuccess(List<Contributor> contributors) {
                for (Contributor contributor : contributors) {
                    System.out.println(contributor.login + " (" + contributor.contributions + ")");
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                countDownLatch.countDown();
            }

            @Override
            public void onComplete() {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();

    }
}
