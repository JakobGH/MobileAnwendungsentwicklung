package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyAdapter extends BaseAdapter {


    @Override
    public int getCount() {
        return MyValues.values().length;
    }

    @Override
    public Object getItem(int position) {
        return MyValues.VALUE1;
    }

    @Override
    public long getItemId(int position) {
        return 2L;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }
}
