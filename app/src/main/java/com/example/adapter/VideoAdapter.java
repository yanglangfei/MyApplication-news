package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Video;

import net.wujingchao.android.view.SimpleTagImageView;

import java.util.List;

/**
 * Created by jucaipen on 16/5/9.
 * <p/>
 * 推荐视频适配器
 */
public class VideoAdapter extends BaseAdapter {

    private Context context;
    private List<Video> list;

    public VideoAdapter(Context context, List<Video> list) {
        this.context = context;
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

    public void setList(List<Video> list) {
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.liveitems, null);
        }
        SimpleTagImageView imagevideo = (SimpleTagImageView) convertView.findViewById(R.id.imagevideo);
        TextView video_context = (TextView) convertView.findViewById(R.id.video_context);
        TextView video_inroduce = (TextView) convertView.findViewById(R.id.video_inroduce);
        Video video = list.get(position);
        if (video != null) {
            video_context.setText(video.getTitle());
            video_inroduce.setText(video.getDesc());
            imagevideo.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(video.getImageUrl()).into(imagevideo);
        }
        boolean isCharge=video.isCharge();
        if(isCharge){
            imagevideo.setTagText("收费");
        }else{
            imagevideo.setTagText("免费");
        }
        imagevideo.setTagRoundRadius(8);
        imagevideo.setTagTextSize(12);
        imagevideo.setTagWidth(25);
        imagevideo.setTagOrientation(SimpleTagImageView.RIGHT_TOP);


        return convertView;
    }
}
