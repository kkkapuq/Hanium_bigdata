package fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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

    public void setWordcloud(View view){
        ImageView wordcloud = view.findViewById(R.id.wordcloud);
        HashMap<String, String> map = new HashMap<String, String>();
        String url = "";
        for(int i = 0; i < keywordRankArrayList.size(); i++){
            Object temp = keywordRankArrayList.get(i).get("wordcloud");
            if(temp == null)
                continue;
            else
                url = String.valueOf(temp);
        }

        if(url.equals(""))
            wordcloud.setImageResource(R.drawable.img_noimg);
        else
            Glide.with(this).load(url).into(wordcloud);
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
        int cnt = 0;

        HashMap<String, String> map1 = new HashMap<String, String>();
        HashMap<String, String> map2 = new HashMap<String, String>();
        HashMap<String, String> map3 = new HashMap<String, String>();
        HashMap<String, String> map4 = new HashMap<String, String>();
        HashMap<String, String> map5 = new HashMap<String, String>();

//        for(int i = 0; i < keywordRankArrayList.size(); i++){
//            Object temp = keywordRankArrayList.get(i).get("emotionBool");
//            if(String.valueOf(temp).equals("1")){
//                map
//            }
//            else
//                url = String.valueOf(temp);
//        }

        posValues.add(new PieEntry(Float.parseFloat(map1.get("count")), map1.get("keyword")));
        posValues.add(new PieEntry(Float.parseFloat(map2.get("count")), map2.get("keyword")));
        posValues.add(new PieEntry(Float.parseFloat(map3.get("count")), map3.get("keyword")));
        posValues.add(new PieEntry(Float.parseFloat(map4.get("count")), map4.get("keyword")));
        posValues.add(new PieEntry(Float.parseFloat(map5.get("count")), map5.get("keyword")));

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

        HashMap<String, String> map1 = keywordRankArrayList.get(25);
        HashMap<String, String> map2 = keywordRankArrayList.get(26);
        HashMap<String, String> map3 = keywordRankArrayList.get(27);
        HashMap<String, String> map4 = keywordRankArrayList.get(28);
        HashMap<String, String> map5 = keywordRankArrayList.get(29);

        negValues.add(new PieEntry(Float.parseFloat(map1.get("count")), map1.get("keyword")));
        negValues.add(new PieEntry(Float.parseFloat(map2.get("count")), map2.get("keyword")));
        negValues.add(new PieEntry(Float.parseFloat(map3.get("count")), map3.get("keyword")));
        negValues.add(new PieEntry(Float.parseFloat(map4.get("count")), map4.get("keyword")));
        negValues.add(new PieEntry(Float.parseFloat(map5.get("count")), map5.get("keyword")));

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

        setWordcloud(view);
        posKeywordChart(view);
        negKeywordChart(view);

        return view;
    }
}
