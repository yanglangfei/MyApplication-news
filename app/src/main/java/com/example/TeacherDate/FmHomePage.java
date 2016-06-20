package com.example.TeacherDate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapter.IdeaAdapter;
import com.example.adapter.QuestionAdapter;
import com.example.androidnetwork.R;
import com.example.model.Interlocution;
import com.example.model.Opinion;
import com.example.view.TestListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/14.
 * 他的主页
 */
public class FmHomePage extends Fragment{
    private  View view;
    private IdeaAdapter ideaAdapter;
    private QuestionAdapter questionAdapter;
    private TestListView homelv_idea;
    private TestListView homelv_answer;
    private List<Opinion> opinionList=new ArrayList<>();
    private List<Interlocution> list=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.fr_homepage,container,false);

        init();

        return view;
    }

    private void init() {
        homelv_idea= (TestListView) view.findViewById(R.id.homelv_idea);
        homelv_answer= (TestListView) view.findViewById(R.id.homelv_answer);
        ideaAdapter=new IdeaAdapter(getActivity(),opinionList);
        questionAdapter=new QuestionAdapter(getActivity(),list);
        homelv_idea.setAdapter(ideaAdapter);
        homelv_answer.setAdapter(questionAdapter);
    }
}
