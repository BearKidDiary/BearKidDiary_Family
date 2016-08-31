package bearkid.com.bearkiddiaryfamily.presenter;

import android.util.Log;

import bearkid.com.bearkiddiaryfamily.model.KidInfoModel;
import bearkid.com.bearkiddiaryfamily.ui.fragment.MeFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.ifragment.IMeFragment;
import bearkid.com.bearkiddiaryfamily.utils.LocalDB;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by YarenChoi on 2016/8/24.
 *
 */
public class MePresenter {

    IMeFragment view;
    String Uphone;

    public MePresenter(MeFragment view) {
        this.view = view;
        this.Uphone = new LocalDB(view.getContext()).getPhoneNum();
    }

    public void loadKidsInfo() {
        KidInfoModel.searchKid(Uphone)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(kidResult -> {
                    if (kidResult.getResultCode() == 0){
                        view.refreshKidList(kidResult.getData());
                        Log.d("加载孩子", kidResult.getData().get(0).getKname());
                        Log.d("加载孩子", kidResult.getData().get(0).getKsex());
                        Log.d("加载孩子", kidResult.getData().get(0).getKask());
                    }else {
                        Log.d("加载孩子", "失败" + kidResult.getResultCode());
                    }
                }, Throwable -> {
                    Log.d("加载孩子", "异常");
                });
    }
}
