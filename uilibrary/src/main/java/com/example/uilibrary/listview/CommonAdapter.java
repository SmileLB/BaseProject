package com.example.uilibrary.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/25 0025.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    protected List<T> mDatas;
    protected Context mContext;
    protected LayoutInflater mInflater;
    private int layoutID;

    public CommonAdapter(Context context, List<T> datas, int layoutID) {
        this.mDatas = datas;
        this.mContext = context;
        this.layoutID = layoutID;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder = ViewHolder.get(mContext, convertView, parent, layoutID, position);
        convert(mHolder, getItem(position));
        return mHolder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T t);
}
