package com.example.thirdUtils;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import com.example.androidnetwork.R;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;

/**
 * Created by jucaipen on 16/6/24.
 */
public class SinaUtils {

    private static AuthInfo authInfo;
    private  Oauth2AccessToken mAccessToken;
    private   WeiboAuthListener listener=new WeiboAuthListener() {
        @Override
        public void onComplete(Bundle bundle) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
            if (mAccessToken.isSessionValid()) {
                // 保存 Token 到 SharedPreferences

            } else {
                // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
                String code = bundle.getString("code", "");

            }
        }

        @Override
        public void onWeiboException(WeiboException e) {

        }

        @Override
        public void onCancel() {

        }
    };

    private RequestListener requestListener=new RequestListener() {
        @Override
        public void onComplete(String s) {

        }

        @Override
        public void onWeiboException(WeiboException e) {

        }
    };

    /**
     * @param context
     * @return  获取新浪API接口对象类
     */
    public  static AuthInfo getInstance(Context context){
        if(authInfo==null){
            authInfo=new AuthInfo(context,Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        }
        return  authInfo;
    }


    /*
       web 授权
     */
    public void web(Activity activity){
        SsoHandler ssoHandler=new SsoHandler(activity,authInfo);
        ssoHandler.authorizeWeb(listener);
    }


    /*
       so 授权
     */
    public void sso(Activity activity){
        SsoHandler ssoHandler=new SsoHandler(activity,authInfo);
        ssoHandler.authorizeClientSso(listener);
    }


    /*
      all In one方式授权


      此种授权方式会根据手机是否安装微博客户端来决定使用sso授权还是网页授权，
      如果安装有微博客户端 则调用微博客户端授权，否则调用Web页面方式授权
     */

    public  void allWay(Activity activity){
        SsoHandler ssoHandler=new SsoHandler(activity,authInfo);
        ssoHandler.authorize(listener);
    }


    public void share(Context context){
        // 1. 获取要发送的图片
        Drawable drawable =ContextCompat.getDrawable(context,R.mipmap.ic_launcher);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        // 2. 调用接口发送微博
        WeiboParameters params = new WeiboParameters("");
        params.put("access_token", mAccessToken.getToken());
        params.put("status",       "通过API发送微博-upload");
        params.put("visible",      "0");
        params.put("list_id",      "");
        params.put("pic",          bitmap);
        params.put("lat",          "14.5");
        params.put("long",         "23.0");
        params.put("annotations",  "");
        AsyncWeiboRunner runner=new AsyncWeiboRunner(context);
        runner.requestAsync(
                "https://api.weibo.com/2/statuses/upload.json",
                params,
                "POST",
                requestListener);

    }


}
