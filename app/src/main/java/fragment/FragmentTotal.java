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

public class FragmentTotal extends Fragment {
    ViewPager viewPager;

    public void Fragment_Total(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_total, container, false);

        ResultActivity resultActivity = new ResultActivity();
        resultActivity.sexRateChart(view);
        resultActivity.ageRateChart(view);
        resultActivity.timeLineChart(view);
        resultActivity.commentSetter(view);

        return view;
    }
}
