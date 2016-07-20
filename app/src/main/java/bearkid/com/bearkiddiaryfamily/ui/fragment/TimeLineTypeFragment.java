package bearkid.com.bearkiddiaryfamily.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.presenter.TimeLineTypePresenter;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineBodyDialog;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineEatDialog;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineFirstTimeDialog;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineSportDialog;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineStudyDialog;
import bearkid.com.bearkiddiaryfamily.ui.fragment.ifragment.ITimeLineTypeFragment;
import bearkid.com.bearkiddiaryfamily.ui.view.IconButton;

/**
 * 主界面上 中间“+”号显示的页面
 * <p>
 * 时间轴事件类型选择界面
 */
public class TimeLineTypeFragment extends BaseFragment implements ITimeLineTypeFragment{

    private List<String> childName = Arrays.asList("王小宝", "王小帅", "丫丫");//孩子列表
    private final float textSize = 20f;//标题栏孩子名字的字体大小
    private AppCompatSpinner spinner_name;
    private TimeLineTypePresenter presenter;

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
        spinner_name = (AppCompatSpinner) v.findViewById(R.id.spinner_child_name);

        btn_camera.setOnClickListener(listener);
        btn_body.setOnClickListener(listener);
        btn_firstTime.setOnClickListener(listener);
        btn_eat.setOnClickListener(listener);
        btn_sport.setOnClickListener(listener);
        btn_study.setOnClickListener(listener);
        btn_add.setOnClickListener(listener);

        //点击名字时的下拉列表，可选择当前用户的不同孩子
        spinner_name.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, childName) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(getContext());
                tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tv.setText(childName.get(position));
                Log.i("zy", "spinner" + childName.get(position) + " pos:" + position);
                tv.setTextSize(textSize);
                tv.setGravity(Gravity.CENTER);
                tv.setTextColor(0xffffffff);
                return tv;
            }
        });
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
