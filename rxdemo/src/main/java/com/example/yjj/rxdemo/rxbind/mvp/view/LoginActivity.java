package com.example.yjj.rxdemo.rxbind.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.yjj.rxdemo.MainActivity;
import com.example.yjj.rxdemo.R;
import com.example.yjj.rxdemo.rxbind.bean.User;
import com.example.yjj.rxdemo.rxbind.mvp.presenter.LoginPresenter;
import com.example.yjj.rxdemo.util.ToastUtil;
import com.jakewharton.rxbinding.view.RxView;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @author:YJJ
 * @date:2015/11/9
 * @email:yangjianjun@117go.com
 */
public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.account)
    EditText editText;
    @Bind(R.id.psd)
    EditText pswd;
    @Bind(R.id.login_btn)
    Button loginBtn;
    @Bind(R.id.loading)
    ProgressBar loading;

    private LoginPresenter loginPresenter;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
        RxView.clicks(loginBtn)
                .filter(new Func1<Void, Boolean>() {
                    @Override
                    public Boolean call(Void aVoid) {
                        return isFiledEmpty();
                    }
                })
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        subscription = loginPresenter.login(editText.getText().toString(), pswd.getText().toString(), new LoginSubscriber());
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @NonNull
    private Boolean isFiledEmpty() {
        if (TextUtils.isEmpty(editText.getText()) || TextUtils.isEmpty(pswd.getText())) {
            ToastUtil.toastShortMsg("用户名或密码不能为空");
            return false;
        }
        loginBtn.setEnabled(false);
        loading.setVisibility(View.VISIBLE);
        return true;
    }

    private class LoginSubscriber extends Subscriber<User> {
        @Override
        public void onCompleted() {
            ToastUtil.toastShortMsg("登录成功");
            loading.setVisibility(View.GONE);
            loginBtn.setEnabled(true);
        }

        @Override
        public void onError(Throwable e) {
            ToastUtil.toastShortMsg("登录失败");
            loginBtn.setEnabled(true);
            loading.setVisibility(View.GONE);
            pswd.setText("");
        }

        @Override
        public void onNext(User user) {
            startActivity(MainActivity.buildIntent(user));
        }
    }
}
