package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.ResultActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class FragmentEmotion extends Fragment {
    ViewPager viewPager;

    public void Fragment_Emotion(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emotion, container, false);
        ResultActivity resultActivity = new ResultActivity();

        resultActivity.emotionChart(view);
//        resultActivityy.posKeywordChart(view);
//        resultActivityy.negKeywordChart(view);
        return view;
    }
}
