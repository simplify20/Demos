package com.example.yjj.simple.data.web.api;

/**
 * @author:YJJ
 * @date:2016/3/10
 * @email:yangjianjun@117go.com
 */
public final class ApiConstants {
    private ApiConstants() {
    }

    public static final String ACTION_GET_CONTRIBUTORS = "getContributors";
    public static final String ACTION_GET_REPOS = "getRepos";
    public static final String ACTION_DAGGER_GET_REPOS = "daggerGetRepos";


    public static final String SCHEDULE_EXECUTOR_SINGLE_THREAD = "executor_single_thread_pool";
    public static final String DAGGER_REPO_FETCHER = "dagger_repo_fetcher";
}
