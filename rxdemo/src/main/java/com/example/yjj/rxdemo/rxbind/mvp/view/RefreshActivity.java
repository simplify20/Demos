package com.example.yjj.rxdemo.rxbind.mvp.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yjj.rxdemo.R;
import com.example.yjj.rxdemo.rxbind.bean.User;
import com.example.yjj.rxdemo.rxbind.interactor.UserService;
import com.example.yjj.rxdemo.rxbind.mvp.presenter.RefreshPresenter;
import com.example.yjj.rxdemo.util.ToastUtil;
import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author:YJJ
 * @date:2015/11/9
 * @email:yangjianjun@117go.com
 */
public class RefreshActivity extends AppCompatActivity implements RefreshPresenter.RefreshListener {
    @Bind(R.id.refresh_hint)
    TextView hint;
    @Bind(R.id.recycler)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipe;

    private DemoAdapter adapter;
    private boolean usePresenter = false;
    private boolean usePresenter1 = true;
    private RefreshPresenter presenter;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        ButterKnife.bind(this);
        presenter = new RefreshPresenter(this);
        adapter = new DemoAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        if (usePresenter) {
            usePresenter();
        } else if (usePresenter1) {
            usePresenter1();
        } else {
            dontUsePresenter();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    @Override
    public void refreshOk(List<User> users) {
        adapter.setData(users);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshError() {
        hint.setVisibility(View.VISIBLE);
        swipe.setRefreshing(false);
        ToastUtil.toastShortMsg("刷新失败,请重试");
    }

    @Override
    public void refreshFinished() {
        swipe.setRefreshing(false);
        ToastUtil.toastShortMsg("刷新成功");
    }

    private void usePresenter() {
        RxSwipeRefreshLayout.refreshes(swipe)
                .doOnNext(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        hint.setVisibility(View.GONE);
                    }
                })
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        subscription = presenter.LoadUsers(new DemoSubscriber());
                    }
                });
    }

    private void usePresenter1() {
        RxSwipeRefreshLayout.refreshes(swipe)
                .doOnNext(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        hint.setVisibility(View.GONE);
                    }
                })
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        presenter.LoadUsers1();
                    }
                });
    }


    private void dontUsePresenter() {
        RxSwipeRefreshLayout.refreshes(swipe)
                .doOnNext(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        swipe.setRefreshing(true);
                        hint.setVisibility(View.GONE);
                    }
                })
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        userService.loadUsers()
                                .doOnNext(new Action1<List<User>>() {
                                    @Override
                                    public void call(List<User> users) {
                                        if (new Random().nextInt(10) % 2 == 0) {
                                            throw new RuntimeException();
                                        }
                                    }
                                })
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new DemoSubscriber());
                    }
                });
    }

    private UserService userService = new UserService() {
        @Override
        public Observable<List<User>> loadUsers() {
            List<User> users = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                User user = new User("DemoUser" + i, 20 + i);
                users.add(user);
            }
            return Observable.just(users);
        }
    };

    private class DemoAdapter extends RecyclerView.Adapter<DemoViewHolder> {

        private List<User> data;

        public DemoAdapter() {
        }

        public void setData(List<User> data) {
            this.data = data;
        }

        @Override
        public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.cell_user, parent, false);
            return new DemoViewHolder(v);
        }

        @Override
        public void onBindViewHolder(DemoViewHolder holder, int position) {
            holder.userName.setText(data.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }
    }

    private class DemoSubscriber extends Subscriber<List<User>> {

        @Override
        public void onCompleted() {
            swipe.setRefreshing(false);
            ToastUtil.toastShortMsg("刷新成功");
        }

        @Override
        public void onError(Throwable e) {
            hint.setVisibility(View.VISIBLE);
            swipe.setRefreshing(false);
            ToastUtil.toastShortMsg("刷新失败,请重试");
        }

        @Override
        public void onNext(List<User> users) {
            adapter.setData(users);
            adapter.notifyDataSetChanged();
        }
    }

    static class DemoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.user_name)
        TextView userName;

        public DemoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
