package bearkid.com.bearkiddiaryfamily.presenter;

import android.util.Log;

import bearkid.com.bearkiddiaryfamily.model.LoginModel;
import bearkid.com.bearkiddiaryfamily.ui.activity.LoginActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.ILoginView;
import bearkid.com.bearkiddiaryfamily.utils.LocalDB;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * @author 张宇
 * 登陆界面的控制器
 */
public class LoginPresenter {
    private ILoginView view;
    private LocalDB db;

    public LoginPresenter(LoginActivity view) {
        this.view = view;
        this.db = new LocalDB(view.getContext());
    }

    /**
     * 把上一次登陆的手机号码显示在登陆界面上
     */
    public void init(){
        String phone = db.getPhoneNum();
        view.setPhoneNum(phone);
    }

    public void login() {
        view.hideError();
        view.loginUnClickable();

        final String phoneNum = view.getPhoneNum();
        final String psw = view.getPassword();

        LoginModel.login(phoneNum, psw)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success -> {/*成功查找到对应的用户并对比密码是否一致*/
                    if (success) {
                        db.putPhoneNum(phoneNum);
                        view.loginClickable();
                        view.finish();
                        Log.i("zy", "LoginPresenter login sucess");
                    } else {
                        view.showError("用户名或密码错误");
                        view.loginClickable();
                        Log.e("zy", "LoginPresenter login error");
                    }
                }, throwable -> {/*查找不成功*/
                    view.showError("用户名不存在");
                    view.loginClickable();
                    Log.e("zy", "LoginPresenter login error " + throwable.toString());
                });
    }
}
