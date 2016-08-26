package bearkid.com.bearkiddiaryfamily.presenter;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.KidInfoModel;
import bearkid.com.bearkiddiaryfamily.model.TimeLineModel;
import bearkid.com.bearkiddiaryfamily.model.bean.Kid;
import bearkid.com.bearkiddiaryfamily.model.bean.Result;
import bearkid.com.bearkiddiaryfamily.model.bean.ResultCode;
import bearkid.com.bearkiddiaryfamily.model.bean.TimeLine;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineBodyDialog;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineDialog;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineEatDialog;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineSportDialog;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineStudyDialog;
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
public class TimeLineTypePresenter implements View.OnClickListener {
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

    @Override
    public void onClick(View v) {
        Long Kid = null;
        final String name = view.getCurrentKidName();
        for (Kid kid : kids) {
            if (kid.getKname().equals(name)) {
                Kid = kid.getKid();
                break;
            }
        }
        if (Kid == null) return;

        int type = TimeLine.Type.BODY;
        switch (v.getId()) {
            case R.id.btn_timeline_camera:
                type = TimeLine.Type.CAMERA;
                break;
            case R.id.btn_timeline_body:
                type = TimeLine.Type.BODY;
                break;
            case R.id.btn_timeline_first_time:
                type = TimeLine.Type.FIRSTTIME;
                break;
            case R.id.btn_timeline_eat:
                type = TimeLine.Type.EAT;
                break;
            case R.id.btn_timeline_sport:
                type = TimeLine.Type.SPORT;
                break;
            case R.id.btn_timeline_study:
                type = TimeLine.Type.STUDY;
                break;
        }
        TimeLineDialog.show(view.getChildFragmentManager(), type, Kid);
    }
}
