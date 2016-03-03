package com.example.yjj.demoproj;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author:YJJ
 * @date:2016/1/25
 * @email:yangjianjun@117go.com
 */
public class TabFragment extends Fragment implements View.OnClickListener {
    private static final String CONTENT = "CONTENT";
    private TextInputLayout textInputLayout;

    public static TabFragment newInstance(String content) {
        TabFragment f = new TabFragment();
        Bundle args = new Bundle();
        args.putString(CONTENT, content);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_tab, container, false);
        String content = (String) getArguments().get(CONTENT);
        TextView textView = (TextView) result.findViewById(R.id.content);
        textView.setText(content);
        textInputLayout = (TextInputLayout) result.findViewById(R.id.input_layout);
        textInputLayout.setCounterEnabled(true);
        textInputLayout.setCounterMaxLength(10);
        textInputLayout.setErrorEnabled(true);
        Button button = (Button) result.findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textInputLayout.getEditText().getText().length() < 5) {
                    textInputLayout.setError("length must more than or equal 5");
                } else {
                    Snackbar.make(v, "saving the setting...", Snackbar.LENGTH_SHORT)
                            .setAction("undo", TabFragment.this)//watch 'this'
                            .show();
                    textInputLayout.setError(null);
                }

            }
        });
        return result;
    }


    @Override
    public void onClick(View v) {
        textInputLayout.getEditText().setText("");

    }
}
