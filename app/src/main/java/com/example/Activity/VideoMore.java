package com.example.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.androidnetwork.R;
import com.example.utils.NetUtils;
import com.example.adapter.VideoAdapter;
import com.example.model.Video;
import com.example.utils.JsonUtil;
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
 * 推荐视频 更多
 */
public class VideoMore extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView lv_videomore;
    private int page;
    private ImageButton video_back;
    private VideoAdapter videoAdapter;
    private List<Video> list = new ArrayList<>();
    private String videopath = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getvideolist";
    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videomore);

        init();
    }

    private void init() {
        lv_videomore = (ListView) findViewById(R.id.lv_videomore);
        lv_videomore.setOnItemClickListener(this);
        video_back = (ImageButton) findViewById(R.id.video_back);
        video_back.setOnClickListener(this);

        videoAdapter = new VideoAdapter(this, list);
        lv_videomore.setAdapter(videoAdapter);
        GetVideoDate();

    }

    private void GetVideoDate() {
        map.clear();
        map.put("isIndex", 1);
        map.put("page", page);
        RequestParams params = NetUtils.sendHttpGet(videopath, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {

                if (result != null && result.length() > 0) {
                    list = JsonUtil.getVide(result);
                    videoAdapter.setList(list);
                    videoAdapter.notifyDataSetChanged();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_back:
                this.finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(this, VideoPlay.class);
        startActivity(intent);
    }
}
