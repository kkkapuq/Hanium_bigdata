
package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import fragment.FragmentEmotion;
import fragment.FragmentEtc;
import fragment.FragmentKeyword;
import fragment.FragmentTotal;

public class ResultActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

    ArrayList<HashMap<String, String>> tnewsArrayList;
    ArrayList<HashMap<String, String>> timeAnalysisArrayList;
    ArrayList<HashMap<String, String>> relevantArticleArrayList;
    ArrayList<HashMap<String, String>> keywordRankArrayList;
    ArrayList<HashMap<String, String>> emotionAnalysisArrayList;
    ArrayList<HashMap<String, String>> emotionCommentsArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        HashMap<String,String> hashMap = new HashMap<>();

        Intent intent = getIntent();
        tnewsArrayList = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("tnews");
        timeAnalysisArrayList = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("timeAnalysis");
        relevantArticleArrayList = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("relevantArticle");
        keywordRankArrayList = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("keywordRank");
        emotionAnalysisArrayList = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("emotionAnalysis");
        emotionCommentsArrayList = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("emotionComments");

        TextView newsTitle = findViewById(R.id.articleTitle);
//        newsTitle = tnewsArrayList.get()

        //워드클라우드 이미지 따로 받아오기
        String wordcloud = getIntent().getStringExtra("wordcloud");
        hashMap.put("wordcloud", wordcloud);
        keywordRankArrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("flag","true");
        String simArticleImg1 = getIntent().getStringExtra("simArticleImg1");
        hashMap.put("simArticleImg1", simArticleImg1);
        String simArticleImg2 = getIntent().getStringExtra("simArticleImg2");
        hashMap.put("simArticleImg2", simArticleImg2);
        String simArticleImg3 = getIntent().getStringExtra("simArticleImg3");
        hashMap.put("simArticleImg3", simArticleImg3);

        relevantArticleArrayList.add(hashMap);

        mViewPager = findViewById(R.id.layout_viewPager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.layout_tab);
        tabLayout.setupWithViewPager(mViewPager);

    }

    public void setupViewPager(ViewPager viewPager) {

        Fragment fragmentTotal = new FragmentTotal();
        Fragment fragmentKeyword = new FragmentKeyword();
        Fragment fragmentEmotion = new FragmentEmotion();
        Fragment fragmentEtc = new FragmentEtc();

        //프래그먼트로 넘겨줄 arraylist친구들
        Bundle bundle = new Bundle();
        bundle.putSerializable("tnewsArrayList", tnewsArrayList);
        bundle.putSerializable("timeAnalysisArrayList", timeAnalysisArrayList);
        bundle.putSerializable("relevantArticleArrayList", relevantArticleArrayList);
        bundle.putSerializable("keywordRankArrayList", keywordRankArrayList);
        bundle.putSerializable("emotionAnalysisArrayList", emotionAnalysisArrayList);
        bundle.putSerializable("emotionCommentsArrayList", emotionCommentsArrayList);

        fragmentTotal.setArguments(bundle);
        fragmentKeyword.setArguments(bundle);
        fragmentEmotion.setArguments(bundle);
        fragmentEtc.setArguments(bundle);

        adapter.addFragment(fragmentTotal, "종합");
        adapter.addFragment(fragmentKeyword, "키워드 분석");
        adapter.addFragment(fragmentEmotion, "감정 분석");
        adapter.addFragment(fragmentEtc, "기타 정보");

        viewPager.setAdapter(adapter);
    }
}