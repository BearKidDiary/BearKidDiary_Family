package bearkid.com.bearkiddiaryfamily.presenter;

import android.util.Log;
import android.widget.TabHost;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.KidInfoModel;
import bearkid.com.bearkiddiaryfamily.model.bean.Height;
import bearkid.com.bearkiddiaryfamily.ui.activity.KidInfoActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IKidInfoView;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by YrenChoi on 2016/7/20.
 * 孩子信息展示界面适配器
 */
public class KidInfoPresenter {

    private IKidInfoView view;
    private Long kidId;
    public KidInfoPresenter(KidInfoActivity view, Long kidId) {
        this.view = view;
        this.kidId = kidId;
    }

    public void init() {
        setHeightData();
        setWeightData();
        setVisionData();
        setExhortData();
    }

    private void setHeightData() {

        view.clearHeightData();

        KidInfoModel.getKidBodyData(kidId, "asc", "Height")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(kidResult -> {
                    if (kidResult.getResultCode() == 0){
                        view.showHeight(kidResult.getData());
                    }else {
                        Log.d("加载孩子身高数据", "失败");
                    }
                }, Throwable -> {
                    Log.d("加载孩子身高数据", "异常");
                });
    }

    private void setWeightData() {

        view.clearWeightData();

        KidInfoModel.getKidBodyData(kidId, "asc", "Weight")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(kidResult -> {
                    if (kidResult.getResultCode() == 0){
                        view.showWeight(kidResult.getData());
                    }else {
                        Log.d("加载孩子体重数据", "失败");
                    }
                }, Throwable -> {
                    Log.d("加载孩子体重数据", "异常");
                });
    }

    private void setVisionData() {

        view.clearVisionData();

        KidInfoModel.getKidBodyData(kidId, "asc", "Vision")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(kidResult -> {
                    if (kidResult.getResultCode() == 0){
                        view.showVision(kidResult.getData());
                    }else {
                        Log.d("加载孩子视力数据", "失败");
                    }
                }, Throwable -> {
                    Log.d("加载孩子视力数据", "异常");
                });

    }

    private void setExhortData() {

    }
}
