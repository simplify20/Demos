package com.example.yjj.rxdemo.rxbind.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author:YJJ
 * @date:2015/11/4
 * @email:yangjianjun@117go.com
 */
public class TestAdapter extends BaseAdapter {
    ArrayList<String> data = new ArrayList<>();
    Context context;

    public TestAdapter(Context context) {
        String[] names = new String[]{"yang", "jian", "jun"};
        data.addAll(Arrays.asList(names));
        notifyDataSetChanged();
        this.context = context;
    }

    public void add(String name) {
        data.add(name);
    }

    public ArrayList<String> getData() {
        return data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return new View(context);
    }
}
