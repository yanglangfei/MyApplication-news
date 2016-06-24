package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.thirdUtils.SinaUtils;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

/**
 * Created by jucaipen on 16/6/24.
 */
public class SinaTest extends Activity {
    private AuthInfo authInfo;
    private  SsoHandler ssoHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        authInfo= SinaUtils.getInstance(this);
        ssoHandler=new SsoHandler(this,authInfo);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(ssoHandler!=null){
            ssoHandler.authorizeCallBack(requestCode,resultCode,data);
        }

    }
}
