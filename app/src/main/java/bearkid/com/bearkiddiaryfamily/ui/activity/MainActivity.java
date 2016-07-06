package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.ui.fragment.GalleryFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.TestFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    protected LinearLayout[] tab = new LinearLayout[5];
    protected int selected;
    protected static int[] tabIcon = {1,2,3,4,5};
    protected Fragment[] mFragments = new Fragment[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTabView();
        initContain();
    }

    private void initTabView() {
        tab[0] = (LinearLayout)findViewById(R.id.activity_main_tab1);
        tab[1] = (LinearLayout)findViewById(R.id.activity_main_tab2);
        tab[2] = (LinearLayout)findViewById(R.id.activity_main_tab3);
        tab[3] = (LinearLayout)findViewById(R.id.activity_main_tab4);
        tab[4] = (LinearLayout)findViewById(R.id.activity_main_tab5);
        tab[0].setOnClickListener(this);
        tab[1].setOnClickListener(this);
        tab[2].setOnClickListener(this);
        tab[3].setOnClickListener(this);
        tab[4].setOnClickListener(this);
    }

    private void initContain() {
        selected = 0;
        ((ImageView)tab[0].getChildAt(0)).setImageResource(R.drawable.admin);
        ((TextView)tab[0].getChildAt(1)).setTextColor(0xffffaf00);
        mFragments[0] = new TestFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_contain, mFragments[0]);
        transaction.commit();
    }

    private void changeTab(int tabNum) {
        onTabSelected(tabNum);
        if (mFragments[tabNum] == null){
            switch (tabNum){
                case 0:
                    mFragments[0] = new TestFragment();
                    break;
                case 1:
                    mFragments[1] = new TestFragment();
                    break;
                case 2:
                    mFragments[2] = new TestFragment();
                    break;
                case 3:
                    mFragments[3] = new GalleryFragment();
                    break;
                case 4:
                    mFragments[4] = new TestFragment();
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
        ((ImageView)tab[selected].getChildAt(0)).setImageResource(R.drawable.watchman);
        ((TextView)tab[selected].getChildAt(1)).setTextColor(0xff000000);
        ((ImageView)tab[tabNum].getChildAt(0)).setImageResource(R.drawable.admin);
        ((TextView)tab[tabNum].getChildAt(1)).setTextColor(0xffffaf00);
        selected = tabNum;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
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
