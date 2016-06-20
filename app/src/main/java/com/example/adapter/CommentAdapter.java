package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.Activity.HotCareful;
import com.example.androidnetwork.R;
import com.example.model.Comments;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/5/14.
 * <p/>
 * 评论适配器
 */
public class CommentAdapter extends BaseAdapter {
    private List<Comments> list;
    private Context context;

    public CommentAdapter(List<Comments> comms, HotCareful careful) {
        this.list = comms;
        this.context = careful;
    }

    public void setList(List<Comments> list) {
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHoder hoder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.commitems, null);
            hoder=new ViewHoder();
            hoder.comm_person = (ImageView) view.findViewById(R.id.comm_person);
            hoder.comm_name = (TextView) view.findViewById(R.id.comm_name);
            hoder.comm_time = (TextView) view.findViewById(R.id.comm_time);
            hoder.comm_content = (TextView) view.findViewById(R.id.comm_content);
            hoder.comm_dianzan = (TextView) view.findViewById(R.id.comm_dianzan);
            hoder.comm_plun = (TextView) view.findViewById(R.id.comm_plun);
            view.setTag(hoder);
        }else{
            hoder= (ViewHoder) view.getTag();
        }

        hoder.comm_name.setText(list.get(position).getUserName());

        hoder.comm_time.setText(list.get(position).getInsertDate());

        hoder.comm_dianzan.setText(list.get(position).getGoods() + "");

        hoder.comm_plun.setText(list.get(position).getReptCount() + "");
        Glide.with(context).load(list.get(position).getHeadFace())
                .bitmapTransform(new CropCircleTransformation(context))
                .into(hoder.comm_person);
      //  hoder.comm_content.loadDataWithBaseURL(null,list.get(position).getBody(),"text/html","utf-8",null);

        return view;
    }


    public class ViewHoder{
        ImageView comm_person;
        TextView comm_name;
        TextView comm_time;
        TextView comm_content;
        TextView comm_dianzan;
        TextView comm_plun;

    }


}
