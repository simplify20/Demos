package com.example.yjj.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yjj.databinding.databinding.ActivityUserListBinding;
import com.example.yjj.databinding.databinding.UserListItemBinding;
import com.example.yjj.databinding.model.ObservableUser;
import com.example.yjj.databinding.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:YJJ
 * @date:2016/3/8
 * @email:yangjianjun@117go.com
 */
public class UserListActivity extends AppCompatActivity {
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserListBinding activityUserListBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_list);
        userAdapter = new UserAdapter();
        ArrayList<ObservableUser> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ObservableUser observableUser = new ObservableUser();
            observableUser.setUser(new User("first" + i, "last" + i));
            data.add(observableUser);
        }
        userAdapter.setData(data);
        activityUserListBinding.userList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        activityUserListBinding.userList.setAdapter(userAdapter);
    }

    public interface IDataBinder<T> {
        void bindData(T data);
    }

    private static class UserViewHolder extends RecyclerView.ViewHolder implements IDataBinder<ObservableUser> {
        protected UserListItemBinding viewDataBinding;

        public UserViewHolder(View itemView) {
            super(itemView);
            viewDataBinding = UserListItemBinding.bind(itemView);
        }

        @Override
        public void bindData(final ObservableUser data) {
            viewDataBinding.setObservableUser(data);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //update clicked
                    data.setUser(new User("update","update"));
                }
            });
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
        private List<ObservableUser> data;

        public void setData(List<ObservableUser> data) {
            this.data = data;
        }

        @Override
        public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            UserViewHolder userViewHolder = new UserViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_list_item, parent, false));
            return userViewHolder;
        }

        @Override
        public void onBindViewHolder(UserViewHolder holder, int position) {
            holder.bindData(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }
    }


}
