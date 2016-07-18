package bearkid.com.bearkiddiaryfamily.model;

import cn.bmob.v3.BmobSMS;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * 获取短信验证码功能
 */
public class SMSCodeModel {

    /**
     * 短信模板的名字
     */
    public static final String TEMPLE = "验证码";

    private SMSCodeModel() {
    }

    /**
     * 获取验证码到指定手机号码
     */
    public static Observable<Integer> requestSMSCode(String number) {
        return BmobSMS.requestSMSCodeObservable(number, TEMPLE)
                .subscribeOn(Schedulers.io());
    }

    /**
     * 验证指定验证码
     */
    public static Observable<Void> vertifySMSCode(String phoneNum, String code) {
        return BmobSMS.verifySmsCodeObservable(phoneNum, code)
                .subscribeOn(Schedulers.io());
    }
}

