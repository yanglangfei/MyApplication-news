package com.example.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.adapter.DetailsAdapter;
import com.example.androidnetwork.R;

/**
 * Created by Administrator on 2016/5/31.
 *
 * 问答详情
 */
public class QuestionAnswer extends Activity implements View.OnClickListener{
    private ListView answer_lv;
    private DetailsAdapter adapter;
    private Context context=this;
    private ImageButton ibt_back;
    private EditText answer_edt;
    private ImageButton answer_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionanswer);

        init();

    }

    private void init() {
        answer_lv= (ListView) findViewById(R.id.answer_lv);
        ibt_back= (ImageButton) findViewById(R.id.ibt_back);
        answer_edt= (EditText) findViewById(R.id.answer_edt);
        answer_send= (ImageButton) findViewById(R.id.answer_send);


        ibt_back.setOnClickListener(this);
        adapter=new DetailsAdapter(context);
        answer_lv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibt_back:
                this.finish();
                break;
        }
    }
}
