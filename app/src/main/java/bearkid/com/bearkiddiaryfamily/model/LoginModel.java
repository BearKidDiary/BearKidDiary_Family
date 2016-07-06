package bearkid.com.bearkiddiaryfamily.model;

import android.util.TimeUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import bearkid.com.bearkiddiaryfamily.model.bean.FamilyUser;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 登陆功能
 */
public class LoginModel {
    private LoginModel() {
    }

    public static Observable<Boolean> login(String phoneNum, final String psw) {
        BmobQuery<FamilyUser> query = new BmobQuery<>();
        query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK); // 先从缓存获取数据，如果没有，再从网络获取
        //query.setMaxCacheAge(TimeUnit.DAYS.toMillis(10));
        return query.addWhereEqualTo(FamilyUser.PHONE, phoneNum)
                .findObjectsObservable(FamilyUser.class)
                .subscribeOn(Schedulers.io())
                .map(new Func1<List<FamilyUser>, Boolean>() {
                    @Override
                    public Boolean call(List<FamilyUser> familyUsers) {
                        if (familyUsers.size() > 0) {
                            return psw.equals(familyUsers.get(0).getFUpsw());
                        }
                        return false;
                    }
                });
    }
}
