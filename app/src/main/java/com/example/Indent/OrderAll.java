package com.example.Indent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.AllAdapter;
import com.example.androidnetwork.R;

/**
 * Created by Administrator on 2016/6/2.
 * 全部订单
 */
public class OrderAll extends Fragment {
    private View view;
    private AllAdapter adapter;
    private ListView lv_all;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.orderall, container, false);

        init();

        return view;
    }

    private void init() {
        lv_all= (ListView) view.findViewById(R.id.lv_all);
        adapter=new AllAdapter(getActivity());
        lv_all.setAdapter(adapter);

    }
}
