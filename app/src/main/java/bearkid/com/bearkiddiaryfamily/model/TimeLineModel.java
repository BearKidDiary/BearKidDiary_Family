package bearkid.com.bearkiddiaryfamily.model;

import android.support.annotation.Nullable;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.bean.Result;
import bearkid.com.bearkiddiaryfamily.model.bean.TimeLine;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.schedulers.Schedulers;

import static bearkid.com.bearkiddiaryfamily.utils.Urls.*;

/**
 * 时间轴相关的功能
 *
 * @author zy
 */
public class TimeLineModel {

    /**
     * 获取孩子的时间轴列表
     *
     * @param Kid      是 	long 	孩子的编号
     * @param Uid      否 	long 	发布者的编号
     * @param Uphone   否 	string 	发布者的手机号码
     * @param Order    否 	string 	获取数据根据发布时间排序 {"desc""asc"}
     * @param PageSize 否 	int 	分页的大小
     * @param PageNum  否 	int 	分页的页码
     */
    public static Observable<List<TimeLine>> getTimeLineList(Long Kid, Long Uid, String Uphone, String Order, int PageSize, int PageNum) {

        if (PageNum <= 0 || PageSize <= 0)
            throw new IllegalArgumentException("pageSize or pageNum should bigger than 0");

        return getService().getTimeLine(Kid, Uid, Uphone, Order, PageSize, PageNum)
                .subscribeOn(Schedulers.io())
                .map(result -> result.getData());
    }

    /**
     * 发布一条时间轴事件
     *
     * @param Kid      是 	long 	孩子的编号
     * @param Uphone   是 	string 	发布者的手机号码
     * @param Uid      是 	long 	发布者的编号
     * @param content  是 	string 	时间轴事件的内容
     * @param time     是 	long 	时间轴事件的发布时间
     * @param type     是 	string 	时间轴事件的类型
     * @param typeLogo 是 	int 	时间轴事件的logo类型
     * @param image1   否 	string 	时间轴事件的图片URL
     * @param image2   否 	string 	时间轴事件的图片URL
     * @param image3   否 	string 	时间轴事件的图片URL
     */
    public static Observable<Result<Object>> postTimeLine(Long Kid, String Uphone, Long Uid,
                                                          String content, Long time,
                                                          String type, Integer typeLogo,
                                                          @Nullable String image1, @Nullable String image2,
                                                          @Nullable String image3) {
        return getService().postTimeLine(Kid, Uphone, Uid, content, time, type, typeLogo, image1, image2, image3);
    }

    public interface TimeLineService {
        @FormUrlEncoded
        @POST(URL_TIMELINE)
        Observable<Result<List<TimeLine>>> getTimeLine(@Field("Kid") Long Kid, @Field("Uid") Long Uid,
                                                       @Field("Uphone") String Uphone, @Field("Order") String Order,
                                                       @Field("PageSize") int PageSize, @Field("PageNum") int PageNum);

        @FormUrlEncoded
        @POST(URL_ADD_TIMELINE)
        Observable<Result<Object>> postTimeLine(@Field("Kid") Long Kid, @Field("Uphone") String Uphone, @Field("Uid") Long Uid,
                                                @Field("Treleasecontent") String content, @Field("Treleasetime") Long time,
                                                @Field("Ttype") String type, @Field("Ttypelogo") Integer typeLogo,
                                                @Field("Timage1") String image1, @Field("Timage2") String image2,
                                                @Field("Timage3") String image3);
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
