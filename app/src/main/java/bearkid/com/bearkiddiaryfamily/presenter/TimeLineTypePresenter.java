package bearkid.com.bearkiddiaryfamily.presenter;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.Date;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.TimeLineModel;
import bearkid.com.bearkiddiaryfamily.model.bean.Kid;
import bearkid.com.bearkiddiaryfamily.model.bean.TimeLine;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
import bearkid.com.bearkiddiaryfamily.ui.fragment.TimeLineTypeFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.ifragment.ITimeLineTypeFragment;
import bearkid.com.bearkiddiaryfamily.utils.LocalDB;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 添加时间轴页面的控制器
 *
 * @author 张宇
 */
public class TimeLineTypePresenter {
    private final ITimeLineTypeFragment view;
    private Kid currentKid;

    public TimeLineTypePresenter(TimeLineTypeFragment view) {
        this.view = view;
    }

    public void init() {
        currentKid = new Kid();
        currentKid.setObjectId("KRlg888G");
    }

    public void uploadTimeLine(String content, String type, int typeLogo, @Nullable File pic1, @Nullable File pic2, @Nullable File pic3) {
        TimeLine timeLine = new TimeLine();
        /*1.设置发布的内容*/
        timeLine.setReleasecontent(content);
        /*2.设置发布时间*/
        timeLine.setReleasetime(new BmobDate(new Date()));
        /*3.设置发布的作者为自己*/
        User author = new User();
//        author.setObjectId(new LocalDB(view.getContext()).getBmobId());
        timeLine.setAuthor(author);
        /*4.设置孩子为当前孩子*/
        timeLine.setKid(currentKid);
        /*5.设置类型*/
        timeLine.setType(type);
        /*6.设置图标*/
        timeLine.setTypelogo(typeLogo);
        /*7.设置图片*/
        if (pic1 != null) timeLine.setImage1(new BmobFile(pic1));
        if (pic2 != null) timeLine.setImage2(new BmobFile(pic2));
        if (pic3 != null) timeLine.setImage3(new BmobFile(pic3));

        TimeLineModel.postTimeLine(timeLine)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }, new Action0() {
                    @Override
                    public void call() {

                    }
                });
    }
}
