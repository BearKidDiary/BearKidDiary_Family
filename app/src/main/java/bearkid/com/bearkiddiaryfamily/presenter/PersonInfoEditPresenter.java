package bearkid.com.bearkiddiaryfamily.presenter;

import bearkid.com.bearkiddiaryfamily.model.FormatCheckModel;
import bearkid.com.bearkiddiaryfamily.model.UserModel;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
import bearkid.com.bearkiddiaryfamily.ui.activity.PersonalInfoEditActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IPersonalInfoEditView;
import bearkid.com.bearkiddiaryfamily.utils.LocalDB;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by YarenChoi on 2016/8/23.
 * 编辑个人信息界面适配器
 */
public class PersonInfoEditPresenter {

    private IPersonalInfoEditView view;
    private LocalDB db;
    private String Uphone;

    public PersonInfoEditPresenter(PersonalInfoEditActivity view) {
        this.view = view;
        this.db = new LocalDB(view.getContext());
        this.Uphone = db.getPhoneNum();
    }

    public void update(int type) {
        view.showProgressDialog();
        String editContent = view.getEditContent();
        switch (type) {
            case PersonalInfoEditActivity.TYPE_NAME:
                doUpdateUser(PersonalInfoEditActivity.TYPE_NAME, new String[]{User.NAME},new String[]{editContent});
                break;
            case PersonalInfoEditActivity.TYPE_ADDRESS:
                doUpdateUser(PersonalInfoEditActivity.TYPE_ADDRESS, new String[]{User.AREA}, new String[]{editContent});
                break;
            case PersonalInfoEditActivity.TYPE_EMAIL:
                if (FormatCheckModel.isEmail(editContent)) {
                    doUpdateUser(PersonalInfoEditActivity.TYPE_EMAIL, new String[]{User.EMAIL}, new String[]{editContent});
                } else{
                    view.showResult("您输入的邮箱地址不正确");
                    view.hideProgressDialog();
                }
                break;
            default:
                break;
        }
    }

    private void doUpdateUser(int type, String[] parameter, String[] value){
        UserModel.updateUserInfomation(Uphone, parameter, value)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userResult -> {
                    view.hideProgressDialog();
                    if (userResult.getResultCode() == 0){
                        save(type, value[0]);
                        view.showResult("修改成功！");
                        view.exit();
                    }else {
                        view.showResult("修改失败！");
                    }
                }, Throwable -> {
                    view.hideProgressDialog();
                    view.showResult("修改失败！异常");
                });
    }

    private void save(int type, String value){
        switch (type){
            case PersonalInfoEditActivity.TYPE_NAME:
                db.putUserName(value);
                break;
            case PersonalInfoEditActivity.TYPE_ADDRESS:
                db.putUserAddress(value);
                break;
            case PersonalInfoEditActivity.TYPE_EMAIL:
                db.putUserEmail(value);
                break;
        }
    }
}
