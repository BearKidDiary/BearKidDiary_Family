package bearkid.com.bearkiddiaryfamily.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bearkid.com.bearkiddiaryfamily.model.bean.Kid;
import bearkid.com.bearkiddiaryfamily.model.bean.Result;
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
        Observable<Result<Kid>> addKid(@Field("Uphone") String Uphone, @FieldMap Map<String, String> map);
    }

    public static Observable<Result<Kid>> addKid(String Uphone, String[] parameter, String[] value) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        AddKidService addKidService = retrofit.create(AddKidService.class);
        Map<String, String> kidInfoMap = new HashMap<>();
        for (int i = 0; i < parameter.length; i++) {
//            try {
//                kidInfoMap.put(parameter[i], URLEncoder.encode(value[i],"UTF-8"));
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
            kidInfoMap.put(parameter[i], value[i]);
        }

        return addKidService.addKid(Uphone, kidInfoMap)
                .subscribeOn(Schedulers.io());
    }

    public interface SearchKidService {
        @FormUrlEncoded
        @POST(URL_KID)
        Observable<Result<Kid>> searchKid(@Field("Uphone") String Uphone, @FieldMap Map<String, String> map);

        @FormUrlEncoded
        @POST(URL_KID)
        Observable<Result<List<Kid>>> getKidInfo(@Field("Kid") Long Kid, @Field("Uphone") String Uphone, @Field("Fid") Long Fid, @Field("Cid") Long Cid);
    }

    public static Observable<Result<Kid>> searchKid(String Uphone, String[] parameter, String[] value) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        SearchKidService searchKidService = retrofit.create(SearchKidService.class);
        Map<String, String> kidInfoMap = new HashMap<>();
        for (int i = 0; i < parameter.length; i++) {
//            try {
//                kidInfoMap.put(parameter[i], URLEncoder.encode(value[i],"UTF-8"));
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }

            kidInfoMap.put(parameter[i], value[i]);
        }

        return searchKidService.searchKid(Uphone, kidInfoMap)
                .subscribeOn(Schedulers.io());
    }

    /**
     * 获取孩子的个人信息
     *
     * @param Kid    孩子的编号
     * @param Uphone 孩子所在家庭的创建者手机号码，也就是监护人手机号码
     * @param Fid    孩子所在家庭的编号
     * @param Cid    孩子所在课程的编号
     */
    public static Observable<List<Kid>> getKidInfo(Long Kid, String Uphone, Long Fid, Long Cid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(SearchKidService.class)
                .getKidInfo(Kid, Uphone, Fid, Cid)
                .subscribeOn(Schedulers.io())
                .map(result -> result.getData());
    }
}
