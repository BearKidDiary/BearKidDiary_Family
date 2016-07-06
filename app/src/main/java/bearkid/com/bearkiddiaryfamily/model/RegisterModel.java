package bearkid.com.bearkiddiaryfamily.model;

import bearkid.com.bearkiddiaryfamily.model.bean.FamilyUser;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 注册新用户功能
 */
public class RegisterModel {
    public Observable<String> register(final FamilyUser user, String code) {
        return SMSCodeModel.vertifySMSCode(user.getFUphone(), code)/*验证码验证*/
                .flatMap(new Func1<Void, Observable<String>>() {
                    @Override
                    public Observable<String> call(Void aVoid) {/*上传用户信息*/
                        return user.saveObservable();
                    }
                });
    }
}
