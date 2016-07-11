package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.ui.fragment.FamilyFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.GalleryFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.MessageFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.MeFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.TestFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.TimeLineTypeFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    protected LinearLayout[] tab = new LinearLayout[5];
    protected int selected;
    protected static int[] tabIcon = {R.drawable.main_msg,
            R.drawable.main_child,
            R.drawable.main_add,
            R.drawable.main_gallery,
            R.drawable.main_setting};
    protected static int[] selectedIcon = {R.drawable.main_selected_msg,
            R.drawable.main_selected_child,
            R.drawable.main_selected_add,
            R.drawable.main_selected_gallery,
            R.drawable.main_selected_setting};
    protected Fragment[] mFragments = new Fragment[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTabView();
        initContain();
    }

    private void initTabView() {
        tab[0] = (LinearLayout) findViewById(R.id.activity_main_tab1);
        tab[1] = (LinearLayout) findViewById(R.id.activity_main_tab2);
        tab[2] = (LinearLayout) findViewById(R.id.activity_main_tab3);
        tab[3] = (LinearLayout) findViewById(R.id.activity_main_tab4);
        tab[4] = (LinearLayout) findViewById(R.id.activity_main_tab5);
        tab[0].setOnClickListener(this);
        tab[1].setOnClickListener(this);
        tab[2].setOnClickListener(this);
        tab[3].setOnClickListener(this);
        tab[4].setOnClickListener(this);
    }

    private void initContain() {
        for (int i = 0; i < 5; i++) {
            ((ImageView) tab[i].getChildAt(0)).setImageResource(tabIcon[i]);
        }

        selected = 0;
        ((ImageView) tab[selected].getChildAt(0)).setImageResource(selectedIcon[selected]);
        ((TextView) tab[selected].getChildAt(1)).setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        mFragments[0] = new MessageFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_contain, mFragments[0]);
        transaction.commit();
    }

    private void changeTab(int tabNum) {
        onTabSelected(tabNum);
        if (mFragments[tabNum] == null) {
            switch (tabNum) {
                case 0:
                    mFragments[0] = new MessageFragment();
                    break;
                case 1:
                    mFragments[1] = new FamilyFragment();
                    break;
                case 2:
                    mFragments[2] = new TimeLineTypeFragment();
                    break;
                case 3:
                    mFragments[3] = new GalleryFragment();
                    break;
                case 4:
                    mFragments[4] = new MeFragment();
                    break;
                default:
                    break;
            }
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_contain, mFragments[tabNum]);
        transaction.commit();
    }

    private void onTabSelected(int tabNum) {
        if (selected == tabNum) return;
        ((ImageView) tab[selected].getChildAt(0)).setImageResource(tabIcon[selected]);
        ((TextView) tab[selected].getChildAt(1)).setTextColor(Color.BLACK);
        ((ImageView) tab[tabNum].getChildAt(0)).setImageResource(selectedIcon[tabNum]);
        ((TextView) tab[tabNum].getChildAt(1)).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        selected = tabNum;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_tab1:
                changeTab(0);
                break;
            case R.id.activity_main_tab2:
                changeTab(1);
                break;
            case R.id.activity_main_tab3:
                changeTab(2);
                break;
            case R.id.activity_main_tab4:
                changeTab(3);
                break;
            case R.id.activity_main_tab5:
                changeTab(4);
                break;
            default:
                break;
        }
    }
}
