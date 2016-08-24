package bearkid.com.bearkiddiaryfamily.presenter;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.KidInfoModel;
import bearkid.com.bearkiddiaryfamily.model.TimeLineModel;
import bearkid.com.bearkiddiaryfamily.model.bean.Kid;
import bearkid.com.bearkiddiaryfamily.model.bean.TimeLine;
import bearkid.com.bearkiddiaryfamily.ui.fragment.TimeLineFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.ifragment.ITimeLineFragment;
import bearkid.com.bearkiddiaryfamily.utils.LocalDB;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by zy on 2016/7/20.
 */
public class TimeLinePresenter {
    private ITimeLineFragment view;
    private int pageSize = 10;
    private int pageNum = 1;
    private LocalDB db;
    private List<Kid> kids = new ArrayList<>();

    public TimeLinePresenter(TimeLineFragment view) {
        this.view = view;
        db = new LocalDB(view.getContext());
    }

    public void init() {
        final String phoneNum = db.getPhoneNum();
        if (phoneNum == null) return;

        KidInfoModel.getKidInfo(null, phoneNum, null, null)
                .observeOn(AndroidSchedulers.mainThread())
                .map(kids -> {
                    List<String> name = new ArrayList<>();
                    for (Kid kid : kids) {
                        name.add(kid.getKname());
                    }
                    view.setChidrenName(name);
                    this.kids = kids;
                    return kids.get(0).getKid();
                })
                .observeOn(Schedulers.io())
                .flatMap(Kid -> TimeLineModel.getTimeLineList(Kid, null, phoneNum, null, 10, 1))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(timeLines -> {
                    Log.i("zy", "get timelines success");
                    view.setTimeLines(timeLines);
                    view.notifyChanged();
                }, throwable -> {
                    view.showToast("获取孩子的成长历程失败");
                    Log.e("zy", "TimeLinePresenter getTimeLine error: " + Log.getStackTraceString(throwable));
                });
    }

    public void refresh() {
        pageNum = 1;
        String name = view.getCurrentChildName();
        for (Kid kid : kids) {
            if (kid.getKname().equals(name)) {
                view.setRefreshing(true);
                final Long Kid = kid.getKid();
                TimeLineModel.getTimeLineList(Kid, null, null, null, pageSize, pageNum)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(timeLines -> {
                            view.setTimeLines(timeLines);
                            view.notifyChanged();
                            view.setRefreshing(false);
                        }, throwable -> {
                            view.showToast("获取孩子的成长历程失败");
                            view.setRefreshing(false);
                        });
                break;
            }
        }
    }

    public void loadMore() {
        pageNum++;

        String name = view.getCurrentChildName();
        for (Kid kid : kids) {
            if (kid.getKname().equals(name)) {
                view.setRefreshing(true);
                final Long Kid = kid.getKid();
                TimeLineModel.getTimeLineList(Kid, null, null, null, pageSize, pageNum)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(timeLines -> {
                            if (timeLines.size() == 0) {
                                view.showToast("到底了");
                            }
                            view.updateTimeLines(timeLines);
                            view.notifyChanged();
                            view.setRefreshing(false);
                        }, throwable -> {
                            view.showToast("获取孩子的成长历程失败");
                            view.setRefreshing(false);
                        });
                break;
            }
        }
    }

    public void showBigImage(@Nullable Bitmap bitmap, String url) {

    }
}
