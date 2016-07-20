package bearkid.com.bearkiddiaryfamily.ui.fragment.ifragment;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.model.bean.TimeLine;

/**
 * Created by admin on 2016/7/20.
 */
public interface ITimeLineFragment extends IBaseFragment {
    void setRefreshing(boolean refreshing);

    void updateTimeLines(List<TimeLine> timeLines);

    void setTimeLines(List<TimeLine> timeLines);

    void notifyChanged();
}
