package com.example.MyRecord;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.adapter.RebateAdapter;
import com.example.androidnetwork.R;

/**
 * Created by Administrator on 2016/6/17.
 * <p/>
 * 我的返利
 */
public class MyRebate extends Activity implements View.OnClickListener {
    private ListView lv_rebate;
    private ImageButton ibt_rebate;
    private RebateAdapter adapter;
    private Context context=MyRebate.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rebate);

        init();
    }

    private void init() {
        ibt_rebate = (ImageButton) findViewById(R.id.ibt_rebate);
        ibt_rebate.setOnClickListener(this);
        lv_rebate= (ListView) findViewById(R.id.lv_rebate);
        adapter=new RebateAdapter(context);
        lv_rebate.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibt_rebate:
                this.finish();
                break;
            default:
                break;
        }
    }
}
