package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.MoodAdapter;
import com.example.adapter.OldLiveAdapter;
import com.example.adapter.PersonAdapter;
import com.example.adapter.TodayLive;
import com.example.androidnetwork.R;
import com.example.model.Famous;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/7.
 * <p/>
 * 直播室 简介
 */
public class Radiointro extends Fragment {
    private RecyclerView lv_today;
    private RecyclerView lv_moods;
    private ListView lv_old;
    //人气名家适配器
    private PersonAdapter personAdapter;
    private List<Famous> list = new ArrayList<>();



    private TodayLive todayLive;
    private MoodAdapter moodAdapter;
    private OldLiveAdapter oldLiveAdapter;
    private String programUrl="http://"+ StringUntils.getHostName()+"/Jucaipen/jucaipen/getguest";

    private View view;
    private Map<String, Object> map=new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.radiointro, container, false);

        init();
        return view;
    }

    private void init() {

        //获取今日直播
       // GetTodyLive();
        lv_today = (RecyclerView) view.findViewById(R.id.lv_today);
        lv_moods = (RecyclerView) view.findViewById(R.id.lv_moods);
        lv_old = (ListView) view.findViewById(R.id.lv_old);


        todayLive = new TodayLive();
        moodAdapter = new MoodAdapter();
        oldLiveAdapter = new OldLiveAdapter(getActivity());



        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        lv_today.setLayoutManager(lm);
        lv_today.setAdapter(todayLive);

        LinearLayoutManager lm1 = new LinearLayoutManager(getActivity());
        lm1.setOrientation(LinearLayoutManager.HORIZONTAL);
        lv_moods.setLayoutManager(lm1);
        lv_moods.setAdapter(moodAdapter);


        lv_old.setAdapter(oldLiveAdapter);


    }


    //获取今日直播
    private void GetTodyLive() {
        map.clear();
        map.put("studioId",6);
        map.put("typeId",0);

        RequestParams params= NetUtils.sendHttpGet(programUrl,map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                Toast.makeText(getActivity(), ""+result, Toast.LENGTH_SHORT).show();
                if (result!=null){


                    //[{"id":3,"headFace":"http://img.jucaipen.com/jucaipenStudy/2016/5/24/2016524161226.jpg",
                    // "nickName":"123","leavel":"1234","hoby":"asda"},

                    Log.i("111", "onSuccess: "+result);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
