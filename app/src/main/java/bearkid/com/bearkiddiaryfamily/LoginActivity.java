package bearkid.com.bearkiddiaryfamily;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bearkid.com.bearkiddiaryfamily.ui.activity.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
