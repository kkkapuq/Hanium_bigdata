package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hanium.moamoa.R;
//import com.example.myapplication.ResultActivity;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class FragmentEtc extends Fragment {
    ViewPager viewPager;

    ArrayList<HashMap> relevantArticleArrayList;

    public void Fragment_Etc(){

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

        String[] url = new String[relevantArticleArrayList.size()];
        for(int i = 0; i < relevantArticleArrayList.size(); i++){
            Object temp = relevantArticleArrayList.get(i).get("flag");
            if(temp == null)
                continue;
            else {
                for(int j = 0; j < 3; j++){
                    Object temp2 = relevantArticleArrayList.get(i).get("simArticleImg"+Integer.toString(j+1));
                    if(temp2 == null)
                        break;
                    url[j] = String.valueOf(temp2);
                }
            }
        }

//        for(int i = 0; i < relevantArticleArrayList.size(); i++){
//            if(url[i] != null)
//                Glide.with(this).load(url).into(simArticleImg);
//        }

        //1번 기사 정보 세팅
        HashMap<String, String> map = relevantArticleArrayList.get(0);
        if(map != null)
            Glide.with(this).load(url[0]).into(simArticleImg1);
        else
            simArticleImg1.setImageResource(R.drawable.img_noimg);
        simArticleTitle1.setText(map.get("articleName"));
        simArticleCon1.setText(map.get("articleContent"));

        //2번 기사 정보 세팅
        map = relevantArticleArrayList.get(1);
        if(map != null)
            Glide.with(this).load(url[1]).into(simArticleImg2);
        else
            simArticleImg2.setImageResource(R.drawable.img_noimg);
        simArticleTitle2.setText(map.get("articleName"));
        simArticleCon2.setText(map.get("articleContent"));

        //3번 기사 정보 세팅
        map = relevantArticleArrayList.get(2);
        if(map != null)
            Glide.with(this).load(url[2]).into(simArticleImg3);
        else
            simArticleImg3.setImageResource(R.drawable.img_noimg);
        simArticleTitle3.setText(map.get("articleName"));
        simArticleCon3.setText(map.get("articleContent"));
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_etc, container, false);

        Bundle bundle = getArguments();

        relevantArticleArrayList = (ArrayList<HashMap>) bundle.getSerializable("relevantArticleArrayList");

        simArticleSetter(view);

        return view;
    }
}
