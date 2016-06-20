package com.example.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
 * Created by Administrator on 2016/5/19.
 * <p/>
 * 设置
 */
public class SettingUp extends Activity implements View.OnClickListener {

    private RelativeLayout update_password;
    private RelativeLayout update_phone;
    private ImageButton setting_back;
    private RelativeLayout other_count;
    private RelativeLayout about_we;
    private RelativeLayout see_code;
    private String codeUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/updateversion";
    private Button btn_exit;
    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingup);

        init();
    }

    private void init() {
        update_password = (RelativeLayout) findViewById(R.id.update_password);
        update_password.setOnClickListener(this);
        update_phone = (RelativeLayout) findViewById(R.id.update_phone);
        update_phone.setOnClickListener(this);
        setting_back = (ImageButton) findViewById(R.id.setting_back);
        setting_back.setOnClickListener(this);
        other_count = (RelativeLayout) findViewById(R.id.other_count);
        other_count.setOnClickListener(this);
        about_we = (RelativeLayout) findViewById(R.id.about_we);
        about_we.setOnClickListener(this);
        see_code = (RelativeLayout) findViewById(R.id.see_code);
        see_code.setOnClickListener(this);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.update_password:

                intent.setClass(this, UpdatePassword.class);
                startActivity(intent);
                break;
            case R.id.update_phone:
                intent.setClass(this, ChangePhone.class);
                startActivity(intent);
                break;
            case R.id.setting_back:
                this.finish();
                break;
            case R.id.other_count:
                Toast.makeText(SettingUp.this, "其他第三方账号", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_we:
                Toast.makeText(SettingUp.this, "关于我们", Toast.LENGTH_SHORT).show();
                intent.setClass(this, AboutWe.class);
                startActivity(intent);
                break;
            case R.id.see_code:
                //检测新版本
                DetectionCode();

                Toast.makeText(SettingUp.this, "检测新版本", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_exit:
                Toast.makeText(SettingUp.this, "退出登录", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void DetectionCode() {

        RequestParams params = NetUtils.sendHttpGet(codeUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                Toast.makeText(SettingUp.this, "版本检测" + result, Toast.LENGTH_SHORT).show();
                if (result != null) {
                    try {
                        JSONObject object = new JSONObject(result);
                        //{"ret_code":0,"versionCode":版本号,"apkUrl":"apk下载URL"}

                        int code = object.getInt("ret_code");
                        int versionCode = object.getInt("versionCode");
                        String apkUrl = object.getString("apkUrl");


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
