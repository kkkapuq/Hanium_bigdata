package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class FragmentKeyword extends Fragment {
    ViewPager viewPager;

    public void Fragment_Keyword(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keyword, container, false);
        ResultActivityy resultActivityy = new ResultActivityy();

        resultActivityy.posKeywordChart(view);
        resultActivityy.negKeywordChart(view);


        return view;
    }
}
