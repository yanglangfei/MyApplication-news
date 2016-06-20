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
import com.example.model.Interlocution;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by jucaipen on 16/5/10.
 */
public class QuestionAdapter extends BaseAdapter {
    private Context context;
    private List<Interlocution> list;


    public QuestionAdapter(FragmentActivity activity, List<Interlocution> list) {
        this.context = activity;
        this.list = list;
    }

    public void setList(List<Interlocution> list) {
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

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ui_ask_item, null);
        }

        ImageView for_picture = (ImageView) convertView.findViewById(R.id.for_picture);
        TextView for_name = (TextView) convertView.findViewById(R.id.for_name);
        TextView for_time = (TextView) convertView.findViewById(R.id.for_time);
        TextView for_body = (TextView) convertView.findViewById(R.id.for_body);
        TextView for_comment = (TextView) convertView.findViewById(R.id.for_comment);
        ImageView iv_ispay= (ImageView) convertView.findViewById(R.id.iv_ispay);
        Interlocution interlocution=list.get(position);
        if (interlocution.getHeadFace()!=null){
            Glide.with(context).load(interlocution.getHeadFace())
                    .bitmapTransform(new CropCircleTransformation(context))
                    .placeholder(R.drawable.rentou)
                    .into(for_picture);
        }

        if (interlocution.getIsPay()==1){
            iv_ispay.setImageResource(R.drawable.word_lock_clear);
        }
//        else {
//            iv_ispay.setImageResource(R.drawable.word_lock);
//        }

        for_name.setText(interlocution.getTrueName());
        for_time.setText(interlocution.getInsertDate());
        for_body.setText(interlocution.getAskBodys());
        for_comment.setText(" "+interlocution.getIsReply()+"");

        return convertView;
    }
}
