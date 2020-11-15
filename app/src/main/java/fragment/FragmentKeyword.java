package fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.ResultActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
//import com.example.myapplication.ResultActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class FragmentKeyword extends Fragment {
    ViewPager viewPager;

    ArrayList<HashMap> keywordRankArrayList;

    public void Fragment_Keyword(){

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keyword, container, false);

        Bundle bundle = getArguments();

        keywordRankArrayList = (ArrayList<HashMap>) bundle.getSerializable("keywordRankArrayList");

        posKeywordChart(view);
        negKeywordChart(view);

        return view;
    }
}