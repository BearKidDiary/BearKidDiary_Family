package bearkid.com.bearkiddiaryfamily.model;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.bean.Kid;
import bearkid.com.bearkiddiaryfamily.model.bean.TimeLine;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 时间轴相关的功能
 *
 * @author 张宇
 */
public class TimeLineModel {

    /**
     * 获取孩子的时间轴列表
     */
    public static Observable<List<TimeLine>> getTimeLineList(Kid kid, int pageNum, int pageSize) {

        if (pageNum <= 0 || pageSize <= 0)
            throw new IllegalArgumentException("pageSize or pageNum should bigger than 0");

        BmobQuery<TimeLine> query = new BmobQuery<>();
        query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        query.addWhereEqualTo(TimeLine.KID, new BmobPointer(kid));
        query.include(TimeLine.AUTHOR + "." + User.NAME);
        query.order("-" + TimeLine.RELEASETIME);
        query.setLimit(pageSize);
        query.setSkip((pageNum - 1) * pageSize);
        return query.findObjectsObservable(TimeLine.class)
                .subscribeOn(Schedulers.io());
    }

    public static Observable<List<TimeLine>> getTimeLineList(Kid kid) {
        return getTimeLineList(kid, 1, 10);
    }

    public static Observable<List<TimeLine>> getTimeLineList(String kidObjectId, int pageNum, int pageSize) {
        Kid kid = new Kid();
        kid.setObjectId(kidObjectId);
        return getTimeLineList(kid, pageNum, pageSize);
    }

    /**
     * 发布一条时间轴事件
     */
    public static Observable<String> postTimeLine(TimeLine timeLine) {
        return timeLine.saveObservable()
                .subscribeOn(Schedulers.io());
    }
}
