package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

/**
 * Created by admin on 2016/7/6.
 */
public interface ILoginView extends IBaseView {
    void loginUnClickable();

    void loginClickable();

    void showError(String str);

    void hideError();

    String getPhoneNum();

    String getPassword();

    void setPhoneNum(String phoneNum);

    void setPassword(String psw);
}
