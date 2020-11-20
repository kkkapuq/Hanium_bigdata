package fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class FragmentEmotion extends Fragment {
    ViewPager viewPager;

    ArrayList<HashMap> tnewsArrayList;
    ArrayList<HashMap> emotionAnalysisArrayList;
    ArrayList<HashMap> emotionCommentsArrayList;

    public void Fragment_Emotion(){

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

        HashMap<String, String> map = tnewsArrayList.get(0);

        emotionValues.add(new PieEntry(10.0f, "좋아요"));
        emotionValues.add(new PieEntry(2.5f, "최고예요"));
        emotionValues.add(new PieEntry(6.1f, "슬퍼요"));
        emotionValues.add(new PieEntry(46.1f, "화나요"));
        emotionValues.add(new PieEntry(29.6f, "후속기사 원해요"));

        emotionChart.animateY(2000, Easing.EaseInOutCubic);

        final int[] ageColors = {
                Color.parseColor("#08f453"),
                Color.parseColor("#08b3f4"),
                Color.parseColor("#c64cfd"),
                Color.parseColor("#ff4040"),
                Color.parseColor("#420420")
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

    public void emotionSubChart(View view){
        PieChart emotionSubChart = view.findViewById(R.id.emotionSubChart);
        emotionSubChart.setUsePercentValues(false);
        emotionSubChart.getDescription().setEnabled(false);
        emotionSubChart.setExtraOffsets(5f, 10f, 5f, 5f);
        emotionSubChart.setDragDecelerationFrictionCoef(0.95f);
        emotionSubChart.setDrawHoleEnabled(false);
        emotionSubChart.setHoleColor(Color.BLACK);
        emotionSubChart.setTransparentCircleRadius(61f);

        ArrayList emotionValues = new ArrayList();

        HashMap<String, String> map = emotionAnalysisArrayList.get(0);

        emotionValues.add(new PieEntry(Float.parseFloat(map.get("positive")), "긍정"));
        emotionValues.add(new PieEntry(Float.parseFloat(map.get("negative")), "부정"));
        emotionValues.add(new PieEntry(Float.parseFloat(map.get("neutral")), "중립"));

        emotionSubChart.animateY(2000, Easing.EaseInOutCubic);

        final int[] ageColors = {
                Color.parseColor("#a5adf3"),
                Color.parseColor("#c94747"),
                Color.parseColor("#9ca796")
        };
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for(int c : ageColors)
            colors.add(c);

        PieDataSet emotionDataset = new PieDataSet((List)emotionValues, "긍/부정 분포도");
        emotionDataset.setSliceSpace(3.0F);
        emotionDataset.setSelectionShift(2.0F);
        emotionDataset.setColors(colors);

        PieData emotionData = new PieData((IPieDataSet)emotionDataset);
        emotionData.setValueTextSize(10.0F);
        emotionData.setValueTextColor(Color.BLACK);

        emotionSubChart.setData(emotionData);
        emotionSubChart.invalidate();
    }

    public void posBestCommentSetter(View view){
//        TextView posBestCommId1 = view.findViewById(R.id.posBestCommId1);
//        TextView posBestCommCon1 = view.findViewById(R.id.posBestCommCon1);
//        ImageView posBestCommLikeImg1 = view.findViewById(R.id.posBestCommLikeImg1);
//        ImageView posBestCommDisLikeImg1 = view.findViewById(R.id.posBestCommDisLikeImg1);
//        TextView posBestCommLike1 = view.findViewById(R.id.posBestCommLike1);
//        TextView posBestCommDisLike1 = view.findViewById(R.id.posBestCommDisLike1);
//
//        TextView posBestCommId2 = view.findViewById(R.id.posBestCommId2);
//        TextView posBestCommCon2 = view.findViewById(R.id.posBestCommCon2);
//        ImageView posBestCommLikeImg2 = view.findViewById(R.id.posBestCommLikeImg2);
//        ImageView posBestCommDisLikeImg2 = view.findViewById(R.id.posBestCommDisLikeImg2);
//        TextView posBestCommLike2 = view.findViewById(R.id.posBestCommLike2);
//        TextView posBestCommDisLike2 = view.findViewById(R.id.posBestCommDisLike2);
//
//        TextView posBestCommId3= view.findViewById(R.id.posBestCommId3);
//        TextView posBestCommCon3 = view.findViewById(R.id.posBestCommCon3);
//        ImageView posBestCommLikeImg3 = view.findViewById(R.id.posBestCommLikeImg3);
//        ImageView posBestCommDisLikeImg3 = view.findViewById(R.id.posBestCommDisLikeImg3);
//        TextView posBestCommLike3 = view.findViewById(R.id.posBestCommLike3);
//        TextView posBestCommDisLike3 = view.findViewById(R.id.posBestCommDisLike3);
//
//        TextView posBestCommId4 = view.findViewById(R.id.posBestCommId4);
//        TextView posBestCommCon4 = view.findViewById(R.id.posBestCommCon4);
//        ImageView posBestCommLikeImg4 = view.findViewById(R.id.posBestCommLikeImg4);
//        ImageView posBestCommDisLikeImg4 = view.findViewById(R.id.posBestCommDisLikeImg4);
//        TextView posBestCommLike4 = view.findViewById(R.id.posBestCommLike4);
//        TextView posBestCommDisLike4 = view.findViewById(R.id.posBestCommDisLike4);
//
//        TextView posBestCommId5 = view.findViewById(R.id.posBestCommId5);
//        TextView posBestCommCon5 = view.findViewById(R.id.posBestCommCon5);
//        ImageView posBestCommLikeImg5 = view.findViewById(R.id.posBestCommLikeImg5);
//        ImageView posBestCommDisLikeImg5 = view.findViewById(R.id.posBestCommDisLikeImg5);
//        TextView posBestCommLike5 = view.findViewById(R.id.posBestCommLike5);
//        TextView posBestCommDisLike5 = view.findViewById(R.id.posBestCommDisLike5);

        TextView[] commId = new TextView[emotionCommentsArrayList.size()];
        TextView[] commCon = new TextView[emotionCommentsArrayList.size()];
        TextView[] commLike = new TextView[emotionCommentsArrayList.size()];
        TextView[] commDislike = new TextView[emotionCommentsArrayList.size()];

        int cnt = 0;

        //긍정 댓글 탑 5 뽑기
        for(int i = 0; i < emotionCommentsArrayList.size(); i++){
            if(cnt == 5)
                break;
            if(emotionCommentsArrayList.get(i).get("emotionBool").toString().equals("1")){
                String usrName = emotionCommentsArrayList.get(i).get("usrName").toString();
                String comments = emotionCommentsArrayList.get(i).get("comments").toString();
                String sympathyCount = emotionCommentsArrayList.get(i).get("sympathyCount").toString();
                String antipathyCount = emotionCommentsArrayList.get(i).get("antipathyCount").toString();

                int id = getResources().getIdentifier("posBestCommId" + (i+1), "id", getActivity().getPackageName());
                int con = getResources().getIdentifier("posBestCommCon" + (i+1), "id", getActivity().getPackageName());
                int like = getResources().getIdentifier("posBestCommLike" + (i+1), "id", getActivity().getPackageName());
                int dislike = getResources().getIdentifier("posBestCommDisLike" + (i+1), "id", getActivity().getPackageName());

                commId[i] = view.findViewById(id);
                commCon[i] = view.findViewById(con);
                commLike[i] = view.findViewById(like);
                commDislike[i] = view.findViewById(dislike);

                if(commId[i] == null)
                    break;
                commId[i].setText(usrName);
                commCon[i].setText(comments);
                commLike[i].setText(sympathyCount);
                commDislike[i].setText(antipathyCount);
                cnt++;
            }
            else {
                continue;
            }
        }

//        HashMap<String, String> map1 = emotionCommentsArrayList.get(0);
//        HashMap<String, String> map2 = emotionCommentsArrayList.get(1);
//        HashMap<String, String> map3 = emotionCommentsArrayList.get(2);
//        HashMap<String, String> map4 = emotionCommentsArrayList.get(3);
//        HashMap<String, String> map5 = emotionCommentsArrayList.get(4);
//
//        posBestCommId1.setText(map1.get("usrName"));
//        posBestCommCon1.setText(map1.get("comments"));
//        posBestCommLike1.setText(map1.get("sympathyCount"));
//        posBestCommDisLike1.setText(map1.get("antipathyCount"));
//
//        posBestCommId2.setText(map2.get("usrName"));
//        posBestCommCon2.setText(map2.get("comments"));
//        posBestCommLike2.setText(map2.get("sympathyCount"));
//        posBestCommDisLike2.setText(map2.get("antipathyCount"));
//
//        posBestCommId3.setText(map3.get("usrName"));
//        posBestCommCon3.setText(map3.get("comments"));
//        posBestCommLike3.setText(map3.get("sympathyCount"));
//        posBestCommDisLike3.setText(map3.get("antipathyCount"));
//
//        posBestCommId4.setText(map4.get("usrName"));
//        posBestCommCon4.setText(map4.get("comments"));
//        posBestCommLike4.setText(map4.get("sympathyCount"));
//        posBestCommDisLike4.setText(map4.get("antipathyCount"));
//
//        posBestCommId5.setText(map5.get("usrName"));
//        posBestCommCon5.setText(map5.get("comments"));
//        posBestCommLike5.setText(map5.get("sympathyCount"));
//        posBestCommDisLike5.setText(map5.get("antipathyCount"));
    }
    public void negBestCommentSetter(View view){

        TextView[] commId = new TextView[emotionCommentsArrayList.size()];
        TextView[] commCon = new TextView[emotionCommentsArrayList.size()];
        TextView[] commLike = new TextView[emotionCommentsArrayList.size()];
        TextView[] commDislike = new TextView[emotionCommentsArrayList.size()];

        int cnt = 0;
        int negIdCnt = 1;

        //부정 댓글 탑 5 뽑기
        for(int i = 0; i < emotionCommentsArrayList.size(); i++){
            if(cnt == 5)
                break;
            if(emotionCommentsArrayList.get(i).get("emotionBool").toString().equals("0")){
                String usrName = emotionCommentsArrayList.get(i).get("usrName").toString();
                String comments = emotionCommentsArrayList.get(i).get("comments").toString();
                String sympathyCount = emotionCommentsArrayList.get(i).get("sympathyCount").toString();
                String antipathyCount = emotionCommentsArrayList.get(i).get("antipathyCount").toString();

                int id = getResources().getIdentifier("negBestCommId" + (i - (i-negIdCnt)), "id", getActivity().getPackageName());
                int con = getResources().getIdentifier("negBestCommCon" + (i- (i-negIdCnt)), "id", getActivity().getPackageName());
                int like = getResources().getIdentifier("negBestCommLike" + (i- (i-negIdCnt)), "id", getActivity().getPackageName());
                int dislike = getResources().getIdentifier("negBestCommDisLike" + (i- (i-negIdCnt)), "id", getActivity().getPackageName());

                negIdCnt++;

                commId[i] = view.findViewById(id);
                commCon[i] = view.findViewById(con);
                commLike[i] = view.findViewById(like);
                commDislike[i] = view.findViewById(dislike);

                if(commId[i] == null)
                    break;
                commId[i].setText(usrName);
                commCon[i].setText(comments);
                commLike[i].setText(sympathyCount);
                commDislike[i].setText(antipathyCount);
                cnt++;
            }
            else {
                continue;
            }
        }


//        TextView negBestCommId1 = view.findViewById(R.id.negBestCommId1);
//        TextView negBestCommCon1 = view.findViewById(R.id.negBestCommCon1);
//        ImageView negBestCommLikeImg1 = view.findViewById(R.id.posBestCommLikeImg1);
//        ImageView negBestCommDisLikeImg1 = view.findViewById(R.id.posBestCommDisLikeImg1);
//        TextView negBestCommLike1 = view.findViewById(R.id.negBestCommLike1);
//        TextView negBestCommDisLike1 = view.findViewById(R.id.negBestCommDisLike1);
//
//        TextView negBestCommId2 = view.findViewById(R.id.negBestCommId2);
//        TextView negBestCommCon2 = view.findViewById(R.id.negBestCommCon2);
//        ImageView negBestCommLikeImg2 = view.findViewById(R.id.negBestCommLikeImg2);
//        ImageView negBestCommDisLikeImg2 = view.findViewById(R.id.negBestCommDisLikeImg2);
//        TextView negBestCommLike2 = view.findViewById(R.id.negBestCommLike2);
//        TextView negBestCommDisLike2 = view.findViewById(R.id.negBestCommDisLike2);
//
//        TextView negBestCommId3= view.findViewById(R.id.negBestCommId3);
//        TextView negBestCommCon3 = view.findViewById(R.id.negBestCommCon3);
//        ImageView negBestCommLikeImg3 = view.findViewById(R.id.negBestCommLikeImg3);
//        ImageView negBestCommDisLikeImg3 = view.findViewById(R.id.negBestCommDisLikeImg3);
//        TextView negBestCommLike3 = view.findViewById(R.id.negBestCommLike3);
//        TextView negBestCommDisLike3 = view.findViewById(R.id.negBestCommDisLike3);
//
//        TextView negBestCommId4 = view.findViewById(R.id.negBestCommId4);
//        TextView negBestCommCon4 = view.findViewById(R.id.negBestCommCon4);
//        ImageView negBestCommLikeImg4 = view.findViewById(R.id.negBestCommLikeImg4);
//        ImageView negBestCommDisLikeImg4 = view.findViewById(R.id.negBestCommDisLikeImg4);
//        TextView negBestCommLike4 = view.findViewById(R.id.negBestCommLike4);
//        TextView negBestCommDisLike4 = view.findViewById(R.id.negBestCommDisLike4);
//
//        TextView negBestCommId5 = view.findViewById(R.id.negBestCommId5);
//        TextView negBestCommCon5 = view.findViewById(R.id.negBestCommCon5);
//        ImageView negBestCommLikeImg5 = view.findViewById(R.id.negBestCommLikeImg5);
//        ImageView negBestCommDisLikeImg5 = view.findViewById(R.id.negBestCommDisLikeImg5);
//        TextView negBestCommLike5 = view.findViewById(R.id.negBestCommLike5);
//        TextView negBestCommDisLike5 = view.findViewById(R.id.negBestCommDisLike5);

//        HashMap<String, String> map1 = emotionCommentsArrayList.get(5);
//        HashMap<String, String> map2 = emotionCommentsArrayList.get(6);
//        HashMap<String, String> map3 = emotionCommentsArrayList.get(7);
//        HashMap<String, String> map4 = emotionCommentsArrayList.get(8);
//        HashMap<String, String> map5 = emotionCommentsArrayList.get(9);

//        negBestCommId1.setText(map1.get("usrName"));
//        negBestCommCon1.setText(map1.get("comments"));
//        negBestCommLike1.setText(map1.get("sympathyCount"));
//        negBestCommDisLike1.setText(map1.get("antipathyCount"));
//
//        negBestCommId2.setText(map2.get("usrName"));
//        negBestCommCon2.setText(map2.get("comments"));
//        negBestCommLike2.setText(map2.get("sympathyCount"));
//        negBestCommDisLike2.setText(map2.get("antipathyCount"));
//
//        negBestCommId3.setText(map3.get("usrName"));
//        negBestCommCon3.setText(map3.get("comments"));
//        negBestCommLike3.setText(map3.get("sympathyCount"));
//        negBestCommDisLike3.setText(map3.get("antipathyCount"));
//
//        negBestCommId4.setText(map4.get("usrName"));
//        negBestCommCon4.setText(map4.get("comments"));
//        negBestCommLike4.setText(map4.get("sympathyCount"));
//        negBestCommDisLike4.setText(map4.get("antipathyCount"));
//
//        negBestCommId5.setText(map5.get("usrName"));
//        negBestCommCon5.setText(map5.get("comments"));
//        negBestCommLike5.setText(map5.get("sympathyCount"));
//        negBestCommDisLike5.setText(map5.get("antipathyCount"));
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emotion, container, false);

        Bundle bundle = getArguments();

        tnewsArrayList = (ArrayList<HashMap>) bundle.getSerializable("tnewsArrayList");
        emotionAnalysisArrayList = (ArrayList<HashMap>) bundle.getSerializable("emotionAnalysisArrayList");
        emotionCommentsArrayList = (ArrayList<HashMap>) bundle.getSerializable("emotionCommentsArrayList");

        emotionChart(view);
        emotionSubChart(view);
        posBestCommentSetter(view);
        negBestCommentSetter(view);

        return view;
    }
}

class MapComparator implements Comparator<HashMap<String, String>> {

    private final String key;

    public MapComparator(String key) {
        this.key = key;
    }

    @Override
    public int compare(HashMap<String, String> first, HashMap<String, String> second) {
        int result = first.get(key).compareTo(second.get(key));
        return result;
    }
}
