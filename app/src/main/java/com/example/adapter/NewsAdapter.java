package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.example.androidnetwork.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/12.
 */
public class NewsAdapter extends BaseAdapter{
    private Context context;


    public NewsAdapter(FragmentActivity activity, List<Integer> list) {
        this.context=activity;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.ui_bd_item,null);
        }
        ImageView rk_photo= (ImageView) convertView.findViewById(R.id.rk_photo);
        if (position==0){
            rk_photo.setImageResource(R.drawable.one);
        }else if(position==1){
            rk_photo.setImageResource(R.drawable.two);
        }else if(position==2){
            rk_photo.setImageResource(R.drawable.three);
        }else {

        }
        return convertView;
    }
}
