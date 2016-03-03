package com.example.yjj.demoproj.dagger2.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @author:YJJ
 * @date:2015/11/2
 * @email:yangjianjun@117go.com
 */
public class UserListFragment extends Fragment {
    private Injector injector;

    public interface Injector {
        void inject(UserListFragment fragment);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.injector.inject(this);
    }
}
