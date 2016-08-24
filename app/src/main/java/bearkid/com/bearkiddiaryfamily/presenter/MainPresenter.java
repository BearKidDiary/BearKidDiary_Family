package bearkid.com.bearkiddiaryfamily.presenter;

import bearkid.com.bearkiddiaryfamily.model.LoginModel;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
import bearkid.com.bearkiddiaryfamily.ui.activity.LoginActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.MainActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IMainView;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by admin on 2016/7/4.
 */
public class MainPresenter {
    private IMainView view;

    public MainPresenter(MainActivity view) {
        this.view = view;
    }

    /**
     * 初始化MainActivity
     * 1、判断用户是否已经登陆，未登陆则跳转到登陆界面
     * 2、用户若已经登陆，跳转到导航界面（未实现）
     */
    public void init() {
        User user = LoginModel.getCurrentUser(view.getContext());
        if (user == null) {
            LoginActivity.startActivity(view.getContext());
        } else {
            //TODO:已登录
        }
    }
}
