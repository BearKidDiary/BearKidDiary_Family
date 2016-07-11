package bearkid.com.bearkiddiaryfamily.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bearkid.com.bearkiddiaryfamily.R;

/**
 * 主界面上 中间“+”号显示的页面
 * <p/>
 * 时间轴事件类型选择界面
 */
public class TimeLineTypeFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline_type, container, false);
        return view;
    }
}
