package com.example.TeacherDate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.IdeaAdapter;
import com.example.androidnetwork.R;
import com.example.model.Opinion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/14.
 * 他的问答
 */
public class FrAnswer extends Fragment{
    private ListView answer_listView;
    private List<Opinion> opinionList=new ArrayList<>();
    private IdeaAdapter adapter;
    private  View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

        view=inflater.inflate(R.layout.fr_answer,container,false);

        init();
        return view;
    }

    private void init() {
        answer_listView= (ListView) view.findViewById(R.id.answer_listView);
        adapter=new IdeaAdapter(getActivity(),opinionList);
        answer_listView.setAdapter(adapter);
    }
}
