package com.example.yjj.demoproj;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yjj.demoproj.util.ToastUtil;
import com.example.yjj.demoproj.view.TagLayout;
import com.example.yjj.demoproj.view.XferView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author:YJJ
 * @date:2016/1/13
 * @email:yangjianjun@117go.com
 */
public class XferActivity extends BaseActivity {
    @Bind(R.id.xfer1)
    XferView xferView1;

    @Bind(R.id.model_name)
    TextView textView;
    @Bind(R.id.tags)
    TagLayout tagLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfer);
        ButterKnife.bind(this);
        final PorterDuff.Mode[] models = PorterDuff.Mode.values();
        final int length = models.length;
        xferView1.setMode(PorterDuff.Mode.SRC_ATOP);
        textView.setText("XferModel:" + PorterDuff.Mode.SRC_ATOP.name());
        xferView1.setClickable(true);
        xferView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = new Random().nextInt(length);
                xferView1.setMode(models[index]);
                textView.setText("XferModel:" + models[index].name());
            }
        });
        ToastUtil.toastShortMsg("点击矩形切换混合模式");

        List<String> tags = new ArrayList<>(Arrays.asList("hhh", "fdsf", "dfs","dfds",
                "fdsfd","hhh","FDF","SDFSDFSD",
                "EFRRFRG","FDSF","dfsf","fsdsdferf",
                "dfefse","rfwertfetgrgtr","frefefgrg","fefe",
                "dwsdasd","dfweferfefegfergrtgrrggrgdffefer"));
        tagLayout.setTags(tags);
        tagLayout.setOnItemClickListener(new TagLayout.OnItemClickListener() {
            @Override
            public void onItemClicked(int position, TextView textView) {
                ToastUtil.toastShortMsg("clicked:" + position);
            }
        });

    }
}
