package bearkid.com.bearkiddiaryfamily.ui.dialog;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bearkid.com.bearkiddiaryfamily.R;

/**
 * Created by admin on 2016/7/14.
 */
public class TimeLineSportDialog extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_timeline_sport, container, false);
        return view;
    }

    public static final void show(FragmentManager manager) {
        new TimeLineSportDialog().show(manager, "SportDialog");
    }
}
