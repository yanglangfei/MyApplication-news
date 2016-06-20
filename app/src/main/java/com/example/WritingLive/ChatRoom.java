package com.example.WritingLive;

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
 * Created by Administrator on 2016/5/27.
 * <p/>
 * 聊天室
 */
public class ChatRoom extends Fragment {
    private View view;
    private ChatAdapter adapter;
    private ListView lv_chat;
    private List<String> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.chatroom, container, false);

        init();

        return view;
    }

    private void init() {
        list = new ArrayList<>();
        lv_chat = (ListView) view.findViewById(R.id.lv_chat);
        list.add("你好");
        adapter = new ChatAdapter(getActivity(), list);
        lv_chat.setAdapter(adapter);
    }
}
