package com.example.yjj.demoproj.test.dagger2;

import com.example.yjj.demoproj.dagger2.demo3.DaggerSingletonComponent;
import com.example.yjj.demoproj.dagger2.demo3.SingletonComponent;
import com.example.yjj.demoproj.dagger2.demo3.UploadManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author:YJJ
 * @date:2016/3/1
 * @email:yangjianjun@117go.com
 */
@RunWith(Parameterized.class)
public class ScopeTest {
    private static SingletonComponent singletonComponent = DaggerSingletonComponent
            .builder()
            .build();

    @Parameterized.Parameters(name = "{index}:{0}-{1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"dd","ff"},
                {"dd","ff"},
                {"dd","ff"},
                {"dd","ff"},
                {"dd","ff"},
                {"dd","f5"}
        });
    }

    public ScopeTest(String arg1,String arg2) {
    }

    @Test
    public void testUploadManager() throws Exception {
        UploadManager uploadManager = singletonComponent.uploadManager();
        System.out.println(uploadManager);
        uploadManager.upload("file--------------");
    }

    @Before
    public void setUp() throws Exception {
    }
}
