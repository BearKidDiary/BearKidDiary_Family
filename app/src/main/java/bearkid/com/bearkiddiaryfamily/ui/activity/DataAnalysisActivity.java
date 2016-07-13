package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.adapter.FragmentAdapter;
import bearkid.com.bearkiddiaryfamily.ui.fragment.DataChartFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.TimeLineFragment;

public class DataAnalysisActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;
    private List<Fragment> fragmentList;

    private TimeLineFragment timeLineFragment;
    private DataChartFragment dataChartFragment;

    private TextView timelineText;
    private TextView chartText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analysis);

        initView();
    }

    public void initView(){
        viewPager = (ViewPager) findViewById(R.id.viewpager_data_analysis);
        timelineText = (TextView) findViewById(R.id.txt_tab_timeline);
        chartText = (TextView) findViewById(R.id.txt_tab_chart);

        fragmentList = new ArrayList<>();
        timeLineFragment = new TimeLineFragment();
        dataChartFragment = new DataChartFragment();
        //fragmentList添加Fragment
        fragmentList.add(timeLineFragment);
        fragmentList.add(dataChartFragment);


        adapter = new FragmentAdapter(this.getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

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

    private void setTextColor(){
        timelineText.setTextColor(Color.BLACK);
        chartText.setTextColor(Color.BLACK);
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context,DataAnalysisActivity.class));
    }
}
