package bearkid.com.bearkiddiaryfamily.presenter;

import bearkid.com.bearkiddiaryfamily.ui.activity.KidInfoActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IKidInfoView;

/**
 * Created by admin on 2016/7/20.
 */
public class KidInfoPresenter {

    private IKidInfoView view;
    public KidInfoPresenter(KidInfoActivity view) {
        this.view = view;
    }

    public void init() {
        view.showHeightAndWeight(181, 45, "2015-02-23");
        view.showVision(2.5, 5.2, "2015-02-03");
        view.showExhort(1, "不能吃辣");
    }
}
