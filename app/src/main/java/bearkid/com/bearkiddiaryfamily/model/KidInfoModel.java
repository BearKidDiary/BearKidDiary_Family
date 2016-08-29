package bearkid.com.bearkiddiaryfamily.model;

import android.support.annotation.Nullable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bearkid.com.bearkiddiaryfamily.model.bean.Height;
import bearkid.com.bearkiddiaryfamily.model.bean.Kid;
import bearkid.com.bearkiddiaryfamily.model.bean.Result;
import bearkid.com.bearkiddiaryfamily.model.bean.Vision;
import bearkid.com.bearkiddiaryfamily.model.bean.Weight;
import bearkid.com.bearkiddiaryfamily.utils.Urls;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.schedulers.Schedulers;

import static bearkid.com.bearkiddiaryfamily.utils.Urls.*;

/**
 * Created by admin on 2016/7/20.
 * 孩子信息相关功能
 */
public class KidInfoModel {
    private KidInfoModel() {
    }

    public interface AddKidService {
        @FormUrlEncoded
        @POST(Urls.URL_ADDKID)
        Observable<Result<Kid>> addKid(@Field("Uphone") String Uphone, @FieldMap Map<String, Object> map);
    }

    public static Observable<Result<Kid>> addKid(String Uphone, String[] parameter, Object[] value) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        AddKidService addKidService = retrofit.create(AddKidService.class);
        Map<String, Object> kidInfoMap = new HashMap<>();
        for (int i = 0; i < parameter.length; i++) {
            kidInfoMap.put(parameter[i], value[i]);
        }

        return addKidService.addKid(Uphone, kidInfoMap)
                .subscribeOn(Schedulers.io());
    }

    public interface SearchKidService {
        @FormUrlEncoded
        @POST(URL_KID)
        Observable<Result<List<Kid>>> searchKid(@Field("Uphone") String Uphone);

        @FormUrlEncoded
        @POST(URL_KID)
        Observable<Result<List<Kid>>> getKidInfo(@Field("Kid") Long Kid, @Field("Uphone") String Uphone, @Field("Fid") Long Fid, @Field("Cid") Long Cid, @Field("Range") String Range);

        @FormUrlEncoded
        @POST(URL_BODY)
        Observable<Result<List>> getKidBodyData(@Field("Kid") Long Kid,
                                                @Field("Order") String Order,
                                                @Field("Range") String Range);
    }

    public static Observable<Result<List<Kid>>> searchKid(String Uphone) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        SearchKidService searchKidService = retrofit.create(SearchKidService.class);
//        Map<String, String> kidInfoMap = new HashMap<>();
//        for (int i = 0; i < parameter.length; i++) {
////            try {
////                kidInfoMap.put(parameter[i], URLEncoder.encode(value[i],"UTF-8"));
////            } catch (UnsupportedEncodingException e) {
////                e.printStackTrace();
////            }
//
//            kidInfoMap.put(parameter[i], value[i]);
//        }

        return searchKidService.searchKid(Uphone)
                .subscribeOn(Schedulers.io());
    }

    /**
     * 获取孩子的个人信息
     *
     * @param Kid    是 	long 	孩子的编号
     * @param Uphone 是 	string 	用户手机号码
     * @param Fid    是 	long 	孩子所在家庭的编号
     * @param Cid    是 	long 	孩子所在课程的编号
     * @param Range  否 	string 	查找范围{"Creator""ALL"}
     */
    public static Observable<List<Kid>> getKidInfo(Long Kid, String Uphone, Long Fid, Long Cid, String Range) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(SearchKidService.class)
                .getKidInfo(Kid, Uphone, Fid, Cid, Range)
                .subscribeOn(Schedulers.io())
                .map(result -> result.getData());
    }

    /**
     *
     * @param Kid      是  Long    孩子的编号
     * @param Order    否  String  排序方式 {"desc"，"asc"}
     * @param Range    否  String  获取类型 {"Height"，"Weight"，"Vision"}
     * @return 可能为List<Height>或List<Weight>或List<Vision>
     */
    public static Observable<Result<List>> getKidBodyData(Long Kid, @Nullable String Order, @Nullable String Range) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(SearchKidService.class)
                .getKidBodyData(Kid, Order, Range)
                .subscribeOn(Schedulers.io());
    }

    public interface AddBodyDataService {
        @FormUrlEncoded
        @POST(Urls.URL_BODY_ADD)
        Observable<Result> addBodyData(@Field("Kid") Long kidId, @FieldMap Map<String, Object> map);
    }

    public static Observable<Result> addBodyData(Long Kid, @Nullable Height height, @Nullable Weight weight, @Nullable Vision vision) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Map<String, Object> bodyDataMap = new HashMap<>();
        if (height != null) {
//            bodyDataMap.put(Height.HEIGHT)
        }
        return retrofit.create(AddBodyDataService.class)
                .addBodyData(Kid, bodyDataMap)
                .subscribeOn(Schedulers.io());
    }

}
