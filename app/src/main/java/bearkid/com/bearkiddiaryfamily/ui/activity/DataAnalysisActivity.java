package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Releasable;

import org.achartengine.GraphicalView;
import org.achartengine.chart.DoughnutChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.DataAnalysisModel;
import bearkid.com.bearkiddiaryfamily.presenter.DataAnalysisPresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IDataAnalysisView;
import bearkid.com.bearkiddiaryfamily.utils.Chart;

public class DataAnalysisActivity extends BaseActivity implements IDataAnalysisView, AdapterView.OnItemSelectedListener, View.OnClickListener {

    /**
     * 身高，体重，视力图表
     */
    private RelativeLayout heightRlayout;
    private RelativeLayout weightRlayout;
    private RelativeLayout visionRlayout;

    private Chart heightChart;
    private Chart weightChart;
    private Chart visionChart;

    private GraphicalView heightGraphicalView;
    private GraphicalView weightGraphicalView;
    private GraphicalView visionGraphicalview;

    /**
     * Spinner
     */
    private Spinner yearSpinner;
    private List<String> yearList;
    private ArrayAdapter<String> adapterSpinner;

    //身高，体重的数据List
    private List<List<Double>> chartDataList;

    //Presenter
    private DataAnalysisPresenter dataAnalysisPresenter;

    //返回按钮
    private ImageView backImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analysis);

        initView();
        initData();

        loadData(DataAnalysisModel.HEIGHT);
        setHeightChartView();
        loadData(DataAnalysisModel.WEIGHT);
        setWeightChartView();
        loadData(DataAnalysisModel.VISION);
        setVisionChartView();

        setYearSpinner();
    }

    /**
     * presenter中加载获取图表数据List
     *
     * @param type 要加载什么数据（身高，体重，视力）
     */
    public void loadData(int type) {
        dataAnalysisPresenter.LoadData(type);
    }

    /**
     * 身高体重视力的图表的显示
     */
    public void setHeightChartView() {
        heightChart.setMultipleSeriesDataset(new String[]{"身高"}, chartDataList);
        heightChart.setMultipleSeriesRenderer(200,12,"身高", "月", "厘米（cm）",Color.BLACK, Color.BLACK, Color.BLACK, new int[]{0xFFFF0000});
        heightGraphicalView = heightChart.getmGraphcalView();
        for (int i = 1; i < 13; i++) {
            heightChart.addXTextLabel(i, i + "");
        }
        heightRlayout.addView(heightGraphicalView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dpTopx(400)
        ));
    }

    public void setWeightChartView() {
        weightChart.setMultipleSeriesDataset(new String[]{"体重"}, chartDataList);
        weightChart.setMultipleSeriesRenderer(100,12,"体重", "月", "千克（kg）",Color.BLACK, Color.BLACK, Color.BLACK, new int[]{0xFFFF0000});
        weightGraphicalView = weightChart.getmGraphcalView();
        for (int i = 1; i < 13; i++) {
            weightChart.addXTextLabel(i, i + "");
        }
        weightRlayout.addView(weightGraphicalView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dpTopx(400)
        ));
    }

    public void setVisionChartView() {
        visionChart.setMultipleSeriesDataset(new String[]{"左眼", "右眼"}, chartDataList);
        visionChart.setMultipleSeriesRenderer(6.0, 12, "视力", "月", "视力值", Color.BLACK, Color.BLACK, Color.BLACK, new int[]{0xFFFF0000, 0xFF00C8FF});
        visionGraphicalview = visionChart.getmGraphcalView();

        for (int i = 1; i < 13; i++) {
            visionChart.addXTextLabel(i, i + "");
        }
        visionRlayout.addView(visionGraphicalview, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dpTopx(400)
        ));
    }

    public void initData() {
        heightChart = new Chart(this);
        weightChart = new Chart(this);
        visionChart = new Chart(this);

        dataAnalysisPresenter = new DataAnalysisPresenter(this, this);
    }

    public void initView() {
        heightRlayout = (RelativeLayout) findViewById(R.id.rlayout_kid_height);
        weightRlayout = (RelativeLayout) findViewById(R.id.rlayout_kid_weight);
        visionRlayout = (RelativeLayout) findViewById(R.id.rlayout_kid_vision);

        yearSpinner = (Spinner) findViewById(R.id.spinner_data_analysis);
        backImg = (ImageView) findViewById(R.id.img_title_back_data_analysis);
        backImg.setOnClickListener(this);
    }

    /**
     * Spinner设置
     */
    public void setYearSpinner(){
        adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearList);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapterSpinner);
        yearSpinner.setOnItemSelectedListener(this);
    }

    /**
     * 将dp单位转化成px
     *
     * @param dp dp大小
     * @return px大小
     */
    public int dpTopx(float dp) {
        int px;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float density = displayMetrics.density;
        px = (int) (dp * density);
        return px;
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, DataAnalysisActivity.class));
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void addDataList(List<List<Double>> chartlists, List<String> yearList) {
        chartDataList = new ArrayList<>();
        this.yearList = yearList;
        this.chartDataList = chartlists;
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showLoadFailMsg() {

    }

    /**
     * Spinner点击事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(DataAnalysisActivity.this,yearList.get(position),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_title_back_data_analysis:
                finish();
                break;
        }
    }
}
