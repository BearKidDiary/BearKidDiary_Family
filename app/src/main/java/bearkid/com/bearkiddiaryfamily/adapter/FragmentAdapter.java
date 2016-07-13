package bearkid.com.bearkiddiaryfamily.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/7/13.
 */
public class FragmentAdapter extends FragmentPagerAdapter{

    List<Fragment> fragmentList = new ArrayList<Fragment>();

    public FragmentAdapter(FragmentManager fm , List<Fragment> FragmentList) {
        super(fm);
        this.fragmentList=FragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
