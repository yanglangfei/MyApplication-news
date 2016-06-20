package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.androidnetwork.R;
import com.example.model.SmallType;
import com.example.utils.NetUtils;
import com.example.adapter.SchoolAdapter;
import com.example.model.SchoolKnowledge;
import com.example.utils.StringUntils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/30.
 * <p/>
 * 证券
 */
public class SchoolSecurity extends Activity {
    private ListView lv_zq;
    private SchoolAdapter adapter;
    private List<SchoolKnowledge> knowledges = new ArrayList<>();
    private String path = "http://192.168.1.134:8080/Jucaipen/jucaipen/getbasetype?bigId=0";
    private String studyUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getbasetype";
    private Map<String, Object> map = new HashMap<>();
    private int btnId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schoolsecurity);
        initView();
    }

    private void initView() {
         btnId = getIntent().getIntExtra("buttonId", -1);

        //获取分类
        if (btnId > 0) {
            GetType(btnId);
        }
        lv_zq = (ListView) findViewById(R.id.lv_zq);
        adapter = new SchoolAdapter(knowledges, this);
        lv_zq.setAdapter(adapter);


    }

    private void GetType(int buttonId) {
        map.clear();
        map.put("bigId", buttonId);
        map.put("leavel", 1);
        RequestParams params = NetUtils.sendHttpGet(studyUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    knowledges=pareJson(result,btnId);
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

    public List<SchoolKnowledge> pareJson(String str, int bigId) {
        knowledges.clear();
        try {
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                //id      bif\gType
                SchoolKnowledge sk = new SchoolKnowledge();



                JSONObject obj1 = array.getJSONObject(i);
                int sId=obj1.getInt("id");
                String bigType = obj1.getString("bigType");

                //  sId
                JSONArray item = obj1.getJSONArray("item");
                List<SmallType> sts = new ArrayList<>();
                for (int j = 0; j < item.length(); j++) {
                    JSONObject obj2 = item.getJSONObject(j);
                    int xxId=obj2.getInt("sId");
                    String smallType = obj2.getString("smallType").trim();
                    SmallType st = new SmallType();
                    st.smallType = smallType;
                    st.xxId=xxId;
                    sts.add(st);
                }
                sk.setSmallId(sId);
                sk.setBigType(bigType);
                sk.setSmallType(sts);
                knowledges.add(sk);
            }
            return  knowledges;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;

    }

}
