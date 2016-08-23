package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

import android.graphics.Bitmap;

/**
 * Created by admin on 2016/7/11.
 *
 */
public interface IPersonalInfoView {
    /**
     * 获取头像
     * @return 头像bitmap
     */
    Bitmap getAvatar();

    /**
     * 获取名字
     */
    void setName(String name);

    /**
     * 获取性别
     */
    void setGender(String gender);

    /**
     * 获取手机号码
     */
    void setPhone(String phone);

    /**
     * 获取地址
     */
    void setAddress(String address);

    /**
     * 获取邮箱
     */
    void setEmail(String email);
}
