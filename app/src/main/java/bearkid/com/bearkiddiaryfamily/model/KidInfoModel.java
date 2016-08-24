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

/**
 * Created by admin on 2016/7/20.
 * 孩子信息相关功能
 */
public class KidInfoModel {
    private KidInfoModel() {
    }

    public interface AddKidService{
        @FormUrlEncoded
        @POST(Urls.URL_ADDKID)
        Observable<Result<Kid>> addKid(@Field("Uphone") String Uphone, @FieldMap Map<String,String> map);
    }

    public static Observable<Result<Kid>> addKid(String Uphone, String[] parameter, String[] value){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        AddKidService addKidService = retrofit.create(AddKidService.class);
        Map<String, String> kidInfoMap = new HashMap<>();
        for(int i = 0; i < parameter.length; i++){
//            try {
//                kidInfoMap.put(parameter[i], URLEncoder.encode(value[i],"UTF-8"));
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
            kidInfoMap.put(parameter[i], value[i]);
        }

        return addKidService.addKid(Uphone, kidInfoMap)
                .subscribeOn(Schedulers.newThread());
    }

    public interface SearchKidService{
        @FormUrlEncoded
        @POST(Urls.URL_KID)
        Observable<Result<List<Kid>>> searchKid(@Field("Uphone") String Uphone);
    }

    public static Observable<Result<List<Kid>>> searchKid(String Uphone) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        SearchKidService searchKidService = retrofit.create(SearchKidService.class);
//        Map<String, String> kidInfoMap = new HashMap<>();
//        for(int i = 0; i < parameter.length; i++){
////            try {
////                kidInfoMap.put(parameter[i], URLEncoder.encode(value[i],"UTF-8"));
////            } catch (UnsupportedEncodingException e) {
////                e.printStackTrace();
////            }
//
//            kidInfoMap.put(parameter[i], value[i]);
//        }

        return searchKidService.searchKid(Uphone)
                .subscribeOn(Schedulers.newThread());
    }

}
