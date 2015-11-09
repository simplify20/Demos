package com.example.yjj.dagger_mvp.mvp.activity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yjj.dagger_mvp.ApplicationComponent;
import com.example.yjj.dagger_mvp.R;
import com.example.yjj.dagger_mvp.mvp.component.MainActivityComponent;
import com.example.yjj.dagger_mvp.ui.component.DaggerMainActivityComponent;
import com.example.yjj.dagger_mvp.mvp.module.MainActivityModule;
import com.example.yjj.dagger_mvp.mvp.presenter.MainPresenter;
import com.example.yjj.dagger_mvp.util.ToastUtil;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Bind(R.id.show_user)
    TextView userList;
    @Bind(R.id.input_id)
    EditText inputId;
    @Inject
    MainPresenter presenter;

    private MainActivityComponent mainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getLatsUser();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.saveUsers();
    }

    @Override
    protected void setupActivityComponent(ApplicationComponent appComponent) {
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .applicationComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build();
        mainActivityComponent.inject(this);

    }

    @OnClick({R.id.find_user, R.id.find_users})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_user:
                Editable editable = inputId.getText();
                if (editable != null) {
                    try {
                        int num = Integer.valueOf(editable.toString());
                        presenter.findUser(num);
                    } catch (Exception e) {
                        ToastUtil.toastShortMsg("please input a number");
                        return;
                    }
                } else {
                    ToastUtil.toastShortMsg("please input a number");
                }
                break;
            case R.id.find_users:
                presenter.findUsers();
                break;
        }
    }

    public void updateUI(String info) {
        userList.setText("user:" + info);
    }


    public MainActivityComponent getMainActivityComponent() {
        return mainActivityComponent;
    }
}
