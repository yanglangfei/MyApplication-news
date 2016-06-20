package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/31.
 *
 * 问答详情适配器
 */
public class DetailsAdapter extends BaseAdapter{
    private Context context;

    public DetailsAdapter(Context context) {
        this.context=context;
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

            convertView= LayoutInflater.from(context).inflate(R.layout.detailaitem,null);
        }
        return convertView;
    }
}
