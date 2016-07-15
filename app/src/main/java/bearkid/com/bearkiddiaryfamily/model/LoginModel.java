package bearkid.com.bearkiddiaryfamily.model;

import android.content.Context;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.utils.LocalDB;
import bearkid.com.bearkiddiaryfamily.model.bean.FamilyUser;
import cn.bmob.v3.BmobQuery;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 登陆功能
 */
public class LoginModel {
    private LoginModel() {
    }

    /**
     * @param phoneNum 用户的手机号码
     * @param psw      用户的密码
     */
    public static Observable<Boolean> login(String phoneNum, final String psw) {
        BmobQuery<FamilyUser> query = new BmobQuery<>();
        query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK); // 先从缓存获取数据，如果没有，再从网络获取
        //query.setMaxCacheAge(TimeUnit.DAYS.toMillis(10));
        return query.addWhereEqualTo(FamilyUser.PHONE, phoneNum)
                .findObjectsObservable(FamilyUser.class)
                .subscribeOn(Schedulers.io())
                .map(familyUsers -> {
                    if (familyUsers.size() > 0) {
                        return psw.equals(familyUsers.get(0).getFUpsw());
                    }
                    return false;
                });
    }

    /**
     * 获取当前登录的用户信息
     * 可用于判断当前手机是否已经登陆过了
     *
     * @return FamilyUser的Observable对象 FamilyUser可能为null
     */
    public static Observable<FamilyUser> getCurrentUser(Context context) {
        String phoneNum = new LocalDB(context).getPhoneNum();
        BmobQuery<FamilyUser> query = new BmobQuery<>();
        query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ONLY); // 只从缓存获取
        query.addWhereEqualTo(FamilyUser.PHONE, phoneNum);
        return query.findObjectsObservable(FamilyUser.class)
                .subscribeOn(Schedulers.io())
                .map(familyUsers -> {
                    if (familyUsers.size() > 0)
                        return familyUsers.get(0);
                    return null;
                });
    }
}
