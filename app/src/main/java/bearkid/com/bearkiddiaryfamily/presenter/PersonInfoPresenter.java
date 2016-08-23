package bearkid.com.bearkiddiaryfamily.presenter;

import bearkid.com.bearkiddiaryfamily.ui.activity.PersonalInfoActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IPersonalInfoView;
import bearkid.com.bearkiddiaryfamily.utils.LocalDB;

/**
 * Created by admin on 2016/7/18.
 * 个人信息界面的控制器
 */
public class PersonInfoPresenter {

    private IPersonalInfoView view;
    private LocalDB db;

    public PersonInfoPresenter(PersonalInfoActivity view) {
        this.view = view;
        this.db = new LocalDB(view.getContext());
    }

    public void init() {
        view.setName(db.getUserName());
        view.setPhone(db.getPhoneNum());
        view.setAddress(db.getUserAddress());
        view.setEmail(db.getUserEmail());
    }
}
