package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFlat;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.global.BaseApplication;
import bearkid.com.bearkiddiaryfamily.presenter.LoginPresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.ILoginView;

public class LoginActivity extends BaseActivity implements ILoginView {

    private ButtonFlat btn_login, btn_register;
    private EditText et_phone, et_password;
    private TextView tv_error;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initPresenter();
        initView();
    }

    private final void initPresenter() {
        presenter = new LoginPresenter(this);
    }

    private final void initView() {
        btn_login = (ButtonFlat) findViewById(R.id.btn_login_ok);
        btn_register = (ButtonFlat) findViewById(R.id.btn_login_register);
        et_phone = (EditText) findViewById(R.id.et_login_phoneNum);
        et_password = (EditText) findViewById(R.id.et_login_password);
        tv_error = (TextView) findViewById(R.id.tv_login_error);

        //点击登陆按钮
        btn_login.setOnClickListener(view -> presenter.login());
        //点击注册按钮
        btn_register.setOnClickListener(view -> RegisterActivity.startActivity(getContext()));

        presenter.init();
    }

    /**
     * 监听返回键：在登陆界面点返回键直接退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ((BaseApplication) getApplication()).exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void loginUnClickable() {
        btn_login.setClickable(false);
        btn_login.setText("请稍候...");
        btn_login.setBackgroundColor(getResources().getColor(R.color.colorUnClickable));
    }

    @Override
    public void loginClickable() {
        btn_login.setClickable(true);
        btn_login.setText("登陆");
        btn_login.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void showError(String str) {
        tv_error.setVisibility(View.VISIBLE);
        tv_error.setText(str);
    }

    @Override
    public void hideError() {
        tv_error.setVisibility(View.GONE);
    }

    @Override
    public String getPhoneNum() {
        if (et_phone != null) return et_phone.getText().toString();
        return "";
    }

    @Override
    public void setPhoneNum(String phoneNum) {
        if (et_phone != null)
            et_phone.setText(phoneNum);
    }

    @Override
    public String getPassword() {
        if (et_password != null) return et_password.getText().toString();
        return "";
    }

    @Override
    public void setPassword(String psw) {
        if (et_password != null)
            et_password.setText(psw);
    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
