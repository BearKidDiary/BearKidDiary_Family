package bearkid.com.bearkiddiaryfamily.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bearkid.com.bearkiddiaryfamily.R;

/**
 * Created by admin on 2016/7/13.
 */
public class TimeLineFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline_type, container, false);
        return view;
    }
}
