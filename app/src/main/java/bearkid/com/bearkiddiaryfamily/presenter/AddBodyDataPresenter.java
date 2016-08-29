package bearkid.com.bearkiddiaryfamily.presenter;

import bearkid.com.bearkiddiaryfamily.ui.activity.AddBodyDataActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IAddBodyDataView;

/**
 * Created by YarenChoi on 2016/8/29.
 * 添加孩子身体数据适配器
 */
public class AddBodyDataPresenter {

    private IAddBodyDataView view;
    private Long kidId;

    public AddBodyDataPresenter(AddBodyDataActivity view, Long kidId) {
        this.view = view;
        this.kidId = kidId;
    }

    public void  addBodyData() {
        view.showProgress();


        view.hideProgress();
    }


}
