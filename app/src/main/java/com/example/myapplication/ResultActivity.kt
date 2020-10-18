package com.example.myapplication

import android.app.Activity
import android.graphics.Color
import android.graphics.Color.red
import android.os.Bundle
import android.service.autofill.FillContext
import android.widget.TabHost
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : Activity() {

    /*
    fun sexRateChart(){
        //남녀 성비 그래프
        sexRateChart.setUsePercentValues(true)
        sexRateChart.description.setEnabled(false)
        sexRateChart.setExtraOffsets(5f, 10f, 5f, 5f)
        sexRateChart.dragDecelerationFrictionCoef = 0.95f
        sexRateChart.isDrawHoleEnabled = false
        sexRateChart.setHoleColor(Color.BLACK)
        sexRateChart.transparentCircleRadius = 61f

        //test입니다!

        val sexValues = ArrayList<PieEntry>() // 데이터 삽입
        sexValues.add(PieEntry(63f,"남성"))
        sexValues.add(PieEntry(37f,"여성"))

        sexRateChart.animateY(2000, Easing.EaseInOutCubic) //애니메이션 효과 설정

        val sexDataSet = PieDataSet(sexValues, "성별")
        sexDataSet.sliceSpace = 3f
        sexDataSet.selectionShift = 2f

        val sexData = PieData((sexDataSet))
        sexData.setValueTextSize(10f)
        sexData.setValueTextColor(Color.BLACK)
        sexRateChart.setData(sexData)
        sexRateChart.invalidate()
        //남녀 성비 끝
    }
    fun ageRateChart(){
        //연령대 막대그래프
        ageRateChart.setExtraOffsets(5f, 10f, 5f, 5f)
        val ageValues = ArrayList<BarEntry>()
        ageValues.add(BarEntry(0f, 10f, "10대"))
        ageValues.add(BarEntry(1f, 30f, "20대"))
        ageValues.add(BarEntry(2f, 50f, "30대"))
        ageValues.add(BarEntry(3f, 30f, "40대"))
        ageValues.add(BarEntry(4f, 40f, "50대"))
        ageValues.add(BarEntry(5f, 5f, "60대 이상"))

        val startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light)
        val startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light)
        val startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light)
        val startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light)
        val startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light)
        val startColor6 = ContextCompat.getColor(this, android.R.color.darker_gray)

//        val gradientFills: MutableList<Fill> = ArrayList()
//        with(gradientFills) {
//            add(Fill(startColor1))
//            add(Fill(startColor2))
//            add(Fill(startColor3))
//            add(Fill(startColor4))
//            add(Fill(startColor5))
//            add(Fill(startColor6))
//        }

        ageRateChart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
            granularity = 1f
            valueFormatter = object : ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    return ((value * 10 + 10).toInt()).toString() + "대"
                }
            }
        }
        ageRateChart.animateY(4000, Easing.EaseInOutCubic)
        val ageDataSet = BarDataSet(ageValues, "연령대")
//        ageDataSet.setColors(intArrayOf(R.color.red1, R.color.red2, R.color.red3, R.color.red4), Context)
        val ageData = BarData(ageDataSet)
        ageData.barWidth = 1f
        ageRateChart.data = ageData
        ageRateChart.setFitBars(true)
        ageRateChart.invalidate()
        //연령대 막대그래프 끝
    }
    fun timeLineChart(){
        val timeValues = ArrayList<Entry>()
        var timeLine1 = Entry(0f, 140f)
        timeValues.add(timeLine1)
        var timeLine2 = Entry(1f, 2000f)
        timeValues.add(timeLine2)
        var timeLine3 = Entry(2f, 800f)
        timeValues.add(timeLine3)
        var timeLine4 = Entry(3f, 500f)
        timeValues.add(timeLine4)

        timeLineChart.animateY(4000, Easing.EaseInOutCubic)

        val setTime = LineDataSet(timeValues, "시간대 별 댓글")
        setTime.axisDependency = YAxis.AxisDependency.LEFT

        val timeDataset = ArrayList<ILineDataSet>()
        timeDataset.add(setTime)

        val quarters = arrayOf<String>("0~6시", "6~12시", "12~18시", "18~0시")
        val formatter = object:ValueFormatter() {
            override fun getAxisLabel(value:Float, axis:AxisBase):String {
                return quarters[value.toInt()]
            }
        }

        val xAxis = timeLineChart.xAxis
        xAxis.setGranularity(1f) // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter)

        val timeData = LineData(timeDataset)
        timeLineChart.data = timeData
        timeLineChart.invalidate()
    }
    fun posKeywordChart(){
        //긍정 키워드
        posKeywordChart.setUsePercentValues(true)
        posKeywordChart.description.setEnabled(false)
        posKeywordChart.setExtraOffsets(5f, 10f, 5f, 5f)
        posKeywordChart.dragDecelerationFrictionCoef = 0.95f
        posKeywordChart.isDrawHoleEnabled = false
        posKeywordChart.setHoleColor(Color.BLACK)
        posKeywordChart.transparentCircleRadius = 61f

        val sexValues = ArrayList<PieEntry>() // 데이터 삽입
        sexValues.add(PieEntry(63f,"좋은"))
        sexValues.add(PieEntry(37f,"긍정적"))
        sexValues.add(PieEntry(37f,"용기있는"))
        sexValues.add(PieEntry(37f,"정의로운"))
        sexValues.add(PieEntry(37f,"최고"))

        posKeywordChart.animateY(2000, Easing.EaseInOutCubic) //애니메이션 효과 설정

        val sexDataSet = PieDataSet(sexValues, "긍정 키워드")
        sexDataSet.sliceSpace = 3f
        sexDataSet.selectionShift = 2f

        val sexData = PieData((sexDataSet))
        sexData.setValueTextSize(10f)
        sexData.setValueTextColor(Color.BLACK)
        posKeywordChart.setData(sexData)
        posKeywordChart.invalidate()
        //긍정 키워드 끝
    }
    fun negKeywordChart(){
        //부정 키워드
        negKeywordChart.setUsePercentValues(true)
        negKeywordChart.description.setEnabled(false)
        negKeywordChart.setExtraOffsets(5f, 10f, 5f, 5f)
        negKeywordChart.dragDecelerationFrictionCoef = 0.95f
        negKeywordChart.isDrawHoleEnabled = false
        negKeywordChart.setHoleColor(Color.BLACK)
        negKeywordChart.transparentCircleRadius = 61f

        val sexValues = ArrayList<PieEntry>() // 데이터 삽입
        sexValues.add(PieEntry(150f,"ㅅㅂ"))
        sexValues.add(PieEntry(90f,"거지같은"))
        sexValues.add(PieEntry(58f,"별로"))
        sexValues.add(PieEntry(15f,"쓰레기"))
        sexValues.add(PieEntry(49f,"최악"))


        negKeywordChart.animateY(2000, Easing.EaseInOutCubic) //애니메이션 효과 설정

        val sexDataSet = PieDataSet(sexValues, "부정 키워드")
        sexDataSet.sliceSpace = 3f
        sexDataSet.selectionShift = 2f

        val sexData = PieData((sexDataSet))
        sexData.setValueTextSize(10f)
        sexData.setValueTextColor(Color.BLACK)
        negKeywordChart.setData(sexData)
        negKeywordChart.invalidate()
        //부정 키워드 TOP 5
    }
    fun emotionChart(){
        //감정분석
        emotionChart.setUsePercentValues(true)
        emotionChart.description.setEnabled(false)
        emotionChart.setExtraOffsets(5f, 10f, 5f, 5f)
        emotionChart.dragDecelerationFrictionCoef = 0.95f
        emotionChart.isDrawHoleEnabled = false
        emotionChart.setHoleColor(Color.BLACK)
        emotionChart.transparentCircleRadius = 61f

        val sexValues = ArrayList<PieEntry>() // 데이터 삽입
        sexValues.add(PieEntry(150f,"좋아요"))
        sexValues.add(PieEntry(90f,"최고예요"))
        sexValues.add(PieEntry(58f,"슬퍼요"))
        sexValues.add(PieEntry(15f,"화나요"))
        sexValues.add(PieEntry(30f,"후속기사 원해요"))


        emotionChart.animateY(2000, Easing.EaseInOutCubic) //애니메이션 효과 설정

        val sexDataSet = PieDataSet(sexValues, "사람들의 반응")
        sexDataSet.sliceSpace = 3f
        sexDataSet.selectionShift = 2f

        val sexData = PieData((sexDataSet))
        sexData.setValueTextSize(10f)
        sexData.setValueTextColor(Color.BLACK)
        emotionChart.setData(sexData)
        emotionChart.invalidate()
        //부정 키워드 TOP 5
    }

     */

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        /*
        val tabHost = findViewById<TabHost>(R.id.TabHost)
        tabHost.setup()
        //종합 탭 소스
        val tabSpecTotal = tabHost.newTabSpec("Total").setIndicator("종합")
        tabSpecTotal.setContent(R.id.total)
        sexRateChart()
        ageRateChart()
        timeLineChart()

        //시간대 라인그래프

        timeLineChart.setExtraOffsets(5f, 10f, 5f, 5f)
        //시간대 라인그래프 끝
        bestCommId1.text = "ID1234"
        bestCommCon1.text = "ㅋㅋ 내가 이럴줄 알았다"
        bestCommLike1.text = "1200"
        bestCommDisLike1.text = "200"
        bestCommId2.text = "ID124"
        bestCommCon2.text = "그래도 이건 아닌거같아요. 힘내시길 바랍니다."
        bestCommLike2.text = "900"
        bestCommDisLike2.text = "123"
        bestCommId3.text = "ID14"
        bestCommCon3.text = "사람들이 그렇게 말했는데, 아직도 이해를 못하네 ㅋㅋ 수준"
        bestCommLike3.text = "791"
        bestCommDisLike3.text = "20"
        bestCommId4.text = "ID19934"
        bestCommCon4.text = "헬퍼 2 개망작됐음"
        bestCommLike4.text = "717"
        bestCommDisLike4.text = "9"
        bestCommId5.text = "ID1234"
        bestCommCon5.text = "오늘의 레시피 : 제육볶음"
        bestCommLike5.text = "711"
        bestCommDisLike5.text = "10"

        //BEST TOP 5 댓글

        tabHost.addTab(tabSpecTotal)
        //종합 탭 소스 끝

        //키워드 분석 소스
        val tabSpecKeyword = tabHost.newTabSpec("Keyword").setIndicator("키워드 분석")
        tabSpecKeyword.setContent(R.id.Keyword)
        posKeywordChart()
        negKeywordChart()

        tabHost.addTab(tabSpecKeyword)

        //키워드 분석 끝

        //감정분석
        val tabSpecEmotion = tabHost.newTabSpec("Emotion").setIndicator("감정분석")
        tabSpecEmotion.setContent(R.id.Emotion)
        emotionChart()

        tabHost.addTab(tabSpecEmotion)

        //감정분석 끝

        val tabSpecEtc = tabHost.newTabSpec("Etc").setIndicator("기타 정보")
        tabSpecEtc.setContent(R.id.Etc)
        tabHost.addTab(tabSpecEtc)
        tabHost.currentTab = 0

         */

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            tabLayout = findViewById<TabLayout>(R.id.main_tablayout)
            viewPager = findViewById<ViewPager>(R.id.main_viewPager)

            tabLayout!!.addTab(tabLayout!!.newTab().setText("Home"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Sport"))
            tabLayout!!.addTab(tabLayout!!.newTab().setText("Movie"))
            tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

            val adapter = PageAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
            viewPager!!.adapter = adapter

            viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

            tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager!!.currentItem = tab.position
                }
                override fun onTabUnselected(tab: TabLayout.Tab) {

                }
                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
        }
}