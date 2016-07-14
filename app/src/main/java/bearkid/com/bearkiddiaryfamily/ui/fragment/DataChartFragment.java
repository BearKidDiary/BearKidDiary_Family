package bearkid.com.bearkiddiaryfamily.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.achartengine.GraphicalView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.utils.Chart;

/**
 * Created by admin on 2016/7/13.
 */
public class DataChartFragment extends BaseFragment {

    private View view;
    private Context context;
    private LinearLayout heightRlayout;
    private RelativeLayout weightRlayout;

    private Chart heightChart;
    private Chart weightChart;
    private GraphicalView heightGraphcalView;
    private GraphicalView weightGraphcalView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_data_chart, container, false);
        context = getContext();
        initView();

        setHeightChartView();

        return view;
    }

    private void initView(){
        heightRlayout = (LinearLayout) view.findViewById(R.id.rlayout_kid_height);
//        weightRlayout = (RelativeLayout) view.findViewById(R.id.rlayout_kid_weight);

        heightChart = new Chart(getActivity().getApplicationContext());
        weightChart = new Chart(context);
    }

    private void setHeightChartView(){
        heightChart.setMultipleSeriesDataset("身高");
        //Color.argb(255, 255, 64, 129)
        heightChart.setMultipleSeriesRenderer(180, "厘米（cm）", "月", "身高", Color.BLACK, Color.BLACK, Color.BLACK, Color.WHITE);
        heightGraphcalView = heightChart.getmGraphcalView();

        for(int i = 1; i < 13; i++){
            heightChart.addXTextLabel(i, i + "");
        }
        heightRlayout.addView(heightGraphcalView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        List<Integer> datalist = new ArrayList<>();

        datalist.add(130);
        datalist.add(131);
        datalist.add(134);
        datalist.add(136);
        datalist.add(136);
        datalist.add(137);
        datalist.add(137);
        datalist.add(137);
        datalist.add(138);
        datalist.add(138);
        datalist.add(138);
        datalist.add(138);
        datalist.add(140);

        for (int j = 1; j < 13; j++){
            heightChart.updataChart(j,datalist.get(j-1));
            Log.d("chart", "updataChart ");
        }

        Log.d("chart","执行了");
    }
}
