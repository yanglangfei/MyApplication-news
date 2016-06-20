package com.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.Activity.KnowListActivity;
import com.example.androidnetwork.R;
import com.example.model.SchoolKnowledge;
import com.example.model.SmallType;

import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class SchoolAdapter extends BaseAdapter implements View.OnClickListener {
    private final List<SchoolKnowledge> knowledges;
    private final Context context;

    public SchoolAdapter(List<SchoolKnowledge> knowledges, Context context) {
        this.knowledges = knowledges;
        this.context = context;
    }

    @Override
    public int getCount() {
        return knowledges.size();
    }

    @Override
    public Object getItem(int position) {
        return knowledges.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ui_knowtype, null);
        }
        TextView tv_bgType = (TextView) convertView.findViewById(R.id.tv_bgType);
        LinearLayout ll_lay = (LinearLayout) convertView.findViewById(R.id.ll_lay);

        SchoolKnowledge know = knowledges.get(position);
        List<SmallType> strs = know.getSmallType();
        int bId = know.getBigId();
        int sId = know.getSmallId();


        String type = know.getBigType();
        tv_bgType.setText(type);
        tv_bgType.setBackgroundColor(know.getColor());
        if (strs.size() <= 0) {
            TextView tv_item = new TextView(context);
            tv_item.setText("暂无二级分类");
            ll_lay.addView(tv_item);
        } else {
            for (int i = 0; i < strs.size(); i++) {
                TextView tv_item = new TextView(context);
                tv_item.setText(strs.get(i).smallType);
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tv_item.setLayoutParams(lp);
                tv_item.setId(i);
                tv_item.setId(strs.get(i).xxId);
                tv_item.setOnClickListener(this);
                ll_lay.addView(tv_item);
            }
            ll_lay.measure(0, 0);
            int h = ll_lay.getMeasuredHeight();
            tv_bgType.setHeight(h);
        }

        return convertView;
    }

    @Override
    public void onClick(View v) {
        int nId = v.getId();
        Intent intent=new Intent();
        intent.setClass(context,KnowListActivity.class);
        intent.putExtra("nId",nId);
        context.startActivity(intent);

    }
}
