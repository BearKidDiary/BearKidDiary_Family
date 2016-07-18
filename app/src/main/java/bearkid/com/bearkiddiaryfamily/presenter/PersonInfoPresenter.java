package bearkid.com.bearkiddiaryfamily.presenter;

import bearkid.com.bearkiddiaryfamily.ui.activity.PersonInfoActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IPersonInfoView;

/**
 * Created by admin on 2016/7/18.
 */
public class PersonInfoPresenter {

    private IPersonInfoView view;

    public PersonInfoPresenter(PersonInfoActivity view) {
        this.view = view;
    }
    

}
