package bearkid.com.bearkiddiaryfamily.model;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.bean.Result;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
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
 * Created by admin on 2016/8/26.
 */
public class FamilyModel {
    private FamilyModel() {
    }

    /**
     * 获取家庭成员的信息
     * Uphone 	是 	string 	家庭创建者的手机号码
     * Fid 	是 	long 	家庭的编号
     * Range 	否 	string 	成员的范围{"Create""Attend""ALL"}
     */
    public static Observable<List<User>> getFamilyMembers(String Uphone, Long Fid, String Range) {
        return getService().getFamilyMembers(Uphone, Fid, Range)
                .subscribeOn(Schedulers.io())
                .map(result -> result.getData());
    }

    /**
     * 删除家庭成员
     * MemberUphone 	是 	string 	成员手机号码
     * CreatorUphone 	是 	string 	创建者手机号码
     * Fid 	是 	long 	家庭的编号
     */
    public static Observable<Result<Object>> removeFamilyMembers(String MemberUphone, String CreatorUphone, Long Fid) {
        return getService().removeFamilyMembers(MemberUphone, CreatorUphone, Fid)
                .subscribeOn(Schedulers.io());
    }

    public interface FamilyService {
        @FormUrlEncoded
        @POST(URL_FAMILY_MEMBERS)
        Observable<Result<List<User>>> getFamilyMembers(@Field("Uphone") String Uphone, @Field("Fid") Long Fid, @Field("Range") String Range);

        @FormUrlEncoded
        @POST(URL_REMOVE_FAMILY_MEMBERS)
        Observable<Result<Object>> removeFamilyMembers(@Field("MemberUphone") String MemberUphone,
                                                       @Field("CreatorUphone") String CreatorUphone, @Field("Fid") Long Fid);
    }

    private static FamilyService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(FamilyService.class);
    }
}
