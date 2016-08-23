package bearkid.com.bearkiddiaryfamily.model;

import android.content.Context;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bearkid.com.bearkiddiaryfamily.model.bean.Result;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
import bearkid.com.bearkiddiaryfamily.utils.LocalDB;
import bearkid.com.bearkiddiaryfamily.utils.Urls;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2016/7/18.
 * 用户相关model
 */
public class UserModel {
    private UserModel() {
    }

//    /**
//     *
//     * @param phoneNum 提供要搜索的用户电话号码
//     * @return
//     */
//    public static Observable<User> searchUser(String phoneNum) {
//        BmobQuery<User> query = new BmobQuery<>();
//        query.setCachePolicy(BmobQuery.CachePolicy.CACHE_THEN_NETWORK); // 先从缓存获取，再从网络获取
//        query.addWhereEqualTo(User.PHONE, phoneNum);
//        return query.findObjectsObservable(User.class)
//                .subscribeOn(Schedulers.io())
//                .map(users -> {
//                    if (users.size() > 0)
//                        return users.get(0);
//                    return null;
//                });
//    }

//    public static void updateUserInfomation(Context context, User user, UpdateListener updateListener) {
//        user.update(new LocalDB(context).getBmobId(), updateListener);
//    }

    public interface updateInfoService{
        @FormUrlEncoded
        @POST(Urls.URL_UPDATEUERINFO)
        Observable<Result<User>> updateUserInfo(@Field("Uphone") String Uphone, @FieldMap Map<String,String> map);
    }

    public static Observable<Result<User>> updateUserInfomation(String Uphone, String[] parameter, String[] value){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        updateInfoService updateInfoService = retrofit.create(UserModel.updateInfoService.class);
        Map<String, String> userMap = new HashMap<>();
        for(int i = 0; i < parameter.length; i++){
//            try {
//                userMap.put(parameter[i], URLEncoder.encode(value[i],"UTF-8"));
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
            userMap.put(parameter[i], value[i]);
        }

        return updateInfoService.updateUserInfo(Uphone, userMap)
                .subscribeOn(Schedulers.newThread());
    }
}
