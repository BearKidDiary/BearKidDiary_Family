package bearkid.com.bearkiddiaryfamily.presenter;

import android.util.Log;

import java.util.Date;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.TimeLineModel;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineDialog;
import bearkid.com.bearkiddiaryfamily.ui.dialog.idialog.ITimeLineView;
import bearkid.com.bearkiddiaryfamily.utils.LocalDB;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadBatchListener;
import rx.android.schedulers.AndroidSchedulers;

/**
 * 发布时间轴的控制器
 */
public class TimeLinePostPresenter {
    private ITimeLineView view;
    private LocalDB db;
    private final static String TAG = "TimeLinePostPresenter";

    public TimeLinePostPresenter(TimeLineDialog view) {
        this.view = view;
        db = new LocalDB(view.getContext());
    }

    /**
     * 发布时间轴事件
     */
    public void uploadTimeLine() {
        final String phone = db.getPhoneNum();
        final Long Kid = view.getKid();
        final String content = view.getContent();
        final Integer typeLogo = view.getTypeLogo();
        final String type = view.getType();
        final List<String> pic = view.getPicPath();

        if (content.trim().equals("")) {
            view.showError("内容不能为空");
            return;
        }

        if (pic.size() == 0) {
            post(Kid, phone, content, type, typeLogo, null, null, null);
        } else {
            final String[] paths = new String[pic.size()];
            pic.toArray(paths);
            BmobFile.uploadBatch(paths, new UploadBatchListener() {
                @Override
                public void onSuccess(List<BmobFile> files, List<String> urls) {
                    if (urls.size() == paths.length) {//如果数量相等，则代表文件全部上传完成
                        String[] pic = new String[]{null, null, null};
                        urls.toArray(pic);
                        post(Kid, phone, content, type, typeLogo, pic);
                    }
                }

                @Override
                public void onProgress(int i, int i1, int i2, int i3) {
                }

                @Override
                public void onError(int i, String s) {
                    Log.e(TAG, "error: " + s);
                    view.showError("发布失败！");
                }
            });
        }
    }

    private void post(Long Kid, String phone, String content, String type, Integer typeLogo, String... pic) {
        TimeLineModel.postTimeLine(Kid, phone, null, content, new Date().getTime(), type, typeLogo, pic[0], pic[1], pic[2])
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Log.i(TAG, "resultCode = " + result.getResultCode() + " , desc = " + result.getResultMessage());
                    view.dismiss();
                }, throwable -> {
                    Log.e(TAG, "error: " + throwable);
                    view.showError("发布失败！");
                });
    }
}
