package com.example.yjj.demoproj;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String STRING_PAYLOAD = "sddfsdf";
    @Bind(R.id.text_hello)
    TextView mHello;
    private EditText mEdit;
    private Button mButton;
    private Button mNext;
    private Button mSendNext;
    private KeyguardManager mKeyguard;
    private KeyguardManager.KeyguardLock mKeylock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //测试
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mKeyguard = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        mKeylock = mKeyguard.newKeyguardLock("");
        mKeylock.disableKeyguard();

        mButton = (Button) findViewById(R.id.button);
        mNext = (Button) findViewById(R.id.launch_next);
        mSendNext = (Button) findViewById(R.id.send_next);
        mEdit = (EditText) findViewById(R.id.edit_text);

        mButton.setOnClickListener(this);
        mNext.setOnClickListener(this);
        mSendNext.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                mHello.setVisibility(View.VISIBLE);
                break;
            case R.id.launch_next:
                Intent intent = new Intent(MainActivity.this, NextActivity.class);
                intent.putExtra(NextActivity.EXTRAS_PAYLOAD_KEY, STRING_PAYLOAD);
                startActivity(intent);
                break;
            case R.id.send_next:
                Intent i = new Intent(this, NextActivity.class);
                i.putExtra("txt", mEdit.getText().toString());
                startActivity(i);
                break;
        }
    }
}
