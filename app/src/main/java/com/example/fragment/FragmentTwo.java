package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.example.androidnetwork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jucaipen on 16/5/5.
 * 演播室
 */
public class FragmentTwo extends Fragment implements View.OnClickListener {

    private View view;
    private Radiointro radiointro;//简介
    private RadioComm radioComm;//评论
    private RadioButton intro_radio;
    private RadioButton comm_radio;
    private RadioGroup radio_group;
    private LinearLayout framelayout;
    private List<Fragment> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.radiotwo, container, false);


        init();
        select(0);

        return view;
    }

    private void select(int i) {
        FragmentManager fm = getFragmentManager();
        //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务
        hidtFragment(ft);   //先隐藏 Fragment
        switch (i) {
            case 0:
                if (radiointro == null) {
                    radiointro = new Radiointro();
                    ft.add(R.id.framelayout, radiointro).commit();
                } else {
                    ft.show(radiointro).commit();
                }
                break;
            case 1:
                if (radioComm == null) {
                    radioComm = new RadioComm();
                    ft.add(R.id.framelayout, radioComm).commit();
                } else {
                    ft.show(radioComm).commit();
                }
                break;
            default:
                break;
        }

    }

    private void hidtFragment(FragmentTransaction ft) {
        if (radiointro != null) {
            ft.hide(radiointro);
        }
        if (radioComm != null) {
            ft.hide(radioComm);
        }


    }

    private void init() {
        framelayout= (LinearLayout) view.findViewById(R.id.framelayout);
        intro_radio = (RadioButton) view.findViewById(R.id.intro_radio);
        intro_radio.setOnClickListener(this);
        comm_radio = (RadioButton) view.findViewById(R.id.comm_radio);
        comm_radio.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.intro_radio:
                select(0);
                break;
            case R.id.comm_radio:
                select(1);
                break;
        }

    }
}

