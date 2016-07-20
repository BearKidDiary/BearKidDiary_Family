package bearkid.com.bearkiddiaryfamily.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/7/18.
 */
public class DataAnalysisModel {

    public static final int HEIGHT = 0;
    public static final int WEIGHT = 1;
    public static final int VISION = 2;

    List<List<Double>> chartlists;
    List<Double> chartlist;
    List<String> yearList = new ArrayList<>();

    public DataAnalysisModel(){
        getYearList();
    }

    public interface LoadDataListLisenter{
        void onSuccess(List<List<Double>> chartlists, List<String> yearList);
        void onFailure(Exception e);
    }

    /**
     * 获取所有的年份（从网络或者从本地）
     */
    public void getYearList(){
        yearList.add("2016年");
        yearList.add("2015年");
        yearList.add("2014年");
    }

    /**
     * 获取图表数据
     * @param Type 是身高，体重，视力
     */
    public void LoadDataList(int Type , LoadDataListLisenter listLisenter) {
        chartlists = new ArrayList<>();
        switch (Type){
            case HEIGHT:
                //TODO:获取图表显示的数据
                chartlist = new ArrayList<>();
                chartlist.add(161.0);
                chartlist.add(161.0);
                chartlist.add(162.0);
                chartlist.add(162.5);
                chartlist.add(162.5);
                chartlist.add(163.0);
                chartlist.add(163.0);
                chartlist.add(163.0);
                chartlist.add(163.0);
                chartlist.add(165.0);
                chartlist.add(165.0);
                chartlist.add(165.5);
                chartlists.add(chartlist);
                listLisenter.onSuccess(chartlists,yearList);
                break;
            case WEIGHT:
                chartlist = new ArrayList<>();
                chartlist.add(50.0);
                chartlist.add(51.0);
                chartlist.add(52.0);
                chartlist.add(51.5);
                chartlist.add(50.5);
                chartlist.add(52.0);
                chartlist.add(53.0);
                chartlist.add(53.5);
                chartlist.add(54.0);
                chartlist.add(54.0);
                chartlist.add(55.0);
                chartlist.add(55.5);
                chartlists.add(chartlist);
                listLisenter.onSuccess(chartlists,yearList);
                break;
            case VISION:
                List<Double> rightchartDataList = new ArrayList<>();
                List<Double> leftchartDataList = new ArrayList<>();
                for (int i = 0; i < 13; i++) {
                    rightchartDataList.add(5.2);
                    leftchartDataList.add(5.1);
                }
                chartlists.add(rightchartDataList);
                chartlists.add(leftchartDataList);
                listLisenter.onSuccess(chartlists,yearList);
                break;
        }
    }
}
