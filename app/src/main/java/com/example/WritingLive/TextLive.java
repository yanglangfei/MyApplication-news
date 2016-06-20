package com.example.WritingLive;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.TvAdapter;
import com.example.androidnetwork.R;

/**
 * Created by Administrator on 2016/5/27.
 * <p/>
 * 文字直播
 */
public class TextLive extends Fragment {
    private View view;
    private ListView text_lv;
    private TvAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

        view=inflater.inflate(R.layout.textlive,container,false);

        init();
        return view;
    }

    private void init() {
        text_lv= (ListView) view.findViewById(R.id.text_lv);
        adapter=new TvAdapter(getActivity());
        text_lv.setAdapter(adapter);
    }
}
