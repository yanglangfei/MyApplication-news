package com.example.FragmentItems;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.Activity.HotMore;
import com.example.Activity.VideoMore;
import com.example.adapter.HotAdapter;
import com.example.adapter.VideoAdapter;
import com.example.androidnetwork.R;
import com.example.model.AdviseBean;
import com.example.utils.NetUtils;
import com.example.view.MyGridView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.Activity.FamousPlain;
import com.example.Activity.HotCareful;
import com.example.Activity.PersonMore;
import com.example.Activity.VideoPlay;
import com.example.adapter.MyAdapter;
import com.example.adapter.PersonAdapter;
import com.example.model.Famous;
import com.example.model.Press;
import com.example.model.Video;
import com.example.utils.JsonUtil;
import com.example.utils.StringUntils;
import com.example.view.TestListView;
import com.nostra13.universalimageloader.core.ImageLoader;

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
 * 主页  －－－精选模块
 */
public class FragmentChoinces extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private View view;
    private ViewPager hotviewpagr;
    private MyAdapter adapter;
    //存放引到图片数组
    private List<View> list = new ArrayList<View>();
    private Map<String, Object> map = new HashMap<>();
    private HotAdapter hotAdapter;
    private VideoAdapter videoAdapter;
    private PersonAdapter personAdapter;
    private TestListView hotlist;
    /*
      广告接口
    */
    public String ADVISE_PATH = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getadvise";
    private String hoturl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getidealist";
    private String videopath = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getvideolist";
    private String personUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getfamous";
    private List<Famous> famousList = new ArrayList<>();
    //private List<Opinion> opinionList = new ArrayList<>();
   // private IdeaAdapter ideaAdapter;
    private TestListView personList;
    private MyGridView gridvideo;
    private List<Video> videoList = new ArrayList<Video>();
    private ImageLoader loder = ImageLoader.getInstance();
    private ImageView hot_more;
    private ImageView video_more;
    private ImageView moods_more;
    private List<Press> pressList = new ArrayList<>();

    private int current;

    //执行图片轮播
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            hotviewpagr.setCurrentItem(current);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.choinces, container, false);
        initView();
        initData();
        return view;
    }

    private void initData() {
        //获取首页广告
        map.put("type", 1);
        RequestParams params = NetUtils.sendHttpGet(ADVISE_PATH, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null && result.length() > 0) {
                    Gson gson = new Gson();
                    List<AdviseBean> advises = gson.fromJson(result, new TypeToken<List<AdviseBean>>() {
                    }.getType());
                    for (AdviseBean a : advises) {
                        String url = a.getImageUrl();
                        ImageView iv = new ImageView(getActivity());
                        iv.setScaleType(ImageView.ScaleType.FIT_XY);
                        Glide.with(getActivity()).load(url)
                                .placeholder(R.drawable.approve)
                                .into(iv);
                        list.add(iv);
                    }
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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
        //获取今日观点参数
        GetIdea();

        //获取首页推荐视频
        GetVideoDate();
        //人气名家
        GetPerson();
    }

    private void GetPerson() {
        map.clear();
        map.put("isIndex", 0);
        map.put("userId", 0 + "");
        RequestParams params = NetUtils.sendHttpGet(personUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null && result.length() > 0) {
                    famousList = JsonUtil.getFamous(result);
                    personAdapter.setList(famousList);
                    personAdapter.notifyDataSetChanged();
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

    private void GetVideoDate() {
        map.clear();
        map.put("isIndex", 0);
        RequestParams params = NetUtils.sendHttpGet(videopath, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null && result.length() > 0) {
                    videoList = JsonUtil.getVide(result);
                    videoAdapter.setList(videoList);
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

    private void GetIdea() {
        map.clear();
        map.put("isIndex", 0);
        map.put("page", 1);
        RequestParams params = NetUtils.sendHttpGet(hoturl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                //[{"page":0,"totlePage":0,"id":796,"insertDate":null,"title":"加入国际指数A股能获得什么？",
                // "hits":0,"goods":0,"teacherId":0,"nickName":null,"level":null,"headFace":null,"isV":0}
                if (result != null && result.length() > 0) {
                    pressList = JsonUtil.getpress(result);
                    hotAdapter.setList(pressList);
                    hotAdapter.notifyDataSetChanged();
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

    private void initView() {
        hotviewpagr = (ViewPager) view.findViewById(R.id.hotviewpagr);
        hotlist = (TestListView) view.findViewById(R.id.hotlist);
        gridvideo = (MyGridView) view.findViewById(R.id.gridvideo);
        personList = (TestListView) view.findViewById(R.id.personList);
        hot_more = (ImageView) view.findViewById(R.id.hot_more);
        hot_more.setOnClickListener(this);
        video_more = (ImageView) view.findViewById(R.id.video_more);
        video_more.setOnClickListener(this);
        moods_more = (ImageView) view.findViewById(R.id.moods_more);
        moods_more.setOnClickListener(this);


        //创建多个适配器
        adapter = new MyAdapter(list);
        hotviewpagr.setAdapter(adapter);

       // ideaAdapter=new IdeaAdapter(getActivity(),opinionList);
        hotAdapter = new HotAdapter(getActivity(), pressList);
        videoAdapter = new VideoAdapter(getActivity(), videoList);
        personAdapter = new PersonAdapter(getActivity(), famousList);

        hotlist.setAdapter(hotAdapter);

        gridvideo.setAdapter(videoAdapter);
        personList.setAdapter(personAdapter);


        gridvideo.setOnItemClickListener(this);
        hotlist.setOnItemClickListener(this);
        personList.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent();
        switch (parent.getId()) {
            case R.id.gridvideo:
                intent.putExtra("id", videoList.get(position).getId());
                intent.putExtra("videoUrl", videoList.get(position).getVideoUrl());
                intent.putExtra("classId", videoList.get(position).getClassId());
                intent.setClass(getActivity(), VideoPlay.class);
                startActivity(intent);
                break;
            case R.id.hotlist:
                intent.putExtra("id",pressList.get(position).getId());
               // intent.setClass(getActivity(), IdeaSee.class);
                intent.setClass(getActivity(), HotCareful.class);
                startActivity(intent);
//                int newsId = opinionList.get(position).getId();
//                intent.putExtra("newsId", newsId);
             //   intent.setClass(getActivity(), HotCareful.class);
//                startActivity(intent);
                break;
            case R.id.personList:
                intent.setClass(getActivity(), FamousPlain.class);
                startActivity(intent);
                // Toast.makeText(getActivity(), "人气名家Item", Toast.LENGTH_SHORT).show();
                break;
        }


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.hot_more:
                intent.setClass(getActivity(), HotMore.class);
                startActivity(intent);
                //Toast.makeText(getActivity(), "今日热点更多", Toast.LENGTH_SHORT).show();
                break;
            case R.id.video_more:
                intent.setClass(getActivity(), VideoMore.class);
                startActivity(intent);
                //Toast.makeText(getActivity(), "推荐视频更多", Toast.LENGTH_SHORT).show();
                break;
            case R.id.moods_more:
                // Toast.makeText(getActivity(), "人气名家更多", Toast.LENGTH_SHORT).show();
                intent.setClass(getActivity(), PersonMore.class);
                startActivity(intent);
                break;
        }
    }

}
