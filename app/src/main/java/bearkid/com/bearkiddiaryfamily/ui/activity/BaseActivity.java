package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import bearkid.com.bearkiddiaryfamily.global.BaseApplication;
import bearkid.com.bearkiddiaryfamily.global.StatusBarCompat;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IBaseView;

/**
 * Created by admin on 2016/7/4.
 */
public class BaseActivity extends AppCompatActivity implements IBaseView {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        StatusBarCompat.compat(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        StatusBarCompat.compat(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        StatusBarCompat.compat(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseApplication) getApplication()).pushActivity(this);
    }

    @Override
    public void finish() {
        super.finish();
        ((BaseApplication) getApplication()).popActivity();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String str) {
        if (getContext() != null)
            Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    public Bundle getBundle() {
        if (getIntent() != null && getIntent().hasExtra(getPackageName()))
            return getIntent().getBundleExtra(getPackageName());
        else
            return null;
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
