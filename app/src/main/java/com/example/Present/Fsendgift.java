package com.example.Present;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/20.
 *
 * 送出的礼品
 */
public class Fsendgift extends Fragment{
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

        view=inflater.inflate(R.layout.fsendgift,container,false);
        return view;
    }
}
