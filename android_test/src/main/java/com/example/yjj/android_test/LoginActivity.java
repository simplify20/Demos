package com.example.yjj.android_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author:YJJ
 * @date:2015/11/5
 * @email:yangjianjun@117go.com
 */
public class LoginActivity extends AppCompatActivity {
    private Button btn;
    private EditText txt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = (Button) findViewById(R.id.login_btn);
        txt = (EditText) findViewById(R.id.input_name);
        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txt.getText() == null ? "" : txt.getText().toString();
                startActivity(MainActivity.buildIntent(name));
            }
        });
    }
}
