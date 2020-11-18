package fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.ResultActivity;
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
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
//import com.example.myapplication.ResultActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class FragmentTotal extends Fragment {
    ViewPager viewPager;

    ArrayList<HashMap> tnewsArrayList;
    ArrayList<HashMap> timeAnalysisArrayList;
    ArrayList<HashMap> relevantArticleArrayList;

    public void Fragment_Total() {
    }

    public void sexRateChart(View view) {
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
        HashMap<String, String> map = tnewsArrayList.get(0);

        sexValues.add(new PieEntry(72.0f, "남성"));
        sexValues.add(new PieEntry(28.0f, "여성"));

        sexRateChart.animateY(5000, Easing.EaseInOutCubic);

        final int[] sexColors = {
                Color.parseColor("#2b88f2"),
                Color.parseColor("#e7b7cc")
        };
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : sexColors)
            colors.add(c);

        PieDataSet sexDataSet = new PieDataSet((List) sexValues, "성별");
        sexDataSet.setSliceSpace(3.0F);
        sexDataSet.setSelectionShift(2.0F);
        sexDataSet.setColors(colors);

        PieData sexData = new PieData((IPieDataSet) sexDataSet);
        sexData.setValueTextSize(10.0F);
        sexData.setValueTextColor(Color.BLACK);

        sexRateChart.setData(sexData);
        sexRateChart.invalidate();
    }

    public void ageRateChart(View view) {
        BarChart ageRateChart = view.findViewById(R.id.ageRateChart);
        ageRateChart.setExtraOffsets(5.0f, 10.0f, 5.0f, 5.0f);

        final ArrayList ageValues = new ArrayList();

        HashMap<String, String> map = tnewsArrayList.get(0);

        ageValues.add(new BarEntry(0.0f, 10.0f, "10대"));
        ageValues.add(new BarEntry(1.0F, 30.0f, "20대"));
        ageValues.add(new BarEntry(2.0F, 40.1f, "30대"));
        ageValues.add(new BarEntry(3.0F, 64.0f, "40대"));
        ageValues.add(new BarEntry(4.0F, 35.0f, "50대"));
        ageValues.add(new BarEntry(5.0F, 5.0f, "60대 이상"));


        final ArrayList<String> xLabel = new ArrayList<>();
        xLabel.add("10대");
        xLabel.add("20대");
        xLabel.add("30대");
        xLabel.add("40대");
        xLabel.add("50대");
        xLabel.add("60대 이상");

        XAxis xAxis = ageRateChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xLabel));
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);

        ageRateChart.animateY(5000, Easing.EaseInOutCubic);

        final int[] ageColors = {
                Color.parseColor("#d4d4d4"),
                Color.parseColor("#d4d4d4"),
                Color.parseColor("#d4d4d4"),
                Color.parseColor("#cf16f2"),
                Color.parseColor("#d4d4d4"),
                Color.parseColor("#d4d4d4"),
        };
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ageColors)
            colors.add(c);

        BarDataSet ageDataSet = new BarDataSet(ageValues, " 연령대");
        ageDataSet.setColors(colors);

        BarData ageData = new BarData(ageDataSet);
        ageData.setBarWidth(1f);
        ageRateChart.setData(ageData);
        ageRateChart.setFitBars(true);
        ageRateChart.invalidate();

    }

    public void timeLineChart(View view) {
        ArrayList timeValues = new ArrayList();

        HashMap<String, String> map1 = timeAnalysisArrayList.get(0);
        HashMap<String, String> map2 = timeAnalysisArrayList.get(1);
        HashMap<String, String> map3 = timeAnalysisArrayList.get(2);
        HashMap<String, String> map4 = timeAnalysisArrayList.get(3);

        Entry timeLine1 = new Entry(0.0F, Float.parseFloat(map1.get("count")));
        timeValues.add(timeLine1);
        Entry timeLine2 = new Entry(1.0F, Float.parseFloat(map2.get("count")));
        timeValues.add(timeLine2);
        Entry timeLine3 = new Entry(2.0F, Float.parseFloat(map3.get("count")));
        timeValues.add(timeLine3);
        Entry timeLine4 = new Entry(3.0F, Float.parseFloat(map4.get("count")));
        timeValues.add(timeLine4);

        LineChart timeLineChart = view.findViewById(R.id.timeLineChart);

        LineDataSet setTime = new LineDataSet(timeValues, "시간대별 댓글");
        setTime.setAxisDependency(YAxis.AxisDependency.LEFT);
        setTime.setColor(Color.parseColor("#c39797"));
        setTime.setLineWidth(6f);

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
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xLabel.get((int) value);
            }
        };
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        LineData timeData = new LineData((List) timeDataset);
        timeLineChart.setData(timeData);
        timeLineChart.invalidate();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_total, container, false);

        Bundle bundle = getArguments();

        tnewsArrayList = (ArrayList<HashMap>) bundle.getSerializable("tnewsArrayList");
        timeAnalysisArrayList = (ArrayList<HashMap>) bundle.getSerializable("timeAnalysisArrayList");

        sexRateChart(view);
        ageRateChart(view);
        timeLineChart(view);

        return view;
    }
}
