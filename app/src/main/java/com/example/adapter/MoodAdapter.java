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
 * 人气榜适配器
 */
public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MyHolder> {

    @Override
    public MoodAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mood, null);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MoodAdapter.MyHolder holder, int position) {
        TextView person_name = (TextView) holder.itemView.findViewById(R.id.person_name);
        person_name.setText("fdsagdsa");

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        View itemView;

        public MyHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
