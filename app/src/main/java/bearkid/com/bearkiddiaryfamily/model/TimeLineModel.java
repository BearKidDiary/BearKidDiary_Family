package bearkid.com.bearkiddiaryfamily.model;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.bean.Kid;
import bearkid.com.bearkiddiaryfamily.model.bean.Result;
import bearkid.com.bearkiddiaryfamily.model.bean.TimeLine;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static bearkid.com.bearkiddiaryfamily.utils.Urls.*;

/**
 * 时间轴相关的功能
 *
 * @author 张宇
 */
public class TimeLineModel {

    /**
     * 获取孩子的时间轴列表
     * Kid 	是 	long 	孩子的编号
     * Uid 	否 	long 	发布者的编号
     * Uphone 	否 	string 	发布者的手机号码
     * Order 	否 	string 	获取数据根据发布时间排序 {"desc""asc"}
     * PageSize 	否 	int 	分页的大小
     * PageNum 	否 	int 	分页的页码
     */
    public static Observable<List<TimeLine>> getTimeLineList(Long Kid, Long Uid, String Uphone, String Order, int pageSize, int pageNum) {

        if (pageNum <= 0 || pageSize <= 0)
            throw new IllegalArgumentException("pageSize or pageNum should bigger than 0");

        return getService().getTimeLine(Kid, Uid, Uphone, Order, pageSize, pageNum)
                .subscribeOn(Schedulers.io())
                .map(result -> result.getData());
    }

    /**
     * 发布一条时间轴事件
     */
    public static Observable<String> postTimeLine(TimeLine timeLine) {
        return Observable.empty();
    }

    public interface TimeLineService {
        @FormUrlEncoded
        @POST(URL_GET_TIMELINE)
        Observable<Result<List<TimeLine>>> getTimeLine(@Field("Kid") Long Kid, @Field("Uid") Long Uid,
                                                       @Field("Uphone") String Uphone, @Field("Order") String Order,
                                                       @Field("PageSize") int PageSize, @Field("PageNum") int PageNum);
    }

    private static TimeLineService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(TimeLineService.class);
    }
}
