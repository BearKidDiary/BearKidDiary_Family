package bearkid.com.bearkiddiaryfamily.presenter;

import bearkid.com.bearkiddiaryfamily.ui.activity.KidInfoActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IKidInfoView;

/**
 * Created by admin on 2016/7/20.
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
                        initKid();
                    }
                }, throwable -> {
                    Log.e("KidInfoPresenter", "获取孩子信息失败" + throwable);
                });
    }

    /**
     * 将查询到的kid对象返回给activity
     * 用于跳转到kid修改界面
     */
    private void initKid() {
        view.initKid(kid);
    }

    private void setHeightData() {

        view.clearHeightData();

        KidInfoModel.getKidHeight(kidId, "desc")
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

        KidInfoModel.getKidWeight(kidId, "desc")
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

        KidInfoModel.getKidVision(kidId, "desc")
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
