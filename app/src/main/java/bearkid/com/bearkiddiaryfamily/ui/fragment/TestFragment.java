package bearkid.com.bearkiddiaryfamily.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bearkid.com.bearkiddiaryfamily.R;

/**
 * Created by admin on 2016/7/4.
 */
public class TestFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState){
        View view = layoutInflater.inflate(R.layout.activity_test, container, false);
        return view;
    }
}
