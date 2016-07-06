package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import bearkid.com.bearkiddiaryfamily.global.BaseApplication;

/**
 * Created by admin on 2016/7/4.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseApplication)getApplication()).pushActivity(this);
    }

    @Override
    public void finish() {
        super.finish();
        ((BaseApplication)getApplication()).popActivity();
    }
}
