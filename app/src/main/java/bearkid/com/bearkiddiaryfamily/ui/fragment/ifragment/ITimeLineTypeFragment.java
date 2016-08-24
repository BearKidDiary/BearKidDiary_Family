package bearkid.com.bearkiddiaryfamily.ui.fragment.ifragment;


import android.support.v4.app.FragmentManager;

import java.util.List;

public interface ITimeLineTypeFragment extends IBaseFragment{
    void setChidrenName(List<String> name);
    String getCurrentKidName();
    FragmentManager getChildFragmentManager();
}
