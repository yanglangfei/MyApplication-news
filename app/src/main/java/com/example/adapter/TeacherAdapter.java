package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/12.
 */
public class TeacherAdapter extends BaseAdapter{
    private Context context;
    public TeacherAdapter(FragmentActivity activity) {
        this.context=activity;

    }

    @Override
    public int getCount() {
        return 12;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.ui_ideaitem,null);
        }
        return convertView;
    }
}
