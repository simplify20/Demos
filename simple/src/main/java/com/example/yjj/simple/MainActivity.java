package com.example.yjj.simple;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yjj.simple.biz.github.ReposService;
import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.databinding.ActivityMainBinding;
import com.example.yjj.simple.databinding.CellRepoBinding;
import com.example.yjj.simple.framework.repository.DataCallback;
import com.example.yjj.simple.presentation.view.Bindable;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RepoAdapter repoAdapter;
    private ActivityMainBinding activityMainBinding;
    private ReposService reposService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        repoAdapter = new RepoAdapter();
        activityMainBinding.reposList.setAdapter(repoAdapter);
        activityMainBinding.reposList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        GitHubComponent gitHubComponent = Dagge.builder().build();
//        reposService = gitHubComponent.getRepoService();
    }

    public void search(View v) {
        CharSequence name = activityMainBinding.inputName.getText();
        if (TextUtils.isEmpty(name)) {
            return;
        }
        final ProgressDialog progressDialog = ProgressDialog.show(this,"搜索中...","");
        reposService.getRepos(name.toString(), new DataCallback<List<Repo>>() {

            @Override
            public void onSuccess(List<Repo> repos) {
                if(repos==null||repos.size()==0){
                    Toast.makeText(MainActivity.this,"没有搜到任何结果~",Toast.LENGTH_SHORT);
                    activityMainBinding.setIsEmpty(true);
                    return;
                }
                activityMainBinding.setIsEmpty(false);
                repoAdapter.setRepos(repos);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this,"出错了~",Toast.LENGTH_SHORT);
                progressDialog.dismiss();
            }

            @Override
            public void onComplete() {
                progressDialog.dismiss();
            }
        });

    }

    private static class RepoViewHolder extends RecyclerView.ViewHolder implements Bindable<Repo> {
        private CellRepoBinding cellRepoBinding;

        public RepoViewHolder(View itemView) {
            super(itemView);
            cellRepoBinding = CellRepoBinding.bind(itemView);
        }

        @Override
        public void bindData(Repo repo) {
            cellRepoBinding.setRepo(repo);
        }
    }

    private class RepoAdapter extends RecyclerView.Adapter<RepoViewHolder> {
        private List<Repo> repos;

        public void setRepos(List<Repo> repos) {
            this.repos = repos;
            notifyDataSetChanged();
        }

        @Override
        public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RepoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_repo, parent, false));
        }

        @Override
        public void onBindViewHolder(RepoViewHolder holder, int position) {
            holder.bindData(repos.get(position));
        }

        @Override
        public int getItemCount() {
            return repos == null ? 0 : repos.size();
        }
    }


}
