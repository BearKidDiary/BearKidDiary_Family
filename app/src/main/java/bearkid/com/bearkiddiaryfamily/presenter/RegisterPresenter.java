package bearkid.com.bearkiddiaryfamily.presenter;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import bearkid.com.bearkiddiaryfamily.model.SMSCodeModel;
import bearkid.com.bearkiddiaryfamily.ui.activity.RegisterActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IRegisterView;
import rx.functions.Action1;

/**
 * Created by admin on 2016/7/6.
 */
public class RegisterPresenter {

    private IRegisterView view;
    private int smsCode;
    int unClickableTime = 60;

    public RegisterPresenter(RegisterActivity view) {
        this.view = view;
    }

    public void register() {

    }

    public void requestSMSCode() {
        view.smscodeUnClickable();

        final TimerTask task = countUnClickableTime();

        SMSCodeModel.requestSMSCode(view.getPhoneNum())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        smsCode = integer;
                        Log.i("zy", "RegisterPresenter request smsCode:" + smsCode);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showToast("获取验证码失败");
                        task.cancel();
                        view.smscodeClickable();
                        Log.e("zy", "RegisterPresenter request smsCode Error:" + throwable);
                    }
                });

    }

    TimerTask countUnClickableTime() {
        unClickableTime = 60;
        TimerTask task;
        new Timer().scheduleAtFixedRate(task = new TimerTask() {
            @Override
            public void run() {
                if (unClickableTime > 0)
                    view.smscodeUnClickableTime(unClickableTime--);
                else
                    view.smscodeClickable();
            }
        }, 0, 1000);
        return task;
    }
}
