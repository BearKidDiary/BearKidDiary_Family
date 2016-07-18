package bearkid.com.bearkiddiaryfamily.presenter;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import bearkid.com.bearkiddiaryfamily.model.RegisterModel;
import bearkid.com.bearkiddiaryfamily.model.SMSCodeModel;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
import bearkid.com.bearkiddiaryfamily.ui.activity.RegisterActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IRegisterView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by admin on 2016/7/6.
 */
public class RegisterPresenter {

    private IRegisterView view;
    int unClickableTime = 60;

    public RegisterPresenter(RegisterActivity view) {
        this.view = view;
    }

    public void register() {
        view.registerUnClickable();
        view.hideError();

        User user = new User();
        user.setUphone(view.getPhoneNum());
        user.setUpsw(view.getPassword());

        RegisterModel.register(user, view.getSMSCode())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {/* 成功注册 s是ObjectId */
                        view.registerClickable();
                        Log.i("zy", "RegisterPresenter register success" + s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {/*验证码不正确 或者 注册用户信息失败 */
                        Log.e("zy", "RegisterPresenter register error" + throwable.toString());
                        view.registerClickable();
                        view.showError("注册失败，请检查网络或验证码");
                    }
                });
    }

    public void requestSMSCode() {
        view.smscodeUnClickable();
        view.hideError();

        final TimerTask task = countUnClickableTime();

        SMSCodeModel.requestSMSCode(view.getPhoneNum())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer smsId) {/* 发送验证码成功 smsId是验证码编号（用于跟踪详情）而不是验证码 */
                        Log.i("zy", "RegisterPresenter request smsCode: " + smsId);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.showError("获取验证码失败");
                        task.cancel();
                        view.smscodeClickable();
                        Log.e("zy", "RegisterPresenter request smsCode Error:" + throwable);
                    }
                });
    }

    /**
     * 点击获取验证码按钮后开始计时 60秒内不能重新获取
     */
    TimerTask countUnClickableTime() {
        unClickableTime = 60;
        TimerTask task;
        new Timer().scheduleAtFixedRate(task = new TimerTask() {
            @Override
            public void run() {
                view.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        if (unClickableTime > 0)
                            view.smscodeUnClickableTime(unClickableTime--);
                        else
                            view.smscodeClickable();
                    }
                });
            }
        }, 0, 1000);
        return task;
    }
}
