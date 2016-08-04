package bearkid.com.bearkiddiaryfamily.presenter;

import android.util.AndroidException;
import android.util.Log;
import android.widget.Toast;

import bearkid.com.bearkiddiaryfamily.model.UserModel;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
import bearkid.com.bearkiddiaryfamily.ui.activity.PersonInfoActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IPersonInfoView;
import bearkid.com.bearkiddiaryfamily.utils.LocalDB;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2016/7/18.
 * 个人信息界面的控制器
 */
public class PersonInfoPresenter {
    public static final int UPDATE_NAME = 0;
    public static final int UPDATE_ADDRESS = 1;
    public static final int UPDATE_EMAIL = 2;

    private IPersonInfoView view;
    private LocalDB db;

    private String Uphone;

    public PersonInfoPresenter(PersonInfoActivity view) {
        this.view = view;
        this.db = new LocalDB(view.getContext());
    }

    public void init() {
        view.setName(db.getUserName());
        view.setPhone(db.getPhoneNum());
        view.setAddress(db.getUserAddress());
        view.setEmail(db.getUserEmail());

        Uphone = db.getPhoneNum();
    }

    public void update(int type) {
        User user;
        switch (type) {
            case UPDATE_NAME:
                String Uname = view.getEditName();
                UserModel.updateUserInfomation(Uphone, new String[]{"Uname"},new String[]{Uname})
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(userResult -> {
                            if (userResult.getResultCode() == 0){
                                Log.d("修改", "修改成功！");
                            }else {
                                Log.d("修改", "修改失败！");
                            }
                        }, Throwable -> {
                            Log.d("修改", "异常！");
                        });
//                UserModel.updateUserInfomation(view.getViewContext(), user, new UpdateListener() {
//                    @Override
//                    public void done(BmobException e) {
//                        if (e == null){
//                            db.putUserName(name);
//                            init();
//                        } else {
//                            Toast.makeText(view.getViewContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
                break;
            case UPDATE_ADDRESS:
                String address = view.getEditAddress();
                UserModel.updateUserInfomation(Uphone, new String[]{User.AREA}, new String[]{address})
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(userResult -> {
                            if (userResult.getResultCode() == 0){
                                Log.d("修改", "修改成功！");
                            }else {
                                Log.d("修改", "修改失败！");
                            }
                        }, Throwable -> {
                            Log.d("修改", "异常！");
                        });
//                user.setUarea(address);
//                UserModel.updateUserInfomation(view.getViewContext(), user, new UpdateListener() {
//                    @Override
//                    public void done(BmobException e) {
//                        if (e == null){
//                            db.putUserAddress(address);
//                            init();
//                        } else {
//                            Toast.makeText(view.getViewContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
                break;
            case UPDATE_EMAIL:
                String email = view.getEditEmail();
                UserModel.updateUserInfomation(Uphone,new String[]{User.EMAIL}, new String[]{email})
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(userResult -> {
                            if (userResult.getResultCode() == 0){
                                Log.d("修改", "修改成功！");
                            }else {
                                Log.d("修改", "修改失败！");
                            }
                        }, Throwable -> {
                            Log.d("修改", "异常！");
                        });
//                user.setUemail(email);
//                UserModel.updateUserInfomation(view.getViewContext(), user, new UpdateListener() {
//                    @Override
//                    public void done(BmobException e) {
//                        if (e == null){
//                            db.putUserEmail(email);
//                            init();
//                        } else {
//                            Toast.makeText(view.getViewContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
                break;
            default:
                break;
        }
    }
    

}
