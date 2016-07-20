package bearkid.com.bearkiddiaryfamily.presenter;

import android.content.Context;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.DataAnalysisModel;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IDataAnalysisView;

/**
 * Created by admin on 2016/7/18.
 */
public class DataAnalysisPresenter implements DataAnalysisModel.LoadDataListLisenter {
    private DataAnalysisModel dataAnalysisModel;
    private IDataAnalysisView dataAnalysisView;
    private Context context;

    public DataAnalysisPresenter(IDataAnalysisView dataAnalysisView, Context context){
        this.dataAnalysisView = dataAnalysisView;
        this.context = context;
        this.dataAnalysisModel = new DataAnalysisModel();
    }

    public void LoadData(int type){
        dataAnalysisModel.LoadDataList(type,this);
    }

    @Override
    public void onSuccess(List<List<Double>> chartlists, List<String> yearList) {
//        dataAnalysisView.hideProgress();
        dataAnalysisView.addDataList(chartlists,yearList);
    }

    @Override
    public void onFailure(Exception e) {
//        dataAnalysisView.hideProgress();
//        dataAnalysisView.showLoadFailMsg();
    }
}
