package com.example.yjj.rxdemo.rxbind;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */

public class PagerFragment extends Fragment {
    public static PagerFragment instance(String name) {
        PagerFragment f = new PagerFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String name = (String) getArguments().get("name");
        TextView textView = new TextView(getContext());
        textView.setText(name);
        return textView;
    }
}