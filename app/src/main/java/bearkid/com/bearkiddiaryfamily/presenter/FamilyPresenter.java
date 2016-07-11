package bearkid.com.bearkiddiaryfamily.presenter;

import java.util.ArrayList;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.FadeModel;
import bearkid.com.bearkiddiaryfamily.model.bean.FamilyKidMsg;
import bearkid.com.bearkiddiaryfamily.ui.fragment.FamilyFragment;

/**
 * Created by admin on 2016/7/11.
 */
public class FamilyPresenter {
    private FamilyFragment view;

    public FamilyPresenter(FamilyFragment view) {
        this.view = view;
    }

    public void initDate() {
        List<FamilyKidMsg> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            FamilyKidMsg msg = FadeModel.getFamilyKidMsg();
            list.add(msg);
        }

        view.updateKidList(list);
    }
}
