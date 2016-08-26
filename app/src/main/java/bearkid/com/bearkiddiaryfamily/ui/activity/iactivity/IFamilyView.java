package bearkid.com.bearkiddiaryfamily.ui.activity.iactivity;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.bean.User;

public interface IFamilyView extends IBaseView{
    void setMemberList(List<User> members);

    void addMemberList(List<User> members);

    void addMember(User member);

    void notifiyChanged();
}
