package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

/**
 * Created by Hung_Xum on 2016/8/18.
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
