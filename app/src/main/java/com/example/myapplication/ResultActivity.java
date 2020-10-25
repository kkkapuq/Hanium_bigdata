package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
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
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import fragment.FragmentEmotion;
import fragment.FragmentEtc;
import fragment.FragmentKeyword;
import fragment.FragmentTotal;

public class ResultActivity extends AppCompatActivity {

    public void sexRateChart(View view){
        PieChart sexRateChart;
        sexRateChart = view.findViewById(R.id.sexRateChart);
        sexRateChart.setUsePercentValues(true);
        sexRateChart.getDescription().setEnabled(true);
        sexRateChart.setExtraOffsets(5f, 10f, 5f, 5f);
        sexRateChart.setDragDecelerationFrictionCoef(0.95f);
        sexRateChart.setDrawHoleEnabled(false);
        sexRateChart.setHoleColor(Color.BLACK);
        sexRateChart.setTransparentCircleRadius(61f);

        ArrayList sexValues = new ArrayList();
        sexValues.add(new PieEntry(71.0F, "남성"));
        sexValues.add(new PieEntry(29.0F, "여성"));

        sexRateChart.animateY(2000, Easing.EaseInOutCubic);

        final int[] sexColors = {
                Color.parseColor("#2b88f2"),
                Color.parseColor("#e7b7cc")
        };
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int c : sexColors)
            colors.add(c);

        PieDataSet sexDataSet = new PieDataSet((List)sexValues, "성별");
        sexDataSet.setSliceSpace(3.0F);
        sexDataSet.setSelectionShift(2.0F);
        sexDataSet.setColors(colors);

        PieData sexData = new PieData((IPieDataSet)sexDataSet);
        sexData.setValueTextSize(10.0F);
        sexData.setValueTextColor(Color.BLACK);

        sexRateChart.setData(sexData);
        sexRateChart.invalidate();
    }
    public void ageRateChart(View view){
        BarChart ageRateChart = view.findViewById(R.id.ageRateChart);
        ageRateChart.setExtraOffsets(5.0f, 10.0f, 5.0f, 5.0f);

        ArrayList ageValues = new ArrayList();
        ageValues.add(new BarEntry(0.0f, 0.0f, "10대"));
        ageValues.add(new BarEntry(1.0F, 4.0F, "20대"));
        ageValues.add(new BarEntry(2.0F, 13.0F, "30대"));
        ageValues.add(new BarEntry(3.0F, 38.0F, "40대"));
        ageValues.add(new BarEntry(4.0F, 33.0F, "50대"));
        ageValues.add(new BarEntry(5.0F, 12.0F, "60대 이상"));


        XAxis xAxis = ageRateChart.getXAxis();
//        xAxis.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getAxisLabel(float value, AxisBase axis) {
//                return ageValues.get((int)value);
//            }
//        });

        ageRateChart.animateY(4000, Easing.EaseInOutCubic);

        final int[] ageColors = {
                Color.parseColor("#d4d4d4"),
                Color.parseColor("#d4d4d4"),
                Color.parseColor("#d4d4d4"),
                Color.parseColor("#cf16f2"),
                Color.parseColor("#d4d4d4"),
                Color.parseColor("#d4d4d4"),
        };
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int c : ageColors)
            colors.add(c);

        BarDataSet ageDataSet = new BarDataSet(ageValues, " 연령대");
        ageDataSet.setColors(colors);

        BarData ageData = new BarData(ageDataSet);
        ageData.setBarWidth(1f);
        ageRateChart.setData(ageData);
        ageRateChart.setFitBars(true);
        ageRateChart.invalidate();

    }
    public void timeLineChart(View view){
        ArrayList timeValues = new ArrayList();
        Entry timeLine1 = new Entry(0.0F, 140.0F);
        timeValues.add(timeLine1);
        Entry timeLine2 = new Entry(1.0F, 2000.0F);
        timeValues.add(timeLine2);
        Entry timeLine3 = new Entry(2.0F, 800.0F);
        timeValues.add(timeLine3);
        Entry timeLine4 = new Entry(3.0F, 500.0F);
        timeValues.add(timeLine4);

        LineChart timeLineChart = view.findViewById(R.id.timeLineChart);

        LineDataSet setTime = new LineDataSet(timeValues, "시간대별 댓글");
        setTime.setAxisDependency(YAxis.AxisDependency.LEFT);
        setTime.setColor(Color.parseColor("#c39797"));

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
    public void posKeywordChart(View view){
        PieChart posKeywordChart = view.findViewById(R.id.posKeywordChart);
        posKeywordChart.setUsePercentValues(true);
        posKeywordChart.getDescription().setEnabled(false);
        posKeywordChart.setExtraOffsets(5f, 10f, 5f, 5f);
        posKeywordChart.setDragDecelerationFrictionCoef(0.95f);
        posKeywordChart.setDrawHoleEnabled(false);
        posKeywordChart.setHoleColor(Color.BLACK);
        posKeywordChart.setTransparentCircleRadius(61f);

        ArrayList posValues = new ArrayList();
        posValues.add(new PieEntry(63.0F, "좋은"));
        posValues.add(new PieEntry(54.0F, "긍정적"));
        posValues.add(new PieEntry(30.0F, "용기있는"));
        posValues.add(new PieEntry(28.0F, "정의로운"));
        posValues.add(new PieEntry(19.0F, "최고"));

        posKeywordChart.animateY(2000, Easing.EaseInOutCubic);

        final int[] posColors = {
                Color.parseColor("#0004ff"),
                Color.parseColor("#3639f9"),
                Color.parseColor("#7073fd"),
                Color.parseColor("#abacf9"),
                Color.parseColor("#cbccff"),
        };
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int c : posColors)
            colors.add(c);

        PieDataSet posDataSet = new PieDataSet((List)posValues, "긍정 키워드");
        posDataSet.setSliceSpace(3.0F);
        posDataSet.setSelectionShift(2.0F);
        posDataSet.setColors(colors);

        PieData posData = new PieData((IPieDataSet)posDataSet);
        posData.setValueTextSize(10.0F);
        posData.setValueTextColor(Color.BLACK);
        posKeywordChart.setData(posData);
        posKeywordChart.invalidate();
    }
    public void negKeywordChart(View view){
        PieChart posKeywordChart = view.findViewById(R.id.negKeywordChart);
        posKeywordChart.setUsePercentValues(true);
        posKeywordChart.getDescription().setEnabled(false);
        posKeywordChart.setExtraOffsets(5f, 10f, 5f, 5f);
        posKeywordChart.setDragDecelerationFrictionCoef(0.95f);
        posKeywordChart.setDrawHoleEnabled(false);
        posKeywordChart.setHoleColor(Color.BLACK);
        posKeywordChart.setTransparentCircleRadius(61f);

        ArrayList negValues = new ArrayList();
        negValues.add(new PieEntry(63.0F, "나쁜"));
        negValues.add(new PieEntry(37.0F, "거지같은"));
        negValues.add(new PieEntry(31.0F, "이상한"));
        negValues.add(new PieEntry(18.0F, "쓰레기같은"));
        negValues.add(new PieEntry(3.0F, "최악의"));

        posKeywordChart.animateY(2000, Easing.EaseInOutCubic);

        final int[] posColors = {
                Color.parseColor("#ff2626"),
                Color.parseColor("#ff5a5a"),
                Color.parseColor("#ff8989"),
                Color.parseColor("#f6baba"),
                Color.parseColor("#e7c8c8"),
        };
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int c : posColors)
            colors.add(c);

        PieDataSet negDataSet = new PieDataSet((List)negValues, "부정 키워드");
        negDataSet.setSliceSpace(3.0F);
        negDataSet.setSelectionShift(2.0F);
        negDataSet.setColors(colors);

        PieData negData = new PieData((IPieDataSet)negDataSet);
        negData.setValueTextSize(10.0F);
        negData.setValueTextColor(Color.BLACK);
        posKeywordChart.setData(negData);
        posKeywordChart.invalidate();
    }
    public void emotionChart(View view){
        PieChart emotionChart = view.findViewById(R.id.emotionChart);
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

        final int[] ageColors = {
                Color.parseColor("#08f453"),
                Color.parseColor("#08b3f4"),
                Color.parseColor("#c64cfd"),
                Color.parseColor("#420420"),
                Color.parseColor("#ff4040"),
                Color.parseColor("#f4ff40"),
        };
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int c : ageColors)
            colors.add(c);

        PieDataSet emotionDataset = new PieDataSet((List)emotionValues, "사람들의 반응");
        emotionDataset.setSliceSpace(3.0F);
        emotionDataset.setSelectionShift(2.0F);
        emotionDataset.setColors(colors);

        PieData emotionData = new PieData((IPieDataSet)emotionDataset);
        emotionData.setValueTextSize(10.0F);
        emotionData.setValueTextColor(Color.BLACK);

        emotionChart.setData(emotionData);
        emotionChart.invalidate();
    }
    public void posBestCommentSetter(View view){
        TextView posBestCommId1 = view.findViewById(R.id.posBestCommId1);
        TextView posBestCommCon1 = view.findViewById(R.id.posBestCommCon1);
        TextView posBestCommLike1 = view.findViewById(R.id.posBestCommLike1);
        TextView posBestCommDisLike1 = view.findViewById(R.id.posBestCommDisLike1);

        TextView posBestCommId2 = view.findViewById(R.id.posBestCommId2);
        TextView posBestCommCon2 = view.findViewById(R.id.posBestCommCon2);
        TextView posBestCommLike2 = view.findViewById(R.id.posBestCommLike2);
        TextView posBestCommDisLike2 = view.findViewById(R.id.posBestCommDisLike2);

        TextView posBestCommId3= view.findViewById(R.id.posBestCommId3);
        TextView posBestCommCon3 = view.findViewById(R.id.posBestCommCon3);
        TextView posBestCommLike3 = view.findViewById(R.id.posBestCommLike3);
        TextView posBestCommDisLike3 = view.findViewById(R.id.posBestCommDisLike3);

        TextView posBestCommId4 = view.findViewById(R.id.posBestCommId4);
        TextView posBestCommCon4 = view.findViewById(R.id.posBestCommCon4);
        TextView posBestCommLike4 = view.findViewById(R.id.posBestCommLike4);
        TextView posBestCommDisLike4 = view.findViewById(R.id.posBestCommDisLike4);

        TextView posBestCommId5 = view.findViewById(R.id.posBestCommId5);
        TextView posBestCommCon5 = view.findViewById(R.id.posBestCommCon5);
        TextView posBestCommLike5 = view.findViewById(R.id.posBestCommLike5);
        TextView posBestCommDisLike5 = view.findViewById(R.id.posBestCommDisLike5);

        posBestCommId1.setText("hwak****");
        posBestCommCon1.setText("국민이 퇴출운동 해야됩니다. 민주당이 안한다고. 하니 우리국민이 합시다 국회의원들 특 권도 없애야합니다 이런걸. 바로세웁시다. 찬성");
        posBestCommLike1.setText("2036");
        posBestCommDisLike1.setText("50");

        posBestCommId2.setText("dudd****");
        posBestCommCon2.setText("손바닥으로 하늘을가리려 하네요 국민퇴출운동에 동참 합시다국민이 그리우습게 보이나봅니다 ~~");
        posBestCommLike2.setText("151");
        posBestCommDisLike2.setText("2");

        posBestCommId3.setText("lhn0****");
        posBestCommCon3.setText("국민이 퇴출운동 해야 한다에 한표!!!!.");
        posBestCommLike3.setText("74");
        posBestCommDisLike3.setText("2");

        posBestCommId4.setText("yo53****");
        posBestCommCon4.setText("ㅇ이년은도둑년입니다.국민모두퇴출에동참합시다");
        posBestCommLike4.setText("27");
        posBestCommDisLike4.setText("0");

        posBestCommId5.setText("simi****");
        posBestCommCon5.setText("민주당을 좋아하지만 이번엔 처신을 못하는듯통합당이 주도해서 국민들이 일어설때가 돈듯하다");
        posBestCommLike5.setText("20");
        posBestCommDisLike5.setText("1");
    }
    public void negBestCommentSetter(View view){
        TextView negBestCommId1 = view.findViewById(R.id.negBestCommId1);
        TextView negBestCommCon1 = view.findViewById(R.id.negBestCommCon1);
        TextView negBestCommLike1 = view.findViewById(R.id.negBestCommLike1);
        TextView negBestCommDisLike1 = view.findViewById(R.id.negBestCommDisLike1);

        TextView negBestCommId2 = view.findViewById(R.id.negBestCommId2);
        TextView negBestCommCon2 = view.findViewById(R.id.negBestCommCon2);
        TextView negBestCommLike2 = view.findViewById(R.id.negBestCommLike2);
        TextView negBestCommDisLike2 = view.findViewById(R.id.negBestCommDisLike2);

        TextView negBestCommId3= view.findViewById(R.id.negBestCommId3);
        TextView negBestCommCon3 = view.findViewById(R.id.negBestCommCon3);
        TextView negBestCommLike3 = view.findViewById(R.id.negBestCommLike3);
        TextView negBestCommDisLike3 = view.findViewById(R.id.negBestCommDisLike3);

        TextView negBestCommId4 = view.findViewById(R.id.negBestCommId4);
        TextView negBestCommCon4 = view.findViewById(R.id.negBestCommCon4);
        TextView negBestCommLike4 = view.findViewById(R.id.negBestCommLike4);
        TextView negBestCommDisLike4 = view.findViewById(R.id.negBestCommDisLike4);

        TextView negBestCommId5 = view.findViewById(R.id.negBestCommId5);
        TextView negBestCommCon5 = view.findViewById(R.id.negBestCommCon5);
        TextView negBestCommLike5 = view.findViewById(R.id.negBestCommLike5);
        TextView negBestCommDisLike5 = view.findViewById(R.id.negBestCommDisLike5);

        negBestCommId1.setText("lgh6****");
        negBestCommCon1.setText("전원주택이 9억이란말에 주택건설에 종사해본 1000만의 노동자 관계자는 알거다....말도안된다는걸.....그래서 저년이 거짓말을 한다는걸...도저히 말도안돼는 가격이지...");
        negBestCommLike1.setText("709");
        negBestCommDisLike1.setText("13");

        negBestCommId2.setText("ssel****");
        negBestCommCon2.setText("문재인이 한것과 똑같이 입에 재갈을 물리고 마스크를 덧 씌워 북조선 정신병원으로 보내야 한다. 국민이 직접 해야합니다. 지금 문정부는 대한민국 정부가 아니라 북조선 지방 자치 단체에 불과하다. 이제는 더이상 두고 볼 수 없습니다. 이들은 토착 빨갱이 김일성이 졸개들입니다. 김정은 눈치만 보고 있고... 시진핑이 눈치만 보고... 해마다 북한과 중국에 들어가는 돈이 수십조 입니다. 이런 미친 짓을 하는데 어떻게 우리나라 경제가 제대로 돌아가겠습니까? 기업들은 문을 닫아야 할 지경입니다. 공산당은 인간들이 아닙니다.");
        negBestCommLike2.setText("526");
        negBestCommDisLike2.setText("40");

        negBestCommId3.setText("syti****");
        negBestCommCon3.setText("6,70년대의 양아치라는 용어가 요즘 시대에 다시 부활했습니다. 민주당 개팔육 애들이 권력화하면서 자연스럽게 매스컴에서 다시 부활했습니다. 요즘 보면 그냥 양아치가 아니라 \"쌩 양아치\"들이 늘어나고있습니다. 그 집단의 좌장은 밀려서는 안된다 그래서 버티자인데, 사실 그 애들의 집단행동은 진보를 내세운 수구꼴통 무늬만 좌파인 야바위 정치 세력일 뿐입니다. 대께문 여러분 무지는 죄가 아니지만 상대에게는 큰 죄를 짖는 것입니다. 빨리 벋어나세요.");
        negBestCommLike3.setText("290");
        negBestCommDisLike3.setText("5");

        negBestCommId4.setText("mona****");
        negBestCommCon4.setText("야당에서 그랬으면 촛불들고 지랄했을텐데 우덜편이라 지켜보자네");
        negBestCommLike4.setText("94");
        negBestCommDisLike4.setText("1");

        negBestCommId5.setText("syti****");
        negBestCommCon5.setText("사기쳐서 먹고 사는 게 익숙해지면 자신이 사기 친다는 도덕관념이 무너지게 됩니다, 결국엔 상대가 사기에 넘어가지 않으면 상대가 나쁜 놈으로 보이기 시작합니다. 20여년전 자신이 투사라는 정신적 자위가 종국엔 이런 현상을 만들게 됩니다. 도덕적 해이가 이런 것이지요, 그게 집단화된것이 현재의 민주당입니다. 민주당의 가장 나쁜점은 지적능력이 없는 젊은 청년들을 집단화시킵니다. 그게 대께문이라는 기형적 지지집단을 만들게됩니다. 민주당은 시대의 기형화 정권이며, 빨리 소각해야 할 정권입니다.");
        negBestCommLike5.setText("84");
        negBestCommDisLike5.setText("3");
    }
    public void simArticleSetter(View view){
        ImageView simArticleImg1 = view.findViewById(R.id.simArticleImg1);
        TextView simArticleTitle1 = view.findViewById(R.id.simArticleTitle1);
        TextView simArticleCon1 = view.findViewById(R.id.simArticleCon1);

        ImageView simArticleImg2 = view.findViewById(R.id.simArticleImg2);
        TextView simArticleTitle2 = view.findViewById(R.id.simArticleTitle2);
        TextView simArticleCon2 = view.findViewById(R.id.simArticleCon2);

        ImageView simArticleImg3 = view.findViewById(R.id.simArticleImg3);
        TextView simArticleTitle3 = view.findViewById(R.id.simArticleTitle3);
        TextView simArticleCon3 = view.findViewById(R.id.simArticleCon3);

        simArticleImg1.setImageResource(R.drawable.simarticleimg1);
        simArticleTitle1.setText("자료받는 윤미향 의원");
        simArticleCon1.setText("더불어민주당 소속 윤미향 의원이 23일 오전 세종시 정부세종청사에서 열린 국회 환경노동위원회의 환경부, 기상청에 대한 종합감사에 참석, 보좌진에게 자료를 받고 있다.");

        simArticleImg2.setImageResource(R.drawable.simarticleimg2);
        simArticleTitle2.setText("지리산 반달곰 관련 질의하는 윤미향 위원");
        simArticleCon2.setText("더불어민주당 소속 윤미향 위원이 23일 오전 세종시 정부세종청사에서 열린 국회 환경노동위원회의 환경부, 기상청에 대한 국정감사에서 지리산 반달곰과 관련해 질의하며 곰 인형을 들어보이고 있다. ");

        simArticleImg3.setImageResource(R.drawable.simarticleimg3);
        simArticleTitle3.setText("자료받는 윤미향 의원");
        simArticleCon3.setText("조명래 환경부 장관이 23일 정부세종청사에서 열린 국회 환경노동위원회의 환경부, 기상청에 대한 국정감사에서 윤미향 더불어민주당 의원과 인사를 나누고 있다.");

    }


    private ViewPager mViewPager;
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mViewPager = findViewById(R.id.layout_viewPager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.layout_tab);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new FragmentTotal(), "종합");
        adapter.addFragment(new FragmentKeyword(), "키워드 분석");
        adapter.addFragment(new FragmentEmotion(), "감정 분석");
        adapter.addFragment(new FragmentEtc(), "기타 정보");

        viewPager.setAdapter(adapter);
    }
}
