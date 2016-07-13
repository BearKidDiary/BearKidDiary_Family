package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.adapter.FragmentAdapter;
import bearkid.com.bearkiddiaryfamily.ui.fragment.DataChartFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.TimeLineFragment;

public class DataAnalysisActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;
    private List<Fragment> fragmentList;

    private TimeLineFragment timeLineFragment;
    private DataChartFragment dataChartFragment;

    private TextView timelineText;
    private TextView chartText;
    private ImageView holoImg;
    private ImageView backImg;
    //当前页面
    private int currentIndex;
    //屏幕宽度
    private int screenWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analysis);

        initView();
        initBottomHoloWidth();
        setTextColor();
    }

    public void initView(){
        viewPager = (ViewPager) findViewById(R.id.viewpager_data_analysis);
        timelineText = (TextView) findViewById(R.id.txt_tab_timeline);
        chartText = (TextView) findViewById(R.id.txt_tab_chart);
        holoImg = (ImageView) findViewById(R.id.img_tab_bottom_holo);
        backImg = (ImageView) findViewById(R.id.img_title_back_data_analysis);

        fragmentList = new ArrayList<>();
        timeLineFragment = new TimeLineFragment();
        dataChartFragment = new DataChartFragment();
        //fragmentList添加Fragment
        fragmentList.add(timeLineFragment);
        fragmentList.add(dataChartFragment);

        backImg.setOnClickListener(this);

        adapter = new FragmentAdapter(this.getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holoImg.getLayoutParams();

        if (currentIndex == 0 && position == 0){
            lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 2) + currentIndex * (screenWidth / 2));
        }else if (currentIndex == 1 && position == 0){
            lp.leftMargin = (int) (-(1 - positionOffset) * (screenWidth * 1.0 / 2) + currentIndex * (screenWidth / 2));
        }else if (currentIndex == 1 && position == 1){
            lp.leftMargin = (int) (positionOffset * (screenWidth * 1.0 / 2) + currentIndex * (screenWidth / 2));
        }
        holoImg.setLayoutParams(lp);
    }

    @Override
    public void onPageSelected(int position) {
        setTextColor();
        switch (position){
            case 0:
                timelineText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case 1:
                chartText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_title_back_data_analysis:
                finish();
                break;
        }
    }

    public void initBottomHoloWidth(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holoImg.getLayoutParams();
        lp.width = screenWidth / 2;
        holoImg.setLayoutParams(lp);

    }

    /**
     * 设置时间轴，图表的tab颜色为黑色
     */
    private void setTextColor(){
        timelineText.setTextColor(Color.BLACK);
        chartText.setTextColor(Color.BLACK);
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,DataAnalysisActivity.class));
    }


}
