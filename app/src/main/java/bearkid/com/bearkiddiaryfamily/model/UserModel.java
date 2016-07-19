package bearkid.com.bearkiddiaryfamily.model;

import android.content.Context;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.bean.User;
import bearkid.com.bearkiddiaryfamily.utils.LocalDB;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
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

    /**
     *
     * @param phoneNum 提供要搜索的用户电话号码
     * @return
     */
    public static Observable<User> searchUser(String phoneNum) {
        BmobQuery<User> query = new BmobQuery<>();
        query.setCachePolicy(BmobQuery.CachePolicy.CACHE_THEN_NETWORK); // 先从缓存获取，再从网络获取
        query.addWhereEqualTo(User.PHONE, phoneNum);
        return query.findObjectsObservable(User.class)
                .subscribeOn(Schedulers.io())
                .map(users -> {
                    if (users.size() > 0)
                        return users.get(0);
                    return null;
                });
    }

    public static void updateUserInfomation(Context context, User user, UpdateListener updateListener) {
        user.update("e827e9dafc", updateListener);//new LocalDB(context).getBmobId()
    }
}
