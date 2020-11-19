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
        posKeywordChart.setUsePercentValues(false);
        posKeywordChart.getDescription().setEnabled(false);
        posKeywordChart.setExtraOffsets(5f, 10f, 5f, 5f);
        posKeywordChart.setDragDecelerationFrictionCoef(0.95f);
        posKeywordChart.setDrawHoleEnabled(false);
        posKeywordChart.setHoleColor(Color.BLACK);
        posKeywordChart.setTransparentCircleRadius(61f);

        ArrayList posValues = new ArrayList();
        int cnt = 0;

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> map = new HashMap<String, String>();

        //긍정 키워드 탑 5 뽑기
        for(int i = 0; i < keywordRankArrayList.size(); i++){
            Object temp = keywordRankArrayList.get(i).get("emotionBool");
            if(String.valueOf(temp).equals("1")){
                if(cnt == 5)
                    break;
                Object count = keywordRankArrayList.get(i).get("count");
                Object keyword = keywordRankArrayList.get(i).get("keyword");
                posValues.add(new PieEntry(Float.parseFloat(count.toString()), keyword.toString()));
                cnt++;
            }
        }

        posKeywordChart.animateY(5000, Easing.EaseInOutCubic);

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
        PieChart negKeywordChart = view.findViewById(R.id.negKeywordChart);
        negKeywordChart.setUsePercentValues(false);
        negKeywordChart.getDescription().setEnabled(false);
        negKeywordChart.setExtraOffsets(5f, 10f, 5f, 5f);
        negKeywordChart.setDragDecelerationFrictionCoef(0.95f);
        negKeywordChart.setDrawHoleEnabled(false);
        negKeywordChart.setHoleColor(Color.BLACK);
        negKeywordChart.setTransparentCircleRadius(61f);

        ArrayList negValues = new ArrayList();
        int cnt = 0;

        //부정 키워드 탑5 뽑기
        for(int i = 0; i < keywordRankArrayList.size(); i++) {
            Object temp = keywordRankArrayList.get(i).get("emotionBool");
            if (String.valueOf(temp).equals("0")) {
                if (cnt == 5)
                    break;
                Object count = keywordRankArrayList.get(i).get("count");
                Object keyword = keywordRankArrayList.get(i).get("keyword");
                negValues.add(new PieEntry(Float.parseFloat(count.toString()), keyword.toString()));
                cnt++;
            }
        }

        negKeywordChart.animateY(2000, Easing.EaseInOutCubic);

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
        negKeywordChart.setData(negData);
        negKeywordChart.invalidate();
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
