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
import bearkid.com.bearkiddiaryfamily.model.bean.TimeLine;
import bearkid.com.bearkiddiaryfamily.presenter.TimeLineTypePresenter;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineBodyDialog;
import bearkid.com.bearkiddiaryfamily.ui.dialog.TimeLineDialog;
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
public class TimeLineTypeFragment extends BaseFragment implements ITimeLineTypeFragment {

    private List<String> childName = Arrays.asList("获取中");//孩子列表
    private final float textSize = 20f;//标题栏孩子名字的字体大小
    private AppCompatSpinner spinner_name;
    private ArrayAdapter<String> nameAdapter;
    private TimeLineTypePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline_type, container, false);
        initPresenter();
        initView(view);
        return view;
    }

    private void initPresenter() {
        presenter = new TimeLineTypePresenter(this);
    }

    private void initView(View v) {
        btn_camera = (IconButton) v.findViewById(R.id.btn_timeline_camera);
        btn_body = (IconButton) v.findViewById(R.id.btn_timeline_body);
        btn_firstTime = (IconButton) v.findViewById(R.id.btn_timeline_first_time);
        btn_eat = (IconButton) v.findViewById(R.id.btn_timeline_eat);
        btn_sport = (IconButton) v.findViewById(R.id.btn_timeline_sport);
        btn_study = (IconButton) v.findViewById(R.id.btn_timeline_study);
        btn_add = (IconButton) v.findViewById(R.id.btn_timeline_add);
        spinner_name = (AppCompatSpinner) v.findViewById(R.id.spinner_child_name);

        btn_camera.setOnClickListener(presenter);
        btn_body.setOnClickListener(presenter);
        btn_firstTime.setOnClickListener(presenter);
        btn_eat.setOnClickListener(presenter);
        btn_sport.setOnClickListener(presenter);
        btn_study.setOnClickListener(presenter);
        btn_add.setOnClickListener(v1 -> {

        });

        //点击名字时的下拉列表，可选择当前用户的不同孩子
        spinner_name.setAdapter(nameAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, childName) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(getContext());
                tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tv.setText(childName.get(position));
                tv.setTextSize(textSize);
                tv.setGravity(Gravity.CENTER);
                tv.setTextColor(0xffffffff);
                return tv;
            }

            @Override
            public int getCount() {
                return childName.size();
            }

            @Override
            public String getItem(int position) {
                return childName.get(position);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init();
    }

    @Override
    public void setChidrenName(List<String> name) {
        childName = name;
        nameAdapter.notifyDataSetChanged();
    }

    @Override
    public String getCurrentKidName() {
        Object obj = spinner_name.getSelectedItem();
        if (obj != null)
            return obj.toString();
        else return "";
    }

    private IconButton btn_camera, btn_body, btn_firstTime, btn_eat, btn_sport, btn_study, btn_add;
}
