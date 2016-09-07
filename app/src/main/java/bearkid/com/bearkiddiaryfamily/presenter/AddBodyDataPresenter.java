package bearkid.com.bearkiddiaryfamily.presenter;

import android.util.Log;

import bearkid.com.bearkiddiaryfamily.model.KidInfoModel;
import bearkid.com.bearkiddiaryfamily.ui.activity.AddBodyDataActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IAddBodyDataView;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by YarenChoi on 2016/8/29.
 * 添加孩子身体数据适配器
 */
public class AddBodyDataPresenter {

    private IAddBodyDataView view;
    private Long kidId;

    public AddBodyDataPresenter(AddBodyDataActivity view, Long kidId) {
        this.view = view;
        this.kidId = kidId;
    }

    public void  addBodyData() {

        view.showProgress();

        KidInfoModel.addBodyData(kidId,
                view.getHeightDate(),
                view.getHeight(),
                view.getWeightDate(),
                view.getWeight(),
                view.getVisionDate(),
                view.getLeftVision(),
                view.getRightVision())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    view.hideProgress();
                    if (result.getResultCode() == 0) {
//                        view.showResult("添加成功!");
                        view.exit();
                    } else {
//                        view.showResult("添加失败,请重试!");
//                        Log.d("添加身体数据", result.getResultCode()+"");
//                        Log.d("添加身体数据", kidId+"");
                    }
                }, throwable -> {
                    view.hideProgress();
                    view.showResult("添加失败,异常!");
                });
    }

}
