package com.example.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Activity.MyAttention;
import com.example.Indent.MyOrder;
import com.example.MyRecord.MyRebate;
import com.example.Activity.Alreadybuy;
import com.example.Activity.Invitefriend;
import com.example.Activity.Login;
import com.example.Activity.MyCollect;
import com.example.Activity.MyGuard;
import com.example.MyRecord.Mymoney;
import com.example.Activity.PersonDate;
import com.example.Activity.SettingUp;
import com.example.Present.MyPresent;
import com.example.androidnetwork.R;
import com.example.view.CircleImageView;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by jucaipen on 16/5/5.
 */
public class FragmentThree extends Fragment implements View.OnClickListener {
    private View view;
    private CircleImageView uLogo;
    private TextView nickName;
    private RelativeLayout my_jifens;//积分
    private RelativeLayout my_money;//聚财币
    private RelativeLayout my_pay;//充值记录
    //private RelativeLayout my_rebate;//返利记录
    private RelativeLayout my_order;//订单记录
    private RelativeLayout my_study;//学习宝典
    private RelativeLayout my_answer;//我的问答
    private RelativeLayout teacher_center;//名师中心
    private RelativeLayout my_rebate;//我的返利
    //private RelativeLayout my_invite;//我的邀请
    private RelativeLayout my_attention;//我的关注
    private RelativeLayout action_me;//关注我的
    private ImageView my_setting;//设置
    private ImageView myBd7;
    private TextView tv_teacher;
    private ImageView myBd11;
    private TextView tv_my;
    private  String updataFace="http://192.168.1.134:8080/Jucaipen/jucaipen/faceupload";
    private  String uploadFace="http://www.jcplicai.com/ashx/AndroidUser.ashx?action=ImageUpload";


    private RelativeLayout rela_goteacher;//名师开通
    //private ImageView myBd12;
    //  private TextView tv_goteacher;
    private ImageView my_gift;
    private  String arry[]={"选择相册","选择相机"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragmentthree, container, false);

        init();
        return view;

    }

    private void init() {
        my_setting = (ImageView) view.findViewById(R.id.my_setting);
        uLogo= (CircleImageView) view.findViewById(R.id.uLogo);
        uLogo.setOnClickListener(this);
        my_setting.setOnClickListener(this);
        myBd7 = (ImageView) view.findViewById(R.id.myBd7);
        tv_teacher = (TextView) view.findViewById(R.id.tv_teacher);
        myBd11 = (ImageView) view.findViewById(R.id.myBd11);
        tv_my = (TextView) view.findViewById(R.id.tv_my);
        // myBd12 = (ImageView) view.findViewById(R.id.myBd12);
        //tv_goteacher = (TextView) view.findViewById(R.id.tv_goteacher);
//        rela_goteacher = (RelativeLayout) view.findViewById(R.id.rela_goteacher);
//        rela_goteacher.setOnClickListener(this);

        my_gift = (ImageView) view.findViewById(R.id.my_gift);
        my_gift.setOnClickListener(this);

        nickName = (TextView) view.findViewById(R.id.nickName);
        nickName.setOnClickListener(this);


        my_jifens = (RelativeLayout) view.findViewById(R.id.my_jifens);
        my_jifens.setOnClickListener(this);

        my_money = (RelativeLayout) view.findViewById(R.id.my_money);
        my_money.setOnClickListener(this);

        my_pay = (RelativeLayout) view.findViewById(R.id.my_pay);
        my_pay.setOnClickListener(this);

//        my_rebate = (RelativeLayout) view.findViewById(R.id.my_rebate);
//        my_rebate.setOnClickListener(this);

        my_order = (RelativeLayout) view.findViewById(R.id.my_order);
        my_order.setOnClickListener(this);

        my_study = (RelativeLayout) view.findViewById(R.id.my_study);
        my_study.setOnClickListener(this);

        my_answer = (RelativeLayout) view.findViewById(R.id.my_answer);
        my_answer.setOnClickListener(this);

        teacher_center = (RelativeLayout) view.findViewById(R.id.teacher_center);
        teacher_center.setOnClickListener(this);

//        my_invite = (RelativeLayout) view.findViewById(R.id.my_invite);
//        my_invite.setOnClickListener(this);

        my_attention = (RelativeLayout) view.findViewById(R.id.my_attention);
        my_attention.setOnClickListener(this);

        action_me = (RelativeLayout) view.findViewById(R.id.action_me);
        action_me.setOnClickListener(this);

        my_rebate= (RelativeLayout) view.findViewById(R.id.my_rebate);
        my_rebate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.nickName:
                //判断用户登录   1 未登录   2 已登录
                int type = 1;
                if (type == 1) {
                    intent.setClass(getActivity(), Login.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getActivity(), PersonDate.class);
                    startActivity(intent);
                }


                break;
            case R.id.my_jifens:
//                intent.setClass(getActivity(), Login.class);
                intent.putExtra("type", 1);
                intent.setClass(getActivity(), Mymoney.class);
                startActivity(intent);
                // Toast.makeText(getActivity(), "积分", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_money:
                intent.putExtra("type", 2);
                intent.setClass(getActivity(), Mymoney.class);
                startActivity(intent);
                // Toast.makeText(getActivity(), "聚财币", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_pay:
                intent.setClass(getActivity(), MyOrder.class);
                startActivity(intent);
//                Toast.makeText(getActivity(), "账单记录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_rebate:
                //我的返利
                intent.setClass(getActivity(), MyRebate.class);
                startActivity(intent);
                break;

            case R.id.my_order:

                intent.setClass(getActivity(), Invitefriend.class);
                startActivity(intent);
                //Toast.makeText(getActivity(), "我的邀请", Toast.LENGTH_SHORT).show();
                break;

            case R.id.my_study:
                intent.setClass(getActivity(), Alreadybuy.class);
                startActivity(intent);
                //Toast.makeText(getActivity(), "学习宝典", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_answer:
                Toast.makeText(getActivity(), "我的问答", Toast.LENGTH_SHORT).show();
                break;


            case R.id.teacher_center:
                // Toast.makeText(getActivity(), "我的守护", Toast.LENGTH_SHORT).show();
                intent.setClass(getActivity(), MyGuard.class);
                startActivity(intent);
                break;

            case R.id.my_attention:
                intent.setClass(getActivity(), MyAttention.class);
                startActivity(intent);
                // Toast.makeText(getActivity(), "我的关注", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_me:
                intent.setClass(getActivity(), MyCollect.class);
                startActivity(intent);
                //Toast.makeText(getActivity(), "我的收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_setting:
                intent.setClass(getActivity(), SettingUp.class);
                startActivity(intent);
                break;
//            case R.id.rela_goteacher:
//                intent.setClass(getActivity(), ApplyforDredge.class);
//                startActivity(intent);
//                Toast.makeText(getActivity(), "名师开通", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.my_gift:
                intent.setClass(getActivity(), MyPresent.class);
                startActivity(intent);
                //Toast.makeText(getActivity(), "我的礼品", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.uLogo:
              AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setSingleChoiceItems(arry, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        String w=arry[which];
                        dialog.cancel();
                        if(w.equals("选择相册")){
                            GalleryFinal.openGallerySingle(100, new GalleryFinal.OnHanlderResultCallback() {
                                @Override
                                public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                                    uLogo.setImageURI(Uri.parse(resultList.get(0).getPhotoPath()));
                                    uploadFile(resultList.get(0).getPhotoPath());
                                }

                                @Override
                                public void onHanlderFailure(int requestCode, String errorMsg) {

                                }
                            });

                        }else{
                            GalleryFinal.openCamera(200, new GalleryFinal.OnHanlderResultCallback() {
                                @Override
                                public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                                    uLogo.setImageURI(Uri.parse(resultList.get(0).getPhotoPath()));
                                    uploadFile(resultList.get(0).getPhotoPath());

                                }

                                @Override
                                public void onHanlderFailure(int requestCode, String errorMsg) {

                                }
                            });
                        }
                    }
                });
                AlertDialog dialog = alertDialog.create();
                dialog.show();
                break;

        }
    }


    public void uploadFile(String path){
        RequestParams params=new RequestParams(uploadFace);
        File f=new File(path);
        params.setMultipart(true);
        params.addBodyParameter("imgFile",new File(path));
        x.http().post(params, new Callback.CacheCallback<String>() {

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object=new JSONObject(result);
                    boolean res=object.getBoolean("Result");
                    if(res){
                        //更新文件上传地址
                        String imageUrl=object.getString("ImageUrl");
                        updateFilePath(imageUrl);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }


    public  void updateFilePath(String path){
        RequestParams p=new RequestParams(updataFace);
        p.addParameter("imageFace",path);
        p.addParameter("userId",6750);
        x.http().post(p, new Callback.CacheCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Toast.makeText(getActivity(), ""+result, Toast.LENGTH_SHORT).show();

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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });

    }


}
