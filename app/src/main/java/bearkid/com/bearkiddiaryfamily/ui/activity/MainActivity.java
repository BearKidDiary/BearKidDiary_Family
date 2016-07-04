package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bearkid.com.bearkiddiaryfamily.R;

public class MainActivity extends BaseActivity {
    protected RelativeLayout[] tab = new RelativeLayout[5];
    protected static int selected;
    protected int[] tabIcon = {1,2,3,4,5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTabView();
        initTabContain();
    }

    private void initTabContain() {

    }

    private void initTabView() {
        tab[0] = (RelativeLayout) findViewById(R.id.activity_main_tab1);
        tab[1] = (RelativeLayout) findViewById(R.id.activity_main_tab2);
        tab[2] = (RelativeLayout) findViewById(R.id.activity_main_tab3);
        tab[3] = (RelativeLayout) findViewById(R.id.activity_main_tab4);
        tab[4] = (RelativeLayout) findViewById(R.id.activity_main_tab5);
        tab[0].setOnClickListener(tabViewOnClickListener);
        tab[1].setOnClickListener(tabViewOnClickListener);
        tab[2].setOnClickListener(tabViewOnClickListener);
        tab[3].setOnClickListener(tabViewOnClickListener);
        tab[4].setOnClickListener(tabViewOnClickListener);
        selected = 0;
    }

    View.OnClickListener tabViewOnClickListener = new View.OnClickListener() {
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
    };

    public void changeTab(int tabNum) {
        onTabViewChanged(tabNum);
    }

    private void onTabViewChanged(int tabNum){
        ((TextView)tab[selected].getChildAt(0)).setTextColor(0xffffaf00);
        ((TextView)tab[tabNum].getChildAt(0)).setTextColor(0xffffff00);
        selected = tabNum;
    }
}
