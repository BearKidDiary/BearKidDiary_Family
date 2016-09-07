package bearkid.com.bearkiddiaryfamily.presenter;

import android.util.Log;
import android.widget.TabHost;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.KidInfoModel;
import bearkid.com.bearkiddiaryfamily.model.bean.Height;
import bearkid.com.bearkiddiaryfamily.model.bean.Kid;
import bearkid.com.bearkiddiaryfamily.ui.activity.KidInfoActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IKidInfoView;
import bearkid.com.bearkiddiaryfamily.utils.DateTimePickerUtil;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by YrenChoi on 2016/7/20.
 * 孩子信息展示界面适配器
 */
public class KidInfoPresenter {

    private IKidInfoView view;
    private Long kidId;
    private Kid kid;
    public KidInfoPresenter(KidInfoActivity view, Long kidId) {
        this.view = view;
        this.kidId = kidId;
    }

    public void init() {
        setKidInfo();
        setHeightData();
        setWeightData();
        setVisionData();
    }

    public void refreshInfo() {
        setKidInfo();
    }

    public void refreshBodyData() {
        setHeightData();
        setWeightData();
        setVisionData();
    }

    private void setKidInfo() {
        KidInfoModel.getKidInfo(kidId, null, null, null, null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.size() == 1) {
                        kid = result.get(0);
                        view.showName(kid.getKname());
                        view.showBirthday(DateTimePickerUtil.getFormatDate(kid.getKbirthday()));
                        view.showGender(kid.getKsex());
                        view.setAsk(kid.getKask());
                    }
                }, throwable -> {
                    Log.e("KidInfoPresenter", "获取孩子信息失败" + throwable);
                });
    }

    public void initKid() {
        view.initKid(kid);
    }

    private void setHeightData() {

        view.clearHeightData();

        KidInfoModel.getKidHeight(kidId, "asc")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(kidResult -> {
                    if (kidResult.getResultCode() == 0){
                        view.showHeight(kidResult.getData());
                        Log.d("加载孩子身高数据", "成功");
                    }else {
                        Log.d("加载孩子身高数据", "失败");
                    }
                }, Throwable -> {
                    Log.d("加载孩子身高数据", "异常" + Throwable.toString());
                });
    }

    private void setWeightData() {

        view.clearWeightData();

        KidInfoModel.getKidWeight(kidId, "asc")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(kidResult -> {
                    if (kidResult.getResultCode() == 0){
                        view.showWeight(kidResult.getData());
                    }else {
                        Log.d("加载孩子体重数据", "失败");
                    }
                }, Throwable -> {
                    Log.d("加载孩子体重数据", "异常" + Throwable.toString());
                });
    }

    private void setVisionData() {

        view.clearVisionData();

        KidInfoModel.getKidVision(kidId, "asc")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(kidResult -> {
                    if (kidResult.getResultCode() == 0){
                        view.showVision(kidResult.getData());
                    }else {
                        Log.d("加载孩子视力数据", "失败");
                    }
                }, Throwable -> {
                    Log.d("加载孩子视力数据", "异常" + Throwable.toString());
                });

    }

}
