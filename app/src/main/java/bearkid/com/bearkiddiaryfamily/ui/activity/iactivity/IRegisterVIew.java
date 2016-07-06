package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

/**
 * Created by admin on 2016/7/6.
 */
public interface IRegisterView extends IBaseView {
    void showError(String e);

    void hideError();

    String getPhoneNum();

    String getSMSCode();

    String getPassword();

    void registerUnClickable();

    void registerClickable();

    void smscodeUnClickable();

    void smscodeUnClickableTime(int seconds);

    void smscodeClickable();

    void postRunnable(Runnable runnable);
}
