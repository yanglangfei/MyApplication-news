package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Video;

import java.util.List;

/**
 * Created by jucaipen on 16/5/10.
 */
public class VideoPlayAdapter extends BaseAdapter{
    private Context context;
    private List<Video> list;

    public VideoPlayAdapter(FragmentActivity activity, List<Video> videoList) {
        this.context=activity;
        this.list=videoList;
    }

    public void setList(List<Video> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.tvitems,null);
        }
        ImageView select_iv= (ImageView) convertView.findViewById(R.id.select_iv);
        TextView tv_context= (TextView) convertView.findViewById(R.id.tv_context);
        TextView live_content= (TextView) convertView.findViewById(R.id.live_content);
        select_iv.setScaleType(ImageView.ScaleType.FIT_XY);

        Video video=list.get(position);
        if (video.getImageUrl()!=null){
            Glide.with(context).load(video.getImageUrl()).into(select_iv);
        }
        tv_context.setText(video.getTitle());
        live_content.setText(video.getDesc());



        return convertView;
    }
}
