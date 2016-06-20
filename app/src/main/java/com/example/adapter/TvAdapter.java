package com.example.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/27.
 *
 * 文字直播适配器
 */
public class TvAdapter extends BaseAdapter{
    private Context context;
    private View view;

    private Dialog dialog;
    public TvAdapter(FragmentActivity activity) {
        this.context=activity;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.txitem,null);
        }
        ImageView iv_deblocking= (ImageView) convertView.findViewById(R.id.iv_deblocking);

        view=LayoutInflater.from(context).inflate(R.layout.tvpopwindow,null);
        dialog= new Dialog(context);
        dialog.setContentView(view);

        iv_deblocking.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (dialog!=null){
                    dialog.show();
                }
            }
        });

        ImageView iv_sure= (ImageView) view.findViewById(R.id.iv_sure);
        ImageView iv_dismiss= (ImageView) view.findViewById(R.id.iv_dismiss);
        iv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "解锁了", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        iv_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        return convertView;
    }
}
