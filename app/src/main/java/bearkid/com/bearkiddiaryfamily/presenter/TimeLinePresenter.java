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
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by zy on 2016/7/20.
 */
public class TimeLinePresenter {
    private ITimeLineFragment view;
    private int pageSize = 10;
    private int pageNum = 1;
    private boolean getKid = false;
    private LocalDB db;
    private List<Kid> kids = new ArrayList<>();

    public TimeLinePresenter(TimeLineFragment view) {
        this.view = view;
        db = new LocalDB(view.getContext());
    }

    /**
     * 初始化时间轴界面，
     * 获取用户当前的孩子列表，
     * 获取第一个孩子的时间轴信息。
     */
    public void init() {
        view.setRefreshing(true);
        final String phoneNum = db.getPhoneNum();
        if (phoneNum == null) return;

        KidInfoModel.getKidInfo(null, phoneNum, null, null, "ALL")
                .observeOn(AndroidSchedulers.mainThread())
                .map(kids -> {
                    List<String> name = new ArrayList<>();
                    if (kids.size() == 0) {
                        name.add("未有孩子");
                    } else {
                        for (Kid kid : kids) {
                            name.add(kid.getKname());
                        }
                    }
                    view.setChidrenName(name);
                    this.kids = kids;
                    return kids.get(0).getKid();
                })
                .observeOn(Schedulers.io())
                .flatMap(Kid -> TimeLineModel.getTimeLineList(Kid, null, phoneNum, null, pageSize, 1))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(timeLines -> {
                    Log.i("zy", "get timelines success");
                    getKid = true;
                    view.setRefreshing(false);
                    view.setTimeLines(timeLines);
                    view.notifyChanged();
                }, throwable -> {
                    view.showToast("获取孩子的成长历程失败");
                    view.setRefreshing(false);
                    Log.e("zy", "TimeLinePresenter getTimeLine error: " + Log.getStackTraceString(throwable));
                });
    }

    /**
     * 刷新
     */
    public void refresh() {
        if (!getKid) {
            init();
            return;
        }

        pageNum = 1;
        String name = view.getCurrentChildName();
        for (Kid kid : kids) {
            if (kid.getKname().equals(name)) {
                view.setRefreshing(true);
                final Long Kid = kid.getKid();
                TimeLineModel.getTimeLineList(Kid, null, null, null, pageSize, pageNum)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(timeLines -> {
                            if (timeLines.size() == 0) {
                                view.showToast("没有事件");
                            }
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

    /**
     * 加载更多
     */
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
