package com.example.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidnetwork.R;
import com.example.thirdUtils.QQLoginUtil;
import com.example.thirdUtils.WeiXinLoginUtils;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;
import com.example.receiver.RecQQLogin;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/31.
 * 登录
 */
public class Login extends Activity implements View.OnClickListener {

    private ImageView login_back;
    private TextView tv_register;
    private TextView forget_pass;
    private String loginUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/login";
    private ImageButton btn_login;
    private EditText edt_phone;
    private EditText edt_passWord;
    private Map<String, Object> map = new HashMap<>();
    private RecQQLogin qqReceiver;
    private  ImageButton ib_qq;
    private  ImageButton ib_weixin;
    private  ImageButton ib_sina;
    private boolean isReginQq;
    private boolean isReginWeixin;
    private RecQQLogin weiXin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        init();
    }

    private void init() {
        login_back = (ImageView) findViewById(R.id.login_back);
        login_back.setOnClickListener(this);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
        forget_pass = (TextView) findViewById(R.id.forget_pass);
        forget_pass.setOnClickListener(this);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_passWord = (EditText) findViewById(R.id.edt_passWord);
        btn_login = (ImageButton) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        ib_qq= (ImageButton) findViewById(R.id.ib_qq);
        ib_qq.setOnClickListener(this);
        ib_weixin= (ImageButton) findViewById(R.id.ib_weixin);
        ib_weixin.setOnClickListener(this);
        ib_sina= (ImageButton) findViewById(R.id.ib_sina);
        ib_sina.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.login_back:
                this.finish();
                break;
            case R.id.tv_register:
                intent.setClass(this, Register.class);
                startActivity(intent);
                break;
            case R.id.forget_pass:
                intent.setClass(this, FoundPassword.class);
                startActivity(intent);
                break;
            case R.id.btn_login:

                String phone = edt_phone.getText().toString().trim();
                String pass = edt_passWord.getText().toString().trim();
                if (phone.length() > 0 && pass.length() > 0) {
                    UserLogin(phone, pass);
                } else {
                    Toast.makeText(Login.this, "账号或密码有误", Toast.LENGTH_SHORT).show();
                }

                break;
            case  R.id.ib_qq:
                reginQQ();
                break;
            case  R.id.ib_weixin:
                reginWeiXin();
                break;
            case  R.id.ib_sina:
                reginSINA();
                break;
            default:
                break;
        }
    }

    private void UserLogin(String phone, String pass) {
        map.put("userId", -1);
        map.put("userName", phone);
        map.put("password", pass);

        RequestParams params = NetUtils.sendHttpGet(loginUrl, map);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null && result.length() > 0) {
                    //解析 保存数据即可
                    Toast.makeText(Login.this, "" + result, Toast.LENGTH_SHORT).show();

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


    private void reginQQ() {
        // QQ登录
        QQLoginUtil qqLogin = QQLoginUtil.getInstance();
        qqLogin.initTencen(this);
        qqLogin.qqLogin(this);
        IntentFilter filter = new IntentFilter("com.receiverQQ");
        qqReceiver = new RecQQLogin() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                String qqOpenId = arg1.getStringExtra("qqOpenId");
                if (qqOpenId.length() > 0) {
                    isReginQq = true;
                    Toast.makeText(Login.this, ""+qqOpenId, Toast.LENGTH_SHORT).show();
                   // new qqLoginApplication(qqOpenId).execute(0);
                }
            }

        };
        this.registerReceiver(qqReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isReginQq) {
            this.unregisterReceiver(qqReceiver);
        }
        if (isReginWeixin) {
            this.unregisterReceiver(weiXin);
        }
    }

    private void reginWeiXin() {
        // 微信登录
        WeiXinLoginUtils.getInstance(this).sendAuthMsg();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.receiverWeiXin");
        weiXin = new RecQQLogin() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                String wexinOpenId = arg1.getStringExtra("weixinOpenId");

                if (wexinOpenId.length() > 0) {
                    isReginWeixin = true;
                    Toast.makeText(Login.this, ""+wexinOpenId, Toast.LENGTH_SHORT).show();
                   // new qqLoginApplication(wexinOpenId).execute(1);
                }
            }
        };
        this.registerReceiver(weiXin, filter);

    }

    private void reginSINA() {
        // 新浪登录
    }


}
