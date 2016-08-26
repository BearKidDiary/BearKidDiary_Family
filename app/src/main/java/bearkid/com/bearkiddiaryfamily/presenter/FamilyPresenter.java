package bearkid.com.bearkiddiaryfamily.presenter;

import android.text.AndroidCharacter;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.FamilyModel;
import bearkid.com.bearkiddiaryfamily.model.bean.Result;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
import bearkid.com.bearkiddiaryfamily.ui.activity.FamilyActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IFamilyView;
import bearkid.com.bearkiddiaryfamily.utils.LocalDB;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by admin on 2016/7/11.
 */
public class FamilyPresenter {
    private IFamilyView view;
    private LocalDB db;

    public FamilyPresenter(FamilyActivity view) {
        this.view = view;
        db = new LocalDB(view.getContext());
    }

    public void init() {
        final String phone = db.getPhoneNum();
        FamilyModel.getFamilyMembers(phone, null, "Attend")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    view.setMemberList(users);
                    view.notifiyChanged();
                }, throwable -> {
                    view.showToast("获取成员信息失败");
                });
    }

    public void deleteMember(User member) {
        final String CreatorUphone = db.getPhoneNum();
        final String MemberUphone = member.getUphone();
        FamilyModel.removeFamilyMembers(MemberUphone, CreatorUphone, null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(objectResult -> {
                    view.notifiyChanged();
                }, throwable -> {
                    view.showToast("删除失败");
                    view.addMember(member);
                    view.notifiyChanged();
                });
    }
}
