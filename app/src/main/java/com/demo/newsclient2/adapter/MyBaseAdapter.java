package com.demo.newsclient2.adapter;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by GSJ
 * Date: 2016/10/15
 * Time: 15:45
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    protected List<T> mData;
    public MyBaseAdapter(List<T> mData) {
        this.mData=mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    @Override
//    public abstract View getView(int position, View convertView, ViewGroup parent);
}
