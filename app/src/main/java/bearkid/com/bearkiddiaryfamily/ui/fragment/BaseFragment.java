package bearkid.com.bearkiddiaryfamily.ui.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import bearkid.com.bearkiddiaryfamily.ui.fragment.ifragment.IBaseFragment;

/**
 * Created by admin on 2016/7/7.
 */
public class BaseFragment extends Fragment implements IBaseFragment {
    //以防以后要对fragment作统一的修改
    @Override
    public Context getContext() {
        return getActivity();
    }
}
