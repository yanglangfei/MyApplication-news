package com.example.test;

import android.app.Activity;
import android.os.Bundle;

import com.example.androidnetwork.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by jucaipen on 16/6/21.
 */
public class VideoTest  extends Activity{
    private JCVideoPlayerStandard custom_videoplayer_standard;
    private  String videoUrl="http://hdl-w.quklive.com/live/w9458019977964845.flv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.ui_test);
        initView();

    }

    private void initView() {
        custom_videoplayer_standard= (JCVideoPlayerStandard) findViewById(R.id.custom_videoplayer_standard);
        custom_videoplayer_standard.setUp(videoUrl,"直播");
        custom_videoplayer_standard.thumbImageView.setImageResource(R.drawable.approve);
        custom_videoplayer_standard.startButton.performClick();


    }
}
