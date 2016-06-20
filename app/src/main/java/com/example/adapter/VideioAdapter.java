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
import com.example.utils.TimeUtils;
import com.example.model.School;

import java.util.List;

/**
 * Created by Administrator on 2016/5/18.
 * <p/>
 * 学院适配器
 */
public class VideioAdapter extends BaseAdapter {
    private List<School> list;
    private Context context;

    public VideioAdapter(FragmentActivity activity, List<School> schoolList) {
        this.context = activity;
        this.list = schoolList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void setList(List<School> list) {
        this.list = list;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.oldvideo, null);
        }
        ImageView video_bg = (ImageView) convertView.findViewById(R.id.video_bg);

        TextView video_date = (TextView) convertView.findViewById(R.id.video_date);
        TextView video_month = (TextView) convertView.findViewById(R.id.video_month);

        TextView tv_context = (TextView) convertView.findViewById(R.id.tv_context);
        TextView tv_indruce = (TextView) convertView.findViewById(R.id.tv_indruce);
        School school = list.get(position);
        video_bg.setScaleType(ImageView.ScaleType.FIT_XY);
        if (school.getImage() != null) {
            Glide.with(context).load(school.getImage()).into(video_bg);
        }
        // tv_context.setText(school.getTitle());
        tv_indruce.setText(school.getDesc());
        video_date.setText(school.getHits() + "");
        video_month.setText(TimeUtils.parseStrDate(school.getInsertDate(), "MM-dd"));

        return convertView;
    }
}
