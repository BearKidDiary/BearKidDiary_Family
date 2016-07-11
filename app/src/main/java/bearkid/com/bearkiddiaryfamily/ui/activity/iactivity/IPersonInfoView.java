package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

import android.graphics.Bitmap;

/**
 * Created by admin on 2016/7/11.
 */
public interface IPersonInfoView {
    Bitmap getAvatar();
    String getName();
    String getPhone();
    String getAddress();
    String getEmail();
}
