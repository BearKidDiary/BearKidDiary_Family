package bearkid.com.bearkiddiaryfamily.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import bearkid.com.bearkiddiaryfamily.R;

/**
 * Created by admin on 2016/7/20.
 */
public class KidInfoView extends LinearLayout {
    public static final int HEIGHTANDWEIGHT = 0;
    public static final int VISION = 1;
    public static final int EXHORT = 2;
    Context context;

    TextView tv_height;
    TextView tv_weight;
    TextView tv_date;


    public KidInfoView(Context context) {
        super(context);
        this.context = context;
    }

    public void init(int type) {
        if (type == HEIGHTANDWEIGHT) {
            initHeightAndWeightView();
        } else if (type == VISION) {
            initVisionView();
        } else if (type == EXHORT) {
            initExhortView();
        }
    }

    private void initHeightAndWeightView() {
        LayoutInflater.from(context).inflate(R.layout.item_kidinfo_show_1, this, true);
        tv_height = (TextView) findViewById(R.id.tv_kid_info_show_height);
        tv_weight = (TextView) findViewById(R.id.tv_kid_info_show_weight);
        tv_date = (TextView) findViewById(R.id.tv_kid_info_show_date);
    }

    public void setValue(int height, int weight, String date) {
        tv_height.setText(height + "cm");
        tv_weight.setText(weight + "kg");
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //String date = sdf.format(new java.util.Date());
        tv_date.setText(date);
    }

    private void initVisionView() {
        LayoutInflater.from(context).inflate(R.layout.item_kidinfo_show_1, this, true);
    }

    private void initExhortView() {
        LayoutInflater.from(context).inflate(R.layout.item_kidinfo_show_2, this, true);
    }
}
