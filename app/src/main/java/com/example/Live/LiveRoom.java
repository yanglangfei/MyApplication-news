package com.example.Live;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.androidnetwork.R;
import com.example.utils.MarqueeTextView;
import com.example.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/11.
 */
public class LiveRoom extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private RadioGroup room_group;
    private ViewPager room_viewpager;
    private InteractList interactList;//互动列表
    private NewList newList;//最新榜单
    private TeacherIdea teacherIdea;//老师观点
    private UserSeat userSeat;//用户席位
    private List<Fragment> list = new ArrayList<Fragment>();
    private RadioButton radio_interact, radio_seat, radio_idea, radio_news;
    private ViewPagerAdapter adapter;
    private MarqueeTextView user_round;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_liveroom_main);
        init();
    }

    private void init() {
        interactList = new InteractList();
        userSeat = new UserSeat();
        teacherIdea = new TeacherIdea();
        newList = new NewList();

        list.add(interactList);
        list.add(userSeat);
        list.add(teacherIdea);
        list.add(newList);

        room_group = (RadioGroup) findViewById(R.id.room_group);
        room_group.setOnCheckedChangeListener(this);

        user_round= (MarqueeTextView) findViewById(R.id.user_round);
        user_round.setText("加载图片时先查看缓存中时候存在该图片，如果存在则返回该图片，否则先加载载一个默认的占位图片");
        radio_interact = (RadioButton) findViewById(R.id.radio_interact);
        radio_seat = (RadioButton) findViewById(R.id.radio_seat);
        radio_idea = (RadioButton) findViewById(R.id.radio_idea);
        radio_news = (RadioButton) findViewById(R.id.radio_news);

        room_viewpager = (ViewPager) findViewById(R.id.room_viewpager);
        room_viewpager.setOnPageChangeListener(this);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        room_viewpager.setCurrentItem(0);
        room_viewpager.setAdapter(adapter);

    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.radio_interact:
                room_viewpager.setCurrentItem(0, false);

                break;
            case R.id.radio_seat:
                room_viewpager.setCurrentItem(1, false);

                break;
            case R.id.radio_idea:
                room_viewpager.setCurrentItem(2, false);

                break;
            case R.id.radio_news:
                room_viewpager.setCurrentItem(3, false);

                break;

        }

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

        switch (i) {
            case 0:
                room_group.check(R.id.radio_interact);
                break;
            case 1:
                room_group.check(R.id.radio_seat);
                break;
            case 2:
                room_group.check(R.id.radio_idea);
                break;
            case 3:
                room_group.check(R.id.radio_news);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
