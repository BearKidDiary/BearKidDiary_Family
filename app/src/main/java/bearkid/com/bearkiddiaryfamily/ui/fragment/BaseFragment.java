package bearkid.com.bearkiddiaryfamily.ui.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import bearkid.com.bearkiddiaryfamily.ui.fragment.ifragment.IBaseFragment;

/**
 * Created by admin on 2016/7/7.
 */
public class BaseFragment extends Fragment implements IBaseFragment {

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void showToast(String str) {
        if (getContext() != null)
            Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }
}
