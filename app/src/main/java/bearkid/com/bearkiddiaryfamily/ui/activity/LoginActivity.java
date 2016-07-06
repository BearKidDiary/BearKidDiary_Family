package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.gc.materialdesign.views.ButtonFlat;

import bearkid.com.bearkiddiaryfamily.R;

public class LoginActivity extends BaseActivity {

    private ButtonFlat btn_login,btn_register;
    private EditText et_phone,et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private final void initView(){
        btn_login = (ButtonFlat) findViewById(R.id.btn_login_ok);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_register = (ButtonFlat) findViewById(R.id.btn_login_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        et_phone = (EditText) findViewById(R.id.et_login_phoneNum);
        et_password = (EditText) findViewById(R.id.et_login_password);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
