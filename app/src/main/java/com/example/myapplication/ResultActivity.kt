package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.widget.TabHost

class ResultActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val tabHost = findViewById<TabHost>(R.id.TabHost)
        tabHost.setup()
        val tabSpecTotal = tabHost.newTabSpec("Total").setIndicator("종합")
        tabSpecTotal.setContent(R.id.Total)
        tabHost.addTab(tabSpecTotal)
        val tabSpecKeyword = tabHost.newTabSpec("Keyword").setIndicator("키워드 분석")
        tabSpecKeyword.setContent(R.id.Keyword)
        tabHost.addTab(tabSpecKeyword)
        val tabSpecEmotion = tabHost.newTabSpec("Emotion").setIndicator("감정분석")
        tabSpecEmotion.setContent(R.id.Emotion)
        tabHost.addTab(tabSpecEmotion)
        val tabSpecEtc = tabHost.newTabSpec("Etc").setIndicator("기타 정보")
        tabSpecEtc.setContent(R.id.Etc)
        tabHost.addTab(tabSpecEtc)
        tabHost.currentTab = 0
    }
}