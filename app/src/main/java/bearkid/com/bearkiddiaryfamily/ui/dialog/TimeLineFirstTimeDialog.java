package bearkid.com.bearkiddiaryfamily.ui.dialog;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bearkid.com.bearkiddiaryfamily.R;

/**
 * Created by admin on 2016/7/15.
 */
public class TimeLineFirstTimeDialog extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_timeline_first_time, container, false);
        return view;
    }

    public static void show(FragmentManager manager){
        new TimeLineFirstTimeDialog().show(manager,"FirstTimeDialog");
    }
}