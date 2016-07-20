package bearkid.com.bearkiddiaryfamily.presenter;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import bearkid.com.bearkiddiaryfamily.model.FormatCheckModel;
import bearkid.com.bearkiddiaryfamily.model.RegisterModel;
import bearkid.com.bearkiddiaryfamily.model.SMSCodeModel;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
import bearkid.com.bearkiddiaryfamily.ui.activity.RegisterActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IRegisterView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 注册界面的控制器
 */
public class RegisterPresenter {

    private IRegisterView view;
    int unClickableTime = 60;

    public RegisterPresenter(RegisterActivity view) {
        this.view = view;
    }

    /**
     * 注册账号
     */
    public void register() {
        view.registerUnClickable();
        view.hideError();

        //1.检查手机号码
        final String phone = view.getPhoneNum();
        if (!FormatCheckModel.isPhoneNumber(phone)) {
            view.showError("请输入合法的手机号码");
            return;
        }

        //2.封装成FamilyUser账号信息
        User user = new User();
        user.setUphone(view.getPhoneNum());
        user.setUpsw(view.getPassword());

        //3.注册
        RegisterModel.register(user, view.getSMSCode())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {/* 成功注册 s是ObjectId */
                    view.registerClickable();
                    view.finish();
                    Log.i("zy", "RegisterPresenter register success" + s);
                }, throwable -> {/*验证码不正确 或者 注册用户信息失败 */
                    Log.e("zy", "RegisterPresenter register error: " + throwable.toString());

                    if (throwable.toString().startsWith("errorCode:401")) {//401主键重复
                        view.registerClickable();
                        view.showError("手机号码已注册");
                    } else {
                        view.registerClickable();
                        view.showError("注册失败，请检查网络或验证码");
                    }
                });
    }

    /**
     * 获取验证码
     */
    public void requestSMSCode() {
        view.smscodeUnClickable();
        view.hideError();

        //1.检查手机号码
        final String phone = view.getPhoneNum();
        if (!FormatCheckModel.isPhoneNumber(phone)) {
            view.showError("请输入合法的手机号码");
            return;
        }

        //2.一定时间内不能再次点击获取验证码按钮
        final TimerTask task = countUnClickableTime();

        //3.获取验证码
        SMSCodeModel.requestSMSCode(phone)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(smsId -> {/* 发送验证码成功 smsId是验证码编号（用于跟踪详情）而不是验证码 */
                    Log.i("zy", "RegisterPresenter request smsCode: " + smsId);
                }, throwable -> {
                    final String e = throwable.toString();
                    if (e.startsWith("errorCode:10010")) {
                        view.showError("手机号码发送短信受限");
                    } else {
                        view.showError("获取验证码失败");
                    }
                    task.cancel();
                    view.smscodeClickable();
                    Log.e("zy", "RegisterPresenter request smsCode Error:" + throwable);
                });
    }

    /**
     * 点击获取验证码按钮后开始计时 60秒内不能重新获取
     */
    private TimerTask countUnClickableTime() {
        unClickableTime = 60;
        TimerTask task;
        new Timer().scheduleAtFixedRate(task = new TimerTask() {
            @Override
            public void run() {
                view.postRunnable(() -> {
                    if (unClickableTime > 0)
                        view.smscodeUnClickableTime(unClickableTime--);
                    else
                        view.smscodeClickable();
                });
            }
        }, 0, 1000);
        return task;
    }
}
