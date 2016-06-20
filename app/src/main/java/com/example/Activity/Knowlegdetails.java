package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.androidnetwork.R;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/13.
 */
public class Knowlegdetails extends Activity {
    private TextView know_details;
    private Map<String, Object> map = new HashMap<>();
    private String detaUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getknowdetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowlegdetails);

        init();
    }

    private void init() {
        int id = getIntent().getIntExtra("id", -1);
        int classId = getIntent().getIntExtra("classId", -1);
        know_details = (TextView) findViewById(R.id.know_details);
        if (id > 0 && classId > 0) {

            GetDetailes(id, classId);
        }


    }

    private void GetDetailes(int classId, int id) {
        map.put("knowId", id);
        map.put("classId", classId);

        RequestParams params = NetUtils.sendHttpGet(detaUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        JSONObject object = new JSONObject(result);
                        String body = object.getString("body");
                        know_details.setText(Html.fromHtml(body));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
