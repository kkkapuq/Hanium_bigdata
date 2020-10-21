package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
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
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;
import kotlin.jvm.internal.Intrinsics;

public class ResultActivity extends Activity {
    public void sexRateChart(){
        PieChart sexRateChart;
        sexRateChart = findViewById(R.id.sexRateChart);
        sexRateChart.setUsePercentValues(true);
        sexRateChart.getDescription().setEnabled(true);
        sexRateChart.setExtraOffsets(5f, 10f, 5f, 5f);
        sexRateChart.setDragDecelerationFrictionCoef(0.95f);
        sexRateChart.setDrawHoleEnabled(false);
        sexRateChart.setHoleColor(Color.BLACK);
        sexRateChart.setTransparentCircleRadius(61f);

        ArrayList sexValues = new ArrayList();
        sexValues.add(new PieEntry(63.0F, "남성"));
        sexValues.add(new PieEntry(37.0F, "여성"));

        sexRateChart.animateY(2000, Easing.EaseInOutCubic);

        PieDataSet sexDataSet = new PieDataSet((List)sexValues, "성별");
        sexDataSet.setSliceSpace(3.0F);
        sexDataSet.setSelectionShift(2.0F);

        PieData sexData = new PieData((IPieDataSet)sexDataSet);
        sexData.setValueTextSize(10.0F);
        sexData.setValueTextColor(Color.BLACK);

        sexRateChart.setData(sexData);
        sexRateChart.invalidate();
    }
    public void ageRateChart(){
        BarChart ageRateChart = findViewById(R.id.ageRateChart);
        ageRateChart.setExtraOffsets(5.0f, 10.0f, 5.0f, 5.0f);

        ArrayList ageValues = new ArrayList();
        ageValues.add(new BarEntry(0.0f, 10.0f, "10대"));
        ageValues.add(new BarEntry(1.0F, 30.0F, "20대"));
        ageValues.add(new BarEntry(2.0F, 50.0F, "30대"));
        ageValues.add(new BarEntry(3.0F, 30.0F, "40대"));
        ageValues.add(new BarEntry(4.0F, 40.0F, "50대"));
        ageValues.add(new BarEntry(5.0F, 5.0F, "60대 이상"));

//        ageValues.add("10대");
//        ageValues.add("20대");
//        ageValues.add("30대");
//        ageValues.add("40대");
//        ageValues.add("50대");
//        ageValues.add("60대 이상");
//        int startColor1 = ContextCompat.getColor((Context)this, 17170456);
//        int startColor2 = ContextCompat.getColor((Context)this, 17170450);
//        int startColor3 = ContextCompat.getColor((Context)this, 17170456);
//        int startColor4 = ContextCompat.getColor((Context)this, 17170452);
//        int startColor5 = ContextCompat.getColor((Context)this, 17170454);
//        int startColor6 = ContextCompat.getColor((Context)this, 17170432);

        XAxis xAxis = ageRateChart.getXAxis();
//        xAxis.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getAxisLabel(float value, AxisBase axis) {
//                return ageValues.get((int)value);
//            }
//        });

        ageRateChart.animateY(4000, Easing.EaseInOutCubic);
        BarDataSet ageDataSet = new BarDataSet(ageValues, " 연령대");
        BarData ageData = new BarData(ageDataSet);
        ageData.setBarWidth(1f);
        ageRateChart.setData(ageData);
        ageRateChart.setFitBars(true);
        ageRateChart.invalidate();

    }
    public void timeLineChart(){
        ArrayList timeValues = new ArrayList();
        Entry timeLine1 = new Entry(0.0F, 140.0F);
        timeValues.add(timeLine1);
        Entry timeLine2 = new Entry(1.0F, 2000.0F);
        timeValues.add(timeLine2);
        Entry timeLine3 = new Entry(2.0F, 800.0F);
        timeValues.add(timeLine3);
        Entry timeLine4 = new Entry(3.0F, 500.0F);
        timeValues.add(timeLine4);

        LineChart timeLineChart = findViewById(R.id.timeLineChart);

        LineDataSet setTime = new LineDataSet(timeValues, "시간대별 댓글");
        setTime.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList timeDataset = new ArrayList();
        timeDataset.add(setTime);

        final ArrayList<String> xLabel = new ArrayList<>();
        xLabel.add("0~6시");
        xLabel.add("6~12시");
        xLabel.add("12~18시");
        xLabel.add("18~0시");

        XAxis xAxis = timeLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value, AxisBase axis){
                return xLabel.get((int)value);
            }
        });
        xAxis.setGranularity(1f);

        LineData timeData = new LineData((List)timeDataset);
        timeLineChart.setData(timeData);
        timeLineChart.invalidate();
    }
    public void posKeywordChart(){
        PieChart posKeywordChart = findViewById(R.id.posKeywordChart);
        posKeywordChart.setUsePercentValues(true);
        posKeywordChart.getDescription().setEnabled(false);
        posKeywordChart.setExtraOffsets(5f, 10f, 5f, 5f);
        posKeywordChart.setDragDecelerationFrictionCoef(0.95f);
        posKeywordChart.setDrawHoleEnabled(false);
        posKeywordChart.setHoleColor(Color.BLACK);
        posKeywordChart.setTransparentCircleRadius(61f);

        ArrayList posValues = new ArrayList();
        posValues.add(new PieEntry(63.0F, "좋은"));
        posValues.add(new PieEntry(37.0F, "긍정적"));
        posValues.add(new PieEntry(37.0F, "용기있는"));
        posValues.add(new PieEntry(37.0F, "정의로운"));
        posValues.add(new PieEntry(37.0F, "최고"));

        posKeywordChart.animateY(2000, Easing.EaseInOutCubic);

        PieDataSet posDataSet = new PieDataSet((List)posValues, "긍정 키워드");
        posDataSet.setSliceSpace(3.0F);
        posDataSet.setSelectionShift(2.0F);
        PieData posData = new PieData((IPieDataSet)posDataSet);
        posData.setValueTextSize(10.0F);
        posData.setValueTextColor(Color.BLACK);
        posKeywordChart.setData(posData);
        posKeywordChart.invalidate();
    }
    public void negKeywordChart(){
        PieChart posKeywordChart = findViewById(R.id.posKeywordChart);
        posKeywordChart.setUsePercentValues(true);
        posKeywordChart.getDescription().setEnabled(false);
        posKeywordChart.setExtraOffsets(5f, 10f, 5f, 5f);
        posKeywordChart.setDragDecelerationFrictionCoef(0.95f);
        posKeywordChart.setDrawHoleEnabled(false);
        posKeywordChart.setHoleColor(Color.BLACK);
        posKeywordChart.setTransparentCircleRadius(61f);

        ArrayList negValues = new ArrayList();
        negValues.add(new PieEntry(63.0F, "좋은"));
        negValues.add(new PieEntry(37.0F, "긍정적"));
        negValues.add(new PieEntry(37.0F, "용기있는"));
        negValues.add(new PieEntry(37.0F, "정의로운"));
        negValues.add(new PieEntry(37.0F, "최고"));

        posKeywordChart.animateY(2000, Easing.EaseInOutCubic);

        PieDataSet negDataSet = new PieDataSet((List)negValues, "긍정 키워드");
        negDataSet.setSliceSpace(3.0F);
        negDataSet.setSelectionShift(2.0F);
        PieData negData = new PieData((IPieDataSet)negDataSet);
        negData.setValueTextSize(10.0F);
        negData.setValueTextColor(Color.BLACK);
        posKeywordChart.setData(negData);
        posKeywordChart.invalidate();
    }
    public void emotionChart(){
        PieChart emotionChart = findViewById(R.id.emotionChart);
        emotionChart.setUsePercentValues(true);
        emotionChart.getDescription().setEnabled(false);
        emotionChart.setExtraOffsets(5f, 10f, 5f, 5f);
        emotionChart.setDragDecelerationFrictionCoef(0.95f);
        emotionChart.setDrawHoleEnabled(false);
        emotionChart.setHoleColor(Color.BLACK);
        emotionChart.setTransparentCircleRadius(61f);

        ArrayList emotionValues = new ArrayList();
        emotionValues.add(new PieEntry(150.0F, "좋아요"));
        emotionValues.add(new PieEntry(90.0F, "최고예요"));
        emotionValues.add(new PieEntry(58.0F, "슬퍼요"));
        emotionValues.add(new PieEntry(15.0F, "화나요"));
        emotionValues.add(new PieEntry(30.0F, "후속기사 원해요"));

        emotionChart.animateY(2000, Easing.EaseInOutCubic);

        PieDataSet emotionDataset = new PieDataSet((List)emotionValues, "사람들의 반응");
        emotionDataset.setSliceSpace(3.0F);
        emotionDataset.setSelectionShift(2.0F);

        PieData emotionData = new PieData((IPieDataSet)emotionDataset);
        emotionData.setValueTextSize(10.0F);
        emotionData.setValueTextColor(Color.BLACK);

        emotionChart.setData(emotionData);
        emotionChart.invalidate();
    }

    public void commentSetter(){
        TextView bestCommId1 = findViewById(R.id.bestCommId1);
        TextView bestCommCon1 = findViewById(R.id.bestCommCon1);
        TextView bestCommLike1 = findViewById(R.id.bestCommLike1);
        TextView bestCommDisLike1 = findViewById(R.id.bestCommDisLike1);

        TextView bestCommId2 = findViewById(R.id.bestCommId2);
        TextView bestCommCon2 = findViewById(R.id.bestCommCon2);
        TextView bestCommLike2 = findViewById(R.id.bestCommLike2);
        TextView bestCommDisLike2 = findViewById(R.id.bestCommDisLike2);

        TextView bestCommId3= findViewById(R.id.bestCommId3);
        TextView bestCommCon3 = findViewById(R.id.bestCommCon3);
        TextView bestCommLike3 = findViewById(R.id.bestCommLike3);
        TextView bestCommDisLike3 = findViewById(R.id.bestCommDisLike3);

        TextView bestCommId4 = findViewById(R.id.bestCommId4);
        TextView bestCommCon4 = findViewById(R.id.bestCommCon4);
        TextView bestCommLike4 = findViewById(R.id.bestCommLike4);
        TextView bestCommDisLike4 = findViewById(R.id.bestCommDisLike4);

        TextView bestCommId5 = findViewById(R.id.bestCommId5);
        TextView bestCommCon5 = findViewById(R.id.bestCommCon5);
        TextView bestCommLike5 = findViewById(R.id.bestCommLike5);
        TextView bestCommDisLike5 = findViewById(R.id.bestCommDisLike5);

        bestCommId1.setText("예시 ID 1");
        bestCommCon1.setText("응애으애애애애애애");
        bestCommLike1.setText("21152");
        bestCommDisLike1.setText("212");

        bestCommId2.setText("예시 ID 2");
        bestCommCon2.setText("응애으애애애애애애");
        bestCommLike2.setText("21152");
        bestCommDisLike2.setText("212");

        bestCommId3.setText("예시 ID 3");
        bestCommCon3.setText("응애으애애애애애애");
        bestCommLike3.setText("21152");
        bestCommDisLike3.setText("212");

        bestCommId4.setText("예시 ID 4");
        bestCommCon4.setText("응애으애애애애애애");
        bestCommLike4.setText("21152");
        bestCommDisLike4.setText("212");

        bestCommId5.setText("예시 ID 5");
        bestCommCon5.setText("응애으애애애애애애");
        bestCommLike5.setText("21152");
        bestCommDisLike5.setText("212");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //탭호스트 생성
        TabHost tabHost = findViewById(R.id.TabHost);
        tabHost.setup();

        //종합 탭
        TabHost.TabSpec tabSpecTotal = tabHost.newTabSpec("Total").setIndicator((CharSequence)"종합");
        tabSpecTotal.setContent(R.id.total);
        sexRateChart();
        ageRateChart();
        timeLineChart();
        commentSetter();

        tabHost.addTab(tabSpecTotal);

        //키워드 분석
        TabHost.TabSpec tabSpecKeyword = tabHost.newTabSpec("Keyword").setIndicator((CharSequence)"키워드 분석");
        tabSpecKeyword.setContent(R.id.Keyword);
        posKeywordChart();
        negKeywordChart();

        tabHost.addTab(tabSpecKeyword);

        //감정 분석

        TabHost.TabSpec tabSpecEmotion = tabHost.newTabSpec("Emotion").setIndicator((CharSequence)"감정분석");
        tabSpecEmotion.setContent(R.id.Emotion);
        emotionChart();
        tabHost.addTab(tabSpecEmotion);

        //기타 정보
        TabHost.TabSpec tabSpecEtc = tabHost.newTabSpec("Etc").setIndicator((CharSequence)"기타 정보");
        tabSpecEtc.setContent(R.id.Etc);
        tabHost.addTab(tabSpecEtc);
        Intrinsics.checkNotNullExpressionValue(tabHost, "tabHost");
        tabHost.setCurrentTab(0);
    }
}
