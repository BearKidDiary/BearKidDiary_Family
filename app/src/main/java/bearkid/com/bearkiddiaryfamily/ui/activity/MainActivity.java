package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bearkid.com.bearkiddiaryfamily.LoginActivity;
import bearkid.com.bearkiddiaryfamily.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //啊啊jjj
        LoginActivity.startActivity(this);
    }
}
