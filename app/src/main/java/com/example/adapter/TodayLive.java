package com.example.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/6/7.
 * <p/>
 * 今日直播 适配器
 */
public class TodayLive extends RecyclerView.Adapter<TodayLive.MyHolder> {


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todylive, null);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        TextView tv_context = (TextView) holder.itemView.findViewById(R.id.tv_context);
        tv_context.setText("name");

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        View itemView;

        public MyHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
