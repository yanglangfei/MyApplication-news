package com.example.Indent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.adapter.ViewPagerAdapter;
import com.example.androidnetwork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 * <p/>
 * 我的订单
 */
public class MyOrder extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    private ImageButton order_back;
    private OrderAll orderAll;//全部订单
    private OrderFinish orderFinssh;//已完成订单
    private OrderUnfinish unfinish;//未完成订单
    private List<Fragment> list;
    private ViewPager order_pager;
    private ViewPagerAdapter adapter;
    private RadioGroup rp_order;
    private RadioButton order_all;
    private RadioButton order_finish;
    private RadioButton order_unfinished;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorder);

        initFragmnet();
        init();
    }

    private void initFragmnet() {
        list = new ArrayList<>();
        orderAll = new OrderAll();
        orderFinssh = new OrderFinish();
        unfinish = new OrderUnfinish();
        list.add(orderAll);
        list.add(orderFinssh);
        list.add(unfinish);
    }

    private void init() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        order_back = (ImageButton) findViewById(R.id.order_back);
        order_back.setOnClickListener(this);

        rp_order = (RadioGroup) findViewById(R.id.rp_order);
        rp_order.setOnCheckedChangeListener(this);

        order_all = (RadioButton) findViewById(R.id.order_all);
        order_finish = (RadioButton) findViewById(R.id.order_finish);
        order_unfinished = (RadioButton) findViewById(R.id.order_unfinished);

        order_pager = (ViewPager) findViewById(R.id.order_pager);
        order_pager.setOnPageChangeListener(this);
        order_pager.setAdapter(adapter);

        order_all.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_back:
                this.finish();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                order_all.setChecked(true);
                break;
            case 1:
                order_finish.setChecked(true);
                break;
            case 2:
                order_unfinished.setChecked(true);
                break;
            default:
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.order_all:
                order_pager.setCurrentItem(0);
                break;
            case R.id.order_finish:
                order_pager.setCurrentItem(1);
                break;
            case R.id.order_unfinished:
                order_pager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }
}
