package com.example.Live;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.ChatAdapter;
import com.example.androidnetwork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/12.
 * 互动列表
 */
public class InteractList extends Fragment {
    private  View view;
    private ListView chatListview;
    private ChatAdapter adapter;
    private List<String> list=new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.interactlist,container,false);

        init();
        return view;
    }

    private void init() {
        chatListview= (ListView) view.findViewById(R.id.chatListview);
        adapter=new ChatAdapter(getActivity(),list);
        chatListview.setAdapter(adapter);
    }
}
