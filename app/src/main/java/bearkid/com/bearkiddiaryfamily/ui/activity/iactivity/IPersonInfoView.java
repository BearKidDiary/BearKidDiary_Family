package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

import android.content.Context;
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
    void setName(String name);

    /**
     * 获取手机号码
     * @return
     */
    void setPhone(String phone);

    /**
     * 获取地址
     * @return
     */
    void setAddress(String address);

    /**
     * 获取邮箱
     * @return
     */
    void setEmail(String email);

    /**
     * 获取想要修改的名字内容
     * @return
     */
    String getEditName();

    /**
     * 获取想要修改的地址内容
     * @return
     */
    String getEditAddress();

    /**
     * 获取想要修改的邮箱内容
     * @return
     */
    String getEditEmail();

    /**
     * 获取上下文
     * @return
     */
    Context getViewContext();
}
