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
import bearkid.com.bearkiddiaryfamily.presenter.MainPresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IMainView;
import bearkid.com.bearkiddiaryfamily.ui.fragment.FamilyFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.GalleryFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.MessageFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.MeFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.TestFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.TimeLineFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.TimeLineTypeFragment;

public class MainActivity extends BaseActivity implements IMainView, View.OnClickListener {
    private LinearLayout[] tab = new LinearLayout[5];
    private int selected;
    private static int[] tabIcon = {R.drawable.main_msg, R.drawable.main_child,
            R.drawable.main_add, R.drawable.main_gallery, R.drawable.main_setting};
    private static int[] selectedIcon = {R.drawable.main_selected_msg, R.drawable.main_selected_child,
            R.drawable.main_selected_add, R.drawable.main_selected_gallery, R.drawable.main_selected_setting};
    private Fragment[] mFragments = new Fragment[5];
    private MainPresenter presenter;

    private Fragment tempFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPresenter();
        initAccount();//判断是否登陆，跳转登陆界面
        initTabView();
        initContain();//设置导航栏图标
    }

    private void initPresenter() {
        presenter = new MainPresenter(this);
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
        mFragments[1] = new TimeLineFragment();
        mFragments[2] = new TimeLineTypeFragment();
        mFragments[3] = new GalleryFragment();
        mFragments[4] = new MeFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_main_contain, mFragments[0]);
        transaction.commit();
        tempFragment = mFragments[0];
    }

    private void initAccount() {
        presenter.init();
    }

    private void changeTab(int tabNum) {
        onTabSelected(tabNum);
        changeFragment(mFragments[tabNum]);
    }

    /**
     * 切换Fragment
     * @param fragment
     */
    private void changeFragment(Fragment fragment) {
        if (fragment != tempFragment) {
            if (fragment.isAdded()) {
                getSupportFragmentManager().beginTransaction()
                        .hide(tempFragment)
                        .show(fragment)
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .hide(tempFragment)
                        .add(R.id.activity_main_contain,fragment)
                        .commit();
            }
            tempFragment = fragment;
        }
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
