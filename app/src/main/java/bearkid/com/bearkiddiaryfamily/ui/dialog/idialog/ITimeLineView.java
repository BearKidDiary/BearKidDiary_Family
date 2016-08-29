package bearkid.com.bearkiddiaryfamily.ui.dialog.idialog;

import java.util.List;

/**
 * Created by admin on 2016/8/24.
 */
public interface ITimeLineView extends IBaseDialog {
    String getContent();

    String getType();

    Integer getTypeLogo();

    Long getKid();

    List<String> getPicPath();

    void showError(String e);
}
