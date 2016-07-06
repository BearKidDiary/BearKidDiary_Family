package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFlat;

import bearkid.com.bearkiddiaryfamily.R;
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
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login();
            }
        });
        btn_register = (ButtonFlat) findViewById(R.id.btn_login_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.startActivity(getContext());
            }
        });
        et_phone = (EditText) findViewById(R.id.et_login_phoneNum);
        et_password = (EditText) findViewById(R.id.et_login_password);
        tv_error = (TextView) findViewById(R.id.tv_login_error);
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
        return et_phone.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_password.getText().toString();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
