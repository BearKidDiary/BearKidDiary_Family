package bearkid.com.bearkiddiaryfamily.presenter;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.util.Date;

import bearkid.com.bearkiddiaryfamily.model.TimeLineModel;
import bearkid.com.bearkiddiaryfamily.model.bean.Kid;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineDialog;
import bearkid.com.bearkiddiaryfamily.ui.dialog.idialog.ITimeLineView;
import bearkid.com.bearkiddiaryfamily.utils.LocalDB;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by admin on 2016/8/24.
 */
public class TimeLinePostPresenter {
    private ITimeLineView view;
    private LocalDB db;
    private final static String TAG = "TimeLinePostPresenter";

    public TimeLinePostPresenter(TimeLineDialog view) {
        this.view = view;
        db = new LocalDB(view.getContext());
    }

    public void uploadTimeLine() {
        final String phone = db.getPhoneNum();
        final Long Kid = view.getKid();
        final String content = view.getContent();
        final Integer typeLogo = view.getTypeLogo();
        final String type = view.getType();
        TimeLineModel.postTimeLine(Kid, phone, null, content, new Date().getTime(), type, typeLogo, null, null, null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Log.i(TAG, "resultCode = " + result.getResultCode() + " , desc = " + result.getResultMessage());
                }, throwable -> {
                    Log.e(TAG, "error: " + throwable);
                });
    }
}
