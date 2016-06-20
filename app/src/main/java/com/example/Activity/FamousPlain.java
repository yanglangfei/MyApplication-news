package com.example.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.TeacherDate.FmHomePage;
import com.example.TeacherDate.FrViewpoint;
import com.example.TeacherDate.FrAnswer;
import com.example.TeacherDate.FrVideoPlay;
import com.example.TeacherDate.FrWriterLive;
import com.example.adapter.ViewPagerAdapter;
import com.example.androidnetwork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/14.
 * 老师详情界面
 */
public class FamousPlain extends FragmentActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private TextView tv_plain;
    private RadioGroup teacher_group;
    private RadioButton rad_homepage;
    private RadioButton rad_writing;
    private RadioButton rad_video;
    private RadioButton rad_viewpoint;
    private RadioButton rad_answer;
    private ViewPager famous_viewpager;
    private FmHomePage fmHomePage;
    private FrWriterLive frWriterLive;
    private FrVideoPlay frVideoPlay;
    private FrViewpoint frViewpoint;
    private FrAnswer frAnswer;
    private List<Fragment> list = new ArrayList<Fragment>();
    private ViewPagerAdapter pagerAdapter;
    private ImageButton ibt_ask;
    private ImageButton ibt_reward;
    private ImageButton ibt_attention;
    private ImageButton ibt_guard;
    private ImageView teacher_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_famous_detaile);

        init();

    }

    private void init() {
        fmHomePage = new FmHomePage();
        frWriterLive = new FrWriterLive();
        frVideoPlay = new FrVideoPlay();
        frViewpoint = new FrViewpoint();
        frAnswer = new FrAnswer();
        list.add(fmHomePage);
        list.add(frWriterLive);
        list.add(frVideoPlay);
        list.add(frViewpoint);
        list.add(frAnswer);

        teacher_back = (ImageView) findViewById(R.id.teacher_back);
        teacher_back.setOnClickListener(this);
        tv_plain = (TextView) findViewById(R.id.tv_plain);
        teacher_group = (RadioGroup) findViewById(R.id.teacher_group);
        rad_homepage = (RadioButton) findViewById(R.id.rad_homepage);
        rad_writing = (RadioButton) findViewById(R.id.rad_writing);
        rad_video = (RadioButton) findViewById(R.id.rad_video);
        rad_viewpoint = (RadioButton) findViewById(R.id.rad_viewpoint);
        rad_answer = (RadioButton) findViewById(R.id.rad_answer);
        famous_viewpager = (ViewPager) findViewById(R.id.famous_viewpager);
        ibt_ask = (ImageButton) findViewById(R.id.ibt_ask);
        ibt_ask.setOnClickListener(this);
        ibt_reward = (ImageButton) findViewById(R.id.ibt_reward);
        ibt_reward.setOnClickListener(this);
        ibt_attention = (ImageButton) findViewById(R.id.ibt_attention);
        ibt_attention.setOnClickListener(this);
        ibt_guard = (ImageButton) findViewById(R.id.ibt_guard);
        ibt_guard.setOnClickListener(this);


        famous_viewpager.setOnPageChangeListener(this);
        tv_plain.setOnClickListener(this);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        famous_viewpager.setAdapter(pagerAdapter);
        rad_homepage.setChecked(true);
        teacher_group.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_plain:
                intent.setClass(this, Teacherintro.class);
                startActivity(intent);
                break;

            case R.id.ibt_ask:
                intent.setClass(this, AskdQuestion.class);
                startActivity(intent);
                // Toast.makeText(FamousPlain.this, "提问", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibt_reward:
                Toast.makeText(FamousPlain.this, "打赏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibt_attention:
                Toast.makeText(FamousPlain.this, "关注", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibt_guard:
                intent.setClass(this, IntoGuard.class);
                startActivity(intent);
                // Toast.makeText(FamousPlain.this, "守护", Toast.LENGTH_SHORT).show();
                break;
            case R.id.teacher_back:
                this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rad_homepage:
                famous_viewpager.setCurrentItem(0, false);
                break;
            case R.id.rad_writing:
                famous_viewpager.setCurrentItem(1, false);
                break;
            case R.id.rad_video:
                famous_viewpager.setCurrentItem(2, false);
                break;
            case R.id.rad_viewpoint:
                famous_viewpager.setCurrentItem(3, false);
                break;
            case R.id.rad_answer:
                famous_viewpager.setCurrentItem(4, false);
                break;

            default:
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
                teacher_group.check(R.id.rad_homepage);
                break;
            case 1:
                teacher_group.check(R.id.rad_writing);
                break;
            case 2:
                teacher_group.check(R.id.rad_video);
                break;
            case 3:
                teacher_group.check(R.id.rad_viewpoint);
                break;
            case 4:
                teacher_group.check(R.id.rad_answer);
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

}
