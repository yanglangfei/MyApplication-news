package com.example.Live;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.NewsAdapter;
import com.example.androidnetwork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/12.
 * 最新榜单
 */
public class NewList extends Fragment {
    private View view;
    private ListView lv_news;
    private NewsAdapter adapter;
    private List<Integer> list=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.newlist, container, false);

        init();
        return view;
    }

    private void init() {
        list.clear();
//        list.add(R.drawable.one);
//        list.add(R.drawable.two);
//        list.add(R.drawable.three);

        lv_news= (ListView) view.findViewById(R.id.lv_news);
        adapter=new NewsAdapter(getActivity(),list);
        lv_news.setAdapter(adapter);
    }
}
