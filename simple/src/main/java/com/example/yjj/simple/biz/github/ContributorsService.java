package com.example.yjj.simple.biz.github;

import com.example.yjj.simple.framework.repository.DataCallback;

/**
 * @author:YJJ
 * @date:2016/3/9
 * @email:yangjianjun@117go.com
 */
public interface ContributorsService {
    /**
     * 获取某个项目的所有贡献者
     *
     * @param owner
     * @param repo
     * @return
     */
    void getContributors(String owner, String repo, DataCallback callback);

}
