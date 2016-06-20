package com.example.Live;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.androidnetwork.R;
import com.example.model.TextVideo;
import com.example.utils.JsonUtil;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;
import com.example.adapter.TextAdapter;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/8.
 * 文字直播
 */
public class LiveText extends Fragment {
    private ListView live_lv;
    private View view;
    private String tvurl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getlivelist";
    private TextAdapter adapter;
    private Map<String, Object> map = new HashMap<>();
    private List<TextVideo> list=new ArrayList<>();
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.livetext, container, false);

        init();

        return view;
    }

    private void init() {
        GetTvDate();
        live_lv = (ListView) view.findViewById(R.id.live_lv);
        adapter = new TextAdapter(getActivity(),list);
        live_lv.setAdapter(adapter);


    }

    private void GetTvDate() {
        map.put("liveType", 0);
        map.put("page", page);
        RequestParams params = NetUtils.sendHttpGet(tvurl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    list= JsonUtil.getTextVideo(result);
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
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
