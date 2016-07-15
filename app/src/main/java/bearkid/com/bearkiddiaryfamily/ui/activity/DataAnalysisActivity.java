package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.achartengine.GraphicalView;
import org.achartengine.chart.DoughnutChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.utils.Chart;

public class DataAnalysisActivity extends BaseActivity {

    private static final int HEIGHT = 0;
    private static final int WEIGHT = 1;

    private RelativeLayout heightRlayout;
    private RelativeLayout weightRlayout;

    private Chart heightChart;
    private Chart weightChart;

    private GraphicalView heightGraphicalView;
    private GraphicalView weightGraphicalView;

    private List<Double> chartDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analysis);

        initView();
        initData();
    }

    public void setHeigthChartView(){
        heightChart.setMultipleSeriesDataset("身高");
        heightChart.setMultipleSeriesRenderer(180, "厘米（cm）", "月", "身高", Color.BLACK, Color.BLACK, Color.BLACK, Color.WHITE);
        heightGraphicalView = heightChart.getmGraphcalView();

        for (int i = 1;i < 13; i++){
            heightChart.addXTextLabel(i, i + "");
        }
        heightRlayout.addView(heightGraphicalView,new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dpTopx(800)
        ));
    }

    /**
     * 获取要显示在图表上的数据
     * @param type 为HEIGHT或者WEIGHT进行区分获取数据
     * @return
     */
    public List<Double> getDataList(int type){
        //TODO:将孩子的身高和体重数据进行获取
        chartDataList = new ArrayList<>();
        //以下是测试的假数据
        switch (type){
            case 0:
                chartDataList.add(161.0);
                chartDataList.add(162.0);
                chartDataList.add(162.0);
                chartDataList.add(163.0);
                chartDataList.add(163.0);
                chartDataList.add(163.0);
                chartDataList.add(164.0);
                chartDataList.add(164.0);
                chartDataList.add(167.0);
                chartDataList.add(167.0);
                chartDataList.add(168.0);
                chartDataList.add(168.0);
                break;
            case 1:
                chartDataList.add(5.2);
                chartDataList.add(5.2);
                chartDataList.add(5.2);
                chartDataList.add(5.2);
                chartDataList.add(5.2);
                chartDataList.add(5.2);
                chartDataList.add(5.2);
                break;
        }
        return chartDataList;
    }

    public void initData(){
        heightChart = new Chart(this);
        weightChart = new Chart(this);

    }

    public void initView(){
        heightRlayout = (RelativeLayout) findViewById(R.id.rlayout_kid_height);
        weightRlayout = (RelativeLayout) findViewById(R.id.rlayout_kid_weight);
    }


    /**
     * 将dp单位转化成px
     * @param dp dp大小
     * @return px大小
     */
    public int dpTopx(float dp){
        int px;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float density = displayMetrics.density;
        px = (int) (dp * density);
        return px;
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, DataAnalysisActivity.class));
    }
}
