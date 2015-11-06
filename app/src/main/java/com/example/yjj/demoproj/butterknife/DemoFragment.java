package com.example.yjj.demoproj.butterknife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yjj.demoproj.R;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * @author:YJJ
 * @date:2015/11/5
 * @email:yangjianjun@117go.com
 */
public class DemoFragment extends Fragment {
    //view binding
    @Bind(R.id.msg)
    TextView textView;
    //resource binding
    @BindString(R.string.msg)
    String msg;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_butter_knife_demo, container, false);
        ButterKnife.bind(this, v);
        textView.setText(msg);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
