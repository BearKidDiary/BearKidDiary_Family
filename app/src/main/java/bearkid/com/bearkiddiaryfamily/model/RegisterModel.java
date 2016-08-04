package bearkid.com.bearkiddiaryfamily.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import bearkid.com.bearkiddiaryfamily.model.bean.User;
import bearkid.com.bearkiddiaryfamily.utils.Urls;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 注册新用户功能
 */
public class RegisterModel {
    private RegisterModel(){}

    /**
     * 注册新用户
     * @param user 新用户的信息
     * @param code 短信验证码
     */
    public static Observable<String> register(final User user, String code) {
//        return SMSCodeModel.vertifySMSCode(user.getUphone(), code)/*验证码验证*/
//                .flatMap(new Func1<Void, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(Void aVoid) {
//                        return registerToWeb(user);
//                    }
//                })
//        .subscribeOn(Schedulers.newThread());
        return Register(user.getUphone(), user.getUpsw());
    }


    /**
     * 注册
     */
    public interface RegisterService{
        @POST(Urls.URL_REGIST)
        Observable<String> Register(@Query("Uphone") String Uphone, @Query("Upsw") String Upsw);
    }

    //使用Retrofit2+RxJava
    public static Observable<String> Register(String Uphone, String Upsw){;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        RegisterService registerService = retrofit.create(RegisterService.class);
        return registerService.Register(Uphone,Upsw)
                .subscribeOn(Schedulers.newThread());
    }



    //android原始的网络请求 + RxJava
    public static Observable<String> registerToWeb(final User user) {
        String url = "http://192.168.191.1:8888/BearKidDiary_Service/regist.jsp";
        String content = "Uphone=" + user.getUphone() + "&Upsw=" + user.getUpsw();

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                String response = doPost(url, content);

                subscriber.onNext(response);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread());

        return observable;
    }


    public static String doPost(String url, String content){
        try {
            URL httpurl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpurl.openConnection();

            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);

            OutputStream out = connection.getOutputStream();
            out.write(content.getBytes());

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str;
            while ((str = reader.readLine()) != null){
                sb.append(str);
            }

            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
