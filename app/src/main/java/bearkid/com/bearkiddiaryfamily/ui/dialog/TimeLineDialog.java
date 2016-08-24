package bearkid.com.bearkiddiaryfamily.ui.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.gc.materialdesign.views.ButtonFlat;

import bearkid.com.bearkiddiaryfamily.model.bean.TimeLine;
import bearkid.com.bearkiddiaryfamily.presenter.TimeLinePostPresenter;
import bearkid.com.bearkiddiaryfamily.ui.dialog.idialog.ITimeLineView;
import bearkid.com.bearkiddiaryfamily.ui.view.IconButton;

/**
 * Created by admin on 2016/8/24.
 */
public abstract class TimeLineDialog extends DialogFragment implements ITimeLineView {

    private TimeLinePostPresenter presenter;
    private Long kid;
    private int typeLogo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kid = getArguments().getLong("Kid");
        typeLogo = getArguments().getInt("type");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        presenter = new TimeLinePostPresenter(this);

        getPositiveButton().setOnClickListener(v -> {
            presenter.uploadTimeLine();
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
        }
        return "自定义";
    }

    protected abstract ButtonFlat getPositiveButton();

    protected abstract IconButton getChoosePictureButton();
}
