package com.example.uilibrary.listview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/25 0025.
 */

public class ViewHolder {
    private SparseArray<View> mViews;
    private int mPosition;
    //是一个map集合，效率比map高
    private View mConvertView;

    public ViewHolder(Context context, ViewGroup parent, int layoutID, int position) {
        this.mViews = new SparseArray<>();
        this.mPosition = position;
        mConvertView = LayoutInflater.from(context).inflate(layoutID, parent, false);
        mConvertView.setTag(this);
    }


    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutID, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutID, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            //更新位置
            holder.mPosition = position;
            return holder;
        }
    }

    /**
     * 通过ViewID获取控件
     *
     * @param viewID
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewID) {
        View view = mViews.get(viewID);
        if (view == null) {
            view = mConvertView.findViewById(viewID);
            mViews.put(viewID, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    /**
     * 设置TextView的值
     *
     * @param viewID
     * @param text
     * @return
     */
    public ViewHolder setText(int viewID, String text) {
        TextView tv = getView(viewID);
        tv.setText(text);
        return this;
    }

    /**
     * 设置图片资源
     * @param viewID
     * @param resouceID
     * @return
     */
    public ViewHolder setImageResouce(int viewID,int resouceID) {
        ImageView view = getView(viewID);
        view.setImageResource(resouceID);
        return this;
    }

    /**
     * 设置图片资源
     * @param viewID
     * @param bitmap
     * @return
     */
    public ViewHolder setImageBitmap(int viewID, Bitmap bitmap) {
        ImageView view = getView(viewID);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 可以利用框架在此方法中中根据url加载图片
     * @param viewID
     * @param url
     * @return
     */
    public ViewHolder setImageUrl(int viewID,String url) {
//        SimpleDraweeView draweeView = getView(viewID);
//        draweeView.setImageURI(url);
        return this;
    }

}
