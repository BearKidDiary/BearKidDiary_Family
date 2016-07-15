package bearkid.com.bearkiddiaryfamily.model;

import bearkid.com.bearkiddiaryfamily.model.bean.FamilyUser;
import rx.Observable;
import rx.functions.Func1;

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
    public static Observable<String> register(final FamilyUser user, String code) {
        return SMSCodeModel.vertifySMSCode(user.getFUphone(), code)/*验证码验证*/
                .flatMap(aVoid -> {/*如果验证码正确 上传用户信息*/
                    return user.saveObservable();
                });
    }
}
