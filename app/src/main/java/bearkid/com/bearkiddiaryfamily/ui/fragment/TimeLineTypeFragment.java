package bearkid.com.bearkiddiaryfamily.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineBodyDialog;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineEatDialog;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineFirstTimeDialog;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineSportDialog;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineStudyDialog;
import bearkid.com.bearkiddiaryfamily.ui.view.IconButton;

/**
 * 主界面上 中间“+”号显示的页面
 * <p>
 * 时间轴事件类型选择界面
 */
public class TimeLineTypeFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline_type, container, false);
        initView(view);
        return view;
    }

    private final void initView(View v) {
        btn_camera = (IconButton) v.findViewById(R.id.btn_timeline_camera);
        btn_body = (IconButton) v.findViewById(R.id.btn_timeline_body);
        btn_firstTime = (IconButton) v.findViewById(R.id.btn_timeline_first_time);
        btn_eat = (IconButton) v.findViewById(R.id.btn_timeline_eat);
        btn_sport = (IconButton) v.findViewById(R.id.btn_timeline_sport);
        btn_study = (IconButton) v.findViewById(R.id.btn_timeline_study);
        btn_add = (IconButton) v.findViewById(R.id.btn_timeline_add);

        btn_camera.setOnClickListener(listener);
        btn_body.setOnClickListener(listener);
        btn_firstTime.setOnClickListener(listener);
        btn_eat.setOnClickListener(listener);
        btn_sport.setOnClickListener(listener);
        btn_study.setOnClickListener(listener);
        btn_add.setOnClickListener(listener);
    }

    private View.OnClickListener listener = v -> {
        switch (v.getId()) {
            case R.id.btn_timeline_camera:
                break;
            case R.id.btn_timeline_body:
                TimeLineBodyDialog.show(getFragmentManager());
                break;
            case R.id.btn_timeline_first_time:
                TimeLineFirstTimeDialog.show(getFragmentManager());
                break;
            case R.id.btn_timeline_eat:
                TimeLineEatDialog.show(getFragmentManager());
                break;
            case R.id.btn_timeline_sport:
                TimeLineSportDialog.show(getFragmentManager());
                break;
            case R.id.btn_timeline_study:
                TimeLineStudyDialog.show(getFragmentManager());
                break;
            case R.id.btn_chat_add:
                break;
            default:
                break;
        }
    };

    private IconButton btn_camera, btn_body, btn_firstTime, btn_eat, btn_sport, btn_study, btn_add;
}
