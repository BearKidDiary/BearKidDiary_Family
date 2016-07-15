package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

import android.content.Context;

/**
 * Created by admin on 2016/7/6.
 */
public interface IBaseView {
    Context getContext();
    void showToast(String str);
    void finish();
}
