package bearkid.com.bearkiddiaryfamily.presenter;

import android.util.Log;

import bearkid.com.bearkiddiaryfamily.model.LoginModel;
import bearkid.com.bearkiddiaryfamily.ui.activity.LoginActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.ILoginView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by admin on 2016/7/6.
 */
public class LoginPresenter {
    private ILoginView view;

    public LoginPresenter(LoginActivity view) {
        this.view = view;
    }

    public void login() {
        view.hideError();
        view.loginUnClickable();

        String phoneNum = view.getPhoneNum();
        String psw = view.getPassword();

        LoginModel.login(phoneNum, psw)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean success) {/*成功查找到对应的用户并对比密码是否一致*/
                        if (success) {
                            view.loginClickable();
                            Log.i("zy", "LoginPresenter login sucess");
                        } else {
                            view.showError("用户名或密码错误");
                            view.loginClickable();
                            Log.e("zy", "LoginPresenter login error");
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {/*查找不成功*/
                        view.showError("用户名不存在");
                        view.loginClickable();
                        Log.e("zy", "LoginPresenter login error " + throwable.toString());
                    }
                });
    }
}
