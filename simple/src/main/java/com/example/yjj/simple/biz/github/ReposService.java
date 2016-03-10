package com.example.yjj.simple.biz.github;

import com.example.yjj.simple.framework.repository.DataCallback;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public interface ReposService {

    /**
     * 获取某个人的全部仓库
     *
     * @param owner
     * @return
     */
    void getRepos(String owner, DataCallback callback);
}
