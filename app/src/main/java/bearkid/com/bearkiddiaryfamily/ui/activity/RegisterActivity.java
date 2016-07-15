package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.presenter.RegisterPresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IRegisterView;

public class RegisterActivity extends BaseActivity implements IRegisterView {

    private EditText et_phone, et_password, et_sms;
    private Button btn_register, btn_sms;
    private TextView tv_error;

    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initPresenter();
        initView();
    }

    private final void initPresenter() {
        presenter = new RegisterPresenter(this);
    }

    private final void initView() {
        et_phone = (EditText) findViewById(R.id.et_register_phone);
        et_sms = (EditText) findViewById(R.id.et_register_sms);
        et_password = (EditText) findViewById(R.id.et_register_password);
        btn_register = (Button) findViewById(R.id.btn_register_ok);
        btn_sms = (Button) findViewById(R.id.btn_register_sms);
        tv_error = (TextView) findViewById(R.id.tv_register_wrong);

        //点击注册
        btn_register.setOnClickListener(view -> presenter.register());
        //点击获取验证码
        btn_sms.setOnClickListener(view -> presenter.requestSMSCode());
    }

    @Override
    public void showError(String e) {
        tv_error.setText(e);
        tv_error.setVisibility(View.VISIBLE);
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
    public String getSMSCode() {
        return et_sms.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_password.getText().toString();
    }

    @Override
    public void registerUnClickable() {
        btn_register.setClickable(false);
        btn_register.setBackgroundColor(getResources().getColor(R.color.colorUnClickable));
        btn_register.setText("请稍候");
    }

    @Override
    public void registerClickable() {
        btn_register.setClickable(true);
        btn_register.setText("注册");
        btn_register.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void postRunnable(Runnable runnable){
        runOnUiThread(runnable);
    }

    @Override
    public void smscodeUnClickable() {
        btn_sms.setClickable(false);
        btn_sms.setBackgroundColor(getResources().getColor(R.color.colorUnClickable));
    }

    @Override
    public void smscodeUnClickableTime(int seconds) {
        btn_sms.setText("(" + seconds + ")");
    }

    @Override
    public void smscodeClickable() {
        btn_sms.setText("获取验证码");
        btn_sms.setClickable(true);
        btn_sms.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }
}
