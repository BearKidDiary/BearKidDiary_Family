package bearkid.com.bearkiddiaryfamily.model;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.bean.Result;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
import bearkid.com.bearkiddiaryfamily.utils.LocalDB;
import bearkid.com.bearkiddiaryfamily.utils.Urls;
import cn.bmob.v3.BmobQuery;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 登陆功能
 */
public class LoginModel {

    private static User user;
    private LoginModel() {
    }

    /**
     * @param phoneNum 用户的手机号码
     * @param psw      用户的密码
     */
    public static Observable<Result<User>> login(Context context, String phoneNum, final String psw) {
//        BmobQuery<User> query = new BmobQuery<>();
//        query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK); // 先从缓存获取数据，如果没有，再从网络获取
//        query.setMaxCacheAge(TimeUnit.DAYS.toMillis(10));
//        return query.addWhereEqualTo(User.PHONE, phoneNum)
//                .findObjectsObservable(User.class)
//                .subscribeOn(Schedulers.io())
//                .map(users -> {
//                    if (users.size() > 0) {
//                        User user = users.get(0);
//                        if (psw.equals(user.getUpsw())) {
//                            //把ObjectId更新到本地
//                            new LocalDB(context).putBmobId(user.getObjectId());
//                            return true;
//                        }
//                    }
//                    return false;
//                });
        return Login(phoneNum, psw);
    }

    /**
     * 登录
     */
    public interface LoginService {
        @FormUrlEncoded
        @POST(Urls.URL_LOGIN)
//        Observable<Result<User>> Login(@Query("Uphone") String Uphone, @Query("Upsw") String Upsw);
        Observable<Result<User>> Login(@Field("Uphone") String Uphone, @Field("Upsw") String Upsw);
//        Observable<Result<User>> Login(@Body User user);
    }

    public static Observable<Result<User>> Login(String Uphone, String Upsw){;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        LoginService loginService = retrofit.create(LoginService.class);
        return loginService.Login(Uphone, Upsw)
                .subscribeOn(Schedulers.newThread());
    }


    /**
     * 获取当前登录的用户信息
     * 可用于判断当前手机是否已经登陆过了
     *
     * @return User的Observable对象 User可能为null
     */
    public static Observable<User> getCurrentUser(Context context) {
        String phoneNum = new LocalDB(context).getPhoneNum();
        BmobQuery<User> query = new BmobQuery<>();
        query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ONLY); // 只从缓存获取
        query.addWhereEqualTo(User.PHONE, phoneNum);
        return query.findObjectsObservable(User.class)
                .subscribeOn(Schedulers.io())
                .map(users -> {
                    if (users.size() > 0) {
                        return users.get(0);
                    }
                    return null;
                });
    }
}
