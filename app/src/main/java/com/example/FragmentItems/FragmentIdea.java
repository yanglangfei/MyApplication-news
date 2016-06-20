package com.example.FragmentItems;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.Activity.IdeaSee;
import com.example.adapter.IdeaAdapter;
import com.example.androidnetwork.R;
import com.example.model.Opinion;
import com.example.utils.JsonUtil;
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
 * Created by jucaipen on 16/5/6.
 * <p/>
 * 主页    －－观点模块
 */
public class FragmentIdea extends Fragment implements AdapterView.OnItemClickListener {
    private ListView ideaListivew;
    private IdeaAdapter adapter;
    private String ideaUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getidealist";
    private View view;
    private Map<String, Object> map = new HashMap<>();
    private List<Opinion> opinionList=new ArrayList<>();
    private int page = 1;
    private Context context=getActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        context=getContext();

        view = inflater.inflate(R.layout.idea, container, false);

        init();
        Getidea();
        return view;
    }

    private void Getidea() {
        map.put("isIndex", 1);
        map.put("page", page);
        RequestParams params = NetUtils.sendHttpGet(ideaUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                opinionList=JsonUtil.getOpinion(result);
                adapter.setList(opinionList);
                adapter.notifyDataSetChanged();


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

    private void init() {
        ideaListivew = (ListView) view.findViewById(R.id.ideaListivew);
        adapter = new IdeaAdapter(context,opinionList);
        ideaListivew.setAdapter(adapter);
        ideaListivew.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        switch (parent.getId()) {
            case R.id.ideaListivew:
                intent.setClass(getActivity(), IdeaSee.class);
                startActivity(intent);
                break;
        }

    }
}
