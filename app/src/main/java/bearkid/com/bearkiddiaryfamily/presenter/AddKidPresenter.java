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
        addKid(new String[]{Kid.NAME, Kid.SEX, Kid.BIRTHDAY, Kid.ASK}, new Object[]{view.getKidName(), view.getKidGender(), view.getKidBirthday(), view.getKidAsk()});
    }

    private void addKid(String[] parameter, Object[] value){
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

    public void updateInfo() {
        view.showProgressDialog();
        KidInfoModel.updateInfo(view.getKidId(),
                view.getKidName(),
                view.getKidBirthday(),
                "",
                view.getKidGender(),
                view.getKidAsk())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    view.hideProgressDialog();
                    if (result.getResultCode() == 0) {
                        view.showResult("修改成功!");
                        view.exit();
                    } else {
                        view.showResult("修改失败!");
                    }
                }, throwable -> {
                    view.hideProgressDialog();
                    view.showResult("修改失败!异常");
                });
    }
}
