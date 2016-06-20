package com.example.FragmentItems;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.example.Live.LiveRoom;
import com.example.Live.LiveText;
import com.example.Live.LiveVideo;
import com.example.WritingLive.TextVideo;
import com.example.androidnetwork.R;

/**
 * Created by jucaipen on 16/5/10.
 * 直播
 */
public class FragmentLive extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private View view;
    private RadioButton rb_wz;
    private RadioButton rb_video;
    private LiveText liveText;//文字直播
    private LiveVideo liveVideo;//视频直播
    private FrameLayout frame_live;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_live, container, false);
        initView();
        select(0);
        return view;
    }

    private void select(int i) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hidtFragment(ft);   //先隐藏 Fragment

        switch (i) {
            case 0:
                if (liveText == null) {
                    liveText = new LiveText();
                    ft.add(R.id.frame_live, liveText).commit();
                } else {
                    ft.show(liveText).commit();
                }
                break;
            case 1:
                if (liveVideo == null) {
                    liveVideo = new LiveVideo();
                    ft.add(R.id.frame_live, liveVideo).commit();
                } else {
                    ft.show(liveVideo).commit();
                }
                break;
        }
    }

    private void hidtFragment(FragmentTransaction ft) {
        if (liveText != null) {
            ft.hide(liveText);
        }
        if (liveVideo != null) {
            ft.hide(liveVideo);
        }


    }

    private void initView() {
        frame_live = (FrameLayout) view.findViewById(R.id.frame_live);
        rb_wz = (RadioButton) view.findViewById(R.id.wz_live);
        rb_wz.setChecked(true);
        rb_wz.setOnClickListener(this);
        rb_video = (RadioButton) view.findViewById(R.id.video_live);
        rb_video.setOnClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent();
        if (rb_wz.isChecked()) {

            //文字直播
            intent.setClass(getActivity(), TextVideo.class);
        } else {
            //视频直播
            intent.setClass(getActivity(), LiveRoom.class);
        }
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wz_live:
                select(0);

                break;
            case R.id.video_live:
                select(1);

                break;
        }
    }
}
