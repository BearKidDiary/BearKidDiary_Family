package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

import android.graphics.Bitmap;

/**
 * Created by admin on 2016/7/11.
 */
public interface IPersonInfoView {
    /**
     * 获取头像
     * @return
     */
    Bitmap getAvatar();

    /**
     * 获取名字
     * @return
     */
    String getName();

    /**
     * 获取手机号码
     * @return
     */
    String getPhone();

    /**
     * 获取地址
     * @return
     */
    String getAddress();

    /**
     * 获取邮箱
     * @return
     */
    String getEmail();
}
