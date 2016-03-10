package com.example.yjj.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yjj.simple.biz.github.ReposService;
import com.example.yjj.simple.data.di.github.component.DaggerGitHubComponent;
import com.example.yjj.simple.data.di.github.component.GitHubComponent;
import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.framework.repository.DataCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        GitHubComponent gitHubComponent = DaggerGitHubComponent.builder().build();
        ReposService reposService = gitHubComponent.getRepoService();
        reposService.getRepos("octocat", new DataCallback<List<Repo>>() {


            @Override
            public void onSuccess(List<Repo> repos) {
                for (Repo repo : repos) {
                    System.out.println(repo.name + "url(" + repo.html_url + ")");
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
