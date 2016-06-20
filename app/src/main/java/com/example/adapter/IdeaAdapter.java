package com.example.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Opinion;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by jucaipen on 16/5/10.
 */
public class IdeaAdapter extends BaseAdapter {
    private List<Opinion> list;
    private Context context;

    public IdeaAdapter(Context context, List<Opinion> opinionList) {
        this.context = context;
        this.list = opinionList;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    public void setList(List<Opinion> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.viewpoint, null);
        }
        ImageView person_iv = (ImageView) convertView.findViewById(R.id.person_iv);
        TextView point_name = (TextView) convertView.findViewById(R.id.point_name);
        TextView point_touzi = (TextView) convertView.findViewById(R.id.point_touzi);
        TextView point_title = (TextView) convertView.findViewById(R.id.point_title);
        TextView point_bodys = (TextView) convertView.findViewById(R.id.idea_body);
        TextView point_plun = (TextView) convertView.findViewById(R.id.point_plun);
        TextView tv_dianzan = (TextView) convertView.findViewById(R.id.tv_dianzan);

        Opinion opinion = list.get(position);
        if (opinion.getHeadface() != null) {
            Glide.with(convertView.getContext()).load(opinion.getHeadface())
                    .placeholder(R.drawable.rentou)
                    .bitmapTransform(new CropCircleTransformation(context))
                    .into(person_iv);
        }
        point_name.setText(opinion.getNickname());
        point_touzi.setText(opinion.getLevel());
        point_title.setText(opinion.getTitle());
        point_bodys.setText(Html.fromHtml(opinion.getBodys()));
        point_plun.setText(opinion.getHits() + "");
        tv_dianzan.setText(opinion.getGoods() + "");
        return convertView;
    }
}
