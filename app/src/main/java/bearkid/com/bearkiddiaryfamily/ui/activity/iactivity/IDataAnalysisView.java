package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

import java.util.List;

/**
 * Created by admin on 2016/7/18.
 */
public interface IDataAnalysisView {
    /**
     * 显示加载
     */
    void showProgress();

    /**
     * 加载得到的图表数据
     * @param chartlists
     */
    void addDataList(List<List<Double>> chartlists,List<String> yearList);

    /**
     * 隐藏加载
     */
    void hideProgress();

    /**
     * 显示错误信息
     */
    void showLoadFailMsg();
}
