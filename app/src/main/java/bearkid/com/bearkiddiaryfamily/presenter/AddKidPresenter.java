package bearkid.com.bearkiddiaryfamily.presenter;

import bearkid.com.bearkiddiaryfamily.model.KidInfoModel;
import bearkid.com.bearkiddiaryfamily.model.bean.Kid;
import bearkid.com.bearkiddiaryfamily.ui.activity.AddKidActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IAddKidView;
import bearkid.com.bearkiddiaryfamily.utils.LocalDB;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by YarenChoi on 2016/8/23.
 * 添加孩子
 */
public class AddKidPresenter {
    private IAddKidView view;
    private String Uphone;

    public AddKidPresenter(AddKidActivity view) {
        this.view = view;
        Uphone = new LocalDB(view).getPhoneNum();
    }

    public void addKid() {
        view.showProgressDialog();
        addKid(new String[]{Kid.NAME, Kid.SEX}, new String[]{view.getKidName(), view.getKidGender()});
    }

    private void addKid(String[] parameter, String[] value){
        KidInfoModel.addKid(Uphone, parameter, value)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userResult -> {
                    view.hideProgressDialog();
                    if (userResult.getResultCode() == 0){
                        view.showResult("添加成功！");
                        view.exit();
                    }else {
                        view.showResult("添加失败！");
                    }
                }, Throwable -> {
                    view.hideProgressDialog();
                    view.showResult("添加失败！异常");
                });
    }
}
