package com.example.TeacherDate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/14.
 *
 * 视频直播
 */
public class FrVideoPlay extends Fragment{
    private  View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

        view=inflater.inflate(R.layout.fr_videoplay,container,false);
        return view;
    }
}
