package com.example.Live;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.TeacherAdapter;
import com.example.androidnetwork.R;

/**
 * Created by Administrator on 2016/5/12.
 * 老师观点
 */
public class TeacherIdea extends Fragment {
    private View view;
    private ListView teacherListview;
    private TeacherAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.teacheridea, container, false);

        init();
        return view;
    }

    private void init() {
        teacherListview = (ListView) view.findViewById(R.id.teacherListview);
        adapter = new TeacherAdapter(getActivity());
        teacherListview.setAdapter(adapter);

    }
}
