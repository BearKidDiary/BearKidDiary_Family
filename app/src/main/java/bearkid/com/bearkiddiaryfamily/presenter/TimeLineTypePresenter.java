package bearkid.com.bearkiddiaryfamily.presenter;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.KidInfoModel;
import bearkid.com.bearkiddiaryfamily.model.TimeLineModel;
import bearkid.com.bearkiddiaryfamily.model.bean.Kid;
import bearkid.com.bearkiddiaryfamily.model.bean.Result;
import bearkid.com.bearkiddiaryfamily.model.bean.ResultCode;
import bearkid.com.bearkiddiaryfamily.model.bean.TimeLine;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
import bearkid.com.bearkiddiaryfamily.ui.fragment.TimeLineTypeFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.ifragment.ITimeLineTypeFragment;
import bearkid.com.bearkiddiaryfamily.utils.LocalDB;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 添加时间轴页面的控制器
 *
 * @author 张宇
 */
public class TimeLineTypePresenter {
    private static final String TAG = "TimeLineTypePresenter";
    private final ITimeLineTypeFragment view;
    private List<Kid> kids;
    private LocalDB db;

    public TimeLineTypePresenter(TimeLineTypeFragment view) {
        this.view = view;
        db = new LocalDB(view.getContext());
    }

    public void init() {
        final String phoneNum = db.getPhoneNum();
        KidInfoModel.getKidInfo(null, phoneNum, null, null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(kids -> {
                    List<String> name = new ArrayList<>();
                    for (Kid kid : kids) {
                        name.add(kid.getKname());
                    }
                    view.setChidrenName(name);
                    TimeLineTypePresenter.this.kids = kids;
                }, throwable -> {
                    view.showToast("获取孩子信息失败");
                    Log.e(TAG, "error: " + throwable);
                });
    }

    public void uploadTimeLine(String content, String type, int typeLogo, @Nullable File pic1, @Nullable File pic2, @Nullable File pic3) {
        final String phone = db.getPhoneNum();
        final String kidName = view.getCurrentKidName();
        for (Kid kid : kids) {
            if (kid.getKname().equals(kidName)) {
                TimeLineModel.postTimeLine(kid.getKid(), phone, null, content, new Date().getTime(), type, typeLogo, null, null, null)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(result -> {
                            Log.i(TAG, "resultCode = " + result.getResultCode() + " , desc = " + result.getResultMessage());
                        }, throwable -> {
                            Log.e(TAG, "error: " + throwable);
                        });
            }
            break;
        }
    }
}
