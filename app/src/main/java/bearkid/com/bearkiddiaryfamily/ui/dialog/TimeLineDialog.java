package bearkid.com.bearkiddiaryfamily.ui.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;

import java.util.ArrayList;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.bean.TimeLine;
import bearkid.com.bearkiddiaryfamily.presenter.TimeLinePostPresenter;
import bearkid.com.bearkiddiaryfamily.ui.dialog.idialog.ITimeLineView;
import bearkid.com.bearkiddiaryfamily.ui.view.IconButton;
import bearkid.com.bearkiddiaryfamily.utils.BitmapSelector;
import bearkid.com.bearkiddiaryfamily.utils.GalleryFinalHelper;

/**
 * Created by zy on 2016/8/24.
 */
public abstract class TimeLineDialog extends DialogFragment implements ITimeLineView {

    private TimeLinePostPresenter presenter;
    private Long kid;
    private int typeLogo;
    private List<String> picPath = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kid = getArguments().getLong("Kid");
        typeLogo = getArguments().getInt("type");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        presenter = new TimeLinePostPresenter(this);

        final ButtonFlat btn_ok = getPositiveButton();
        if (btn_ok != null)
            btn_ok.setOnClickListener(v -> presenter.uploadTimeLine());

        final IconButton btn_pic = getChoosePictureButton();
        if (btn_pic != null)
            btn_pic.setOnClickListener(v -> {
                BitmapSelector bitmapSelector = new GalleryFinalHelper(getContext());
                bitmapSelector.openGallery(3, new BitmapSelector.CallBack() {
                    @Override
                    public void finish(List<String> path, int picNum) {
                        picPath = path;
                    }

                    @Override
                    public void error(String msg) {
                        showError(msg);
                    }
                });
            });
    }

    public static TimeLineDialog show(FragmentManager manager, int type, Long Kid) {
        TimeLineDialog dialog;
        switch (type) {
            case TimeLine.Type.EAT:
                dialog = new TimeLineEatDialog();
                break;
            case TimeLine.Type.FIRSTTIME:
                dialog = new TimeLineFirstTimeDialog();
                break;
            case TimeLine.Type.SPORT:
                dialog = new TimeLineSportDialog();
                break;
            case TimeLine.Type.STUDY:
                dialog = new TimeLineStudyDialog();
                break;
            case TimeLine.Type.BODY:
            default:
                dialog = new TimeLineBodyDialog();
                break;
        }
        Bundle args = new Bundle();
        args.putLong("Kid", Kid);
        args.putInt("type", type);
        dialog.setArguments(args);
        dialog.show(manager, "TimeLineDialog");
        return dialog;
    }

    @Override
    public List<String> getPicPath() {
        return picPath;
    }

    @Override
    public Long getKid() {
        return kid;
    }

    @Override
    public Integer getTypeLogo() {
        return typeLogo;
    }

    @Override
    public String getType() {
        switch (typeLogo) {
            case TimeLine.Type.EAT:
                return "美食";
            case TimeLine.Type.FIRSTTIME:
                return "第一次";
            case TimeLine.Type.SPORT:
                return "运动";
            case TimeLine.Type.STUDY:
                return "学习";
            case TimeLine.Type.BODY:
                return "身体";
        }
        return "自定义";
    }

    @Override
    public void showError(String e) {
        Toast.makeText(getContext(), e, Toast.LENGTH_SHORT).show();
    }

    protected abstract ButtonFlat getPositiveButton();

    protected abstract void setPic1(String picPath);

    protected abstract void setPic2(String picPath);

    protected abstract void setPic3(String picPath);

    protected abstract IconButton getChoosePictureButton();
}
