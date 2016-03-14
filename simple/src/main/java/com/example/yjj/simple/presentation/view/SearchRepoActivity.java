package com.example.yjj.simple.presentation.view;

import android.app.ProgressDialog;
import android.databinding.BindingAdapter;
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

import com.example.yjj.simple.R;
import com.example.yjj.simple.biz.github.RepoService;
import com.example.yjj.simple.data.di.github.component.DaggerGitHubComponent;
import com.example.yjj.simple.data.di.github.component.DaggerSearchRepoComponent;
import com.example.yjj.simple.data.entity.github.Repo;
import com.example.yjj.simple.databinding.ActivityRepoSearchBinding;
import com.example.yjj.simple.databinding.CellRepoBinding;
import com.example.yjj.simple.framework.repository.DataCallback;
import com.example.yjj.simple.framework.repository.impl.DataCallbackAdapter;
import com.example.yjj.simple.framework.util.DeviceUtil;
import com.example.yjj.simple.framework.util.ToastUtil;
import com.example.yjj.simple.presentation.viewmodel.RepoViewModel;

import java.util.List;

import javax.inject.Inject;

public class SearchRepoActivity extends AppCompatActivity {

    @Inject
    RepoService repoService;
    private ActivityRepoSearchBinding activityMainBinding;
    private RepoAdapter repoAdapter;
    private RepoViewModel repoViewModel = new RepoViewModel();
    private ProgressDialog progressDialog;
    private DataCallback<List<Repo>> mDataCallback;

    /**
     * Not recommend do like this,static method is hard to test
     * @param recyclerView
     * @param repos
     */
    @BindingAdapter("dataSet")
    public static void setRepoList(RecyclerView recyclerView, List<Repo> repos) {
        RepoAdapter repoAdapter = (RepoAdapter) recyclerView.getAdapter();
        if (repos == null || repos.size() == 0) {
            ToastUtil.toastShortMsg("没有搜到任何结果~");
            return;
        }
        repoAdapter.setRepos(repos);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_repo_search);
        activityMainBinding.setRepoVm(repoViewModel);
        mDataCallback = new RepoCallback();
        initViews();
        injectDepens();
    }

    private void initViews() {
        repoAdapter = new RepoAdapter();
        activityMainBinding.reposList.setAdapter(repoAdapter);
        activityMainBinding.reposList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void injectDepens() {
        DaggerSearchRepoComponent.builder()
                .gitHubComponent(DaggerGitHubComponent.builder().build())
                .build().inject(this);
    }


    public void search(View v) {
        DeviceUtil.toggleSoftInput(activityMainBinding.inputName, true);
        CharSequence name = activityMainBinding.inputName.getText();
        if (TextUtils.isEmpty(name)) {
            return;
        }
        progressDialog = ProgressDialog.show(this, "搜索中...", "");
        repoService.getRepos(name.toString(), mDataCallback);
    }


    /**
     * DataCallback
     */
    private class RepoCallback extends DataCallbackAdapter<List<Repo>> {
        @Override
        public void onError(Throwable e) {
            super.onError(e);
            Toast.makeText(SearchRepoActivity.this, "出错了~", Toast.LENGTH_SHORT);
            progressDialog.dismiss();
        }

        @Override
        public void onSuccess(List<Repo> repos) {
            super.onSuccess(repos);
            repoViewModel.addAll(repos);
            progressDialog.dismiss();
        }
    }


    private static class RepoViewHolder extends RecyclerView.ViewHolder implements Bindable<com.example.yjj.simple.data.entity.github.Repo> {
        private CellRepoBinding cellRepoBinding;

        public RepoViewHolder(View itemView) {
            super(itemView);
            cellRepoBinding = CellRepoBinding.bind(itemView);
        }

        @Override
        public void bindData(Repo repo) {

            cellRepoBinding.setRepo(repo);
            cellRepoBinding.executePendingBindings();
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
