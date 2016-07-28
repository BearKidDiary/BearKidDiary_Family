package bearkid.com.bearkiddiaryfamily.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import bearkid.com.bearkiddiaryfamily.R;

/**
 * Created by admin on 2016/7/20.
 * 孩子信息界面显示
 * 身高、体重
 * 视力
 * 家长叮嘱
 */
public class KidInfoView extends LinearLayout {
    public static final int HEIGHTANDWEIGHT = 0;
    public static final int VISION = 1;
    public static final int EXHORT = 2;
    Context context;

    TextView tv_left;
    TextView tv_right;
    TextView tv_date;
    TextView tv_count;
    TextView tv_exhort;


    public KidInfoView(Context context) {
        super(context);
        this.context = context;
    }

    public KidInfoView init(int type) {
        if (type == HEIGHTANDWEIGHT) {
            initHeightAndWeightView();
        } else if (type == VISION) {
            initVisionView();
        } else if (type == EXHORT) {
            initExhortView();
        }
        return this;
    }

    private void initHeightAndWeightView() {
        LayoutInflater.from(context).inflate(R.layout.item_kidinfo_show_1, this, true);
        tv_left = (TextView) findViewById(R.id.tv_kid_info_show_height);
        tv_right = (TextView) findViewById(R.id.tv_kid_info_show_weight);
        tv_date = (TextView) findViewById(R.id.tv_kid_info_show_date);
    }

    private void initVisionView() {
        LayoutInflater.from(context).inflate(R.layout.item_kidinfo_show_1, this, true);
        tv_left = (TextView) findViewById(R.id.tv_kid_info_show_height);
        tv_right = (TextView) findViewById(R.id.tv_kid_info_show_weight);
        tv_date = (TextView) findViewById(R.id.tv_kid_info_show_date);
    }

    private void initExhortView() {
        LayoutInflater.from(context).inflate(R.layout.item_kidinfo_show_2, this, true);
        tv_count = (TextView) findViewById(R.id.tv_kid_info_show_exhort_count);
        tv_exhort = (TextView) findViewById(R.id.tv_kid_info_show_exhort);
    }

    public KidInfoView setHWValue(int height, int weight, String date) {
        tv_left.setText(height + "cm");
        tv_right.setText(weight + "kg");
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //String date = sdf.format(new java.util.Date());
        tv_date.setText(date);
        return this;
    }

    public KidInfoView setVValue(double left, double right, String date) {
        tv_left.setText("左眼" + left);
        tv_right.setText("右眼" + right);
        tv_date.setText(date);
        return this;
    }

    public KidInfoView setExhort(int count, String exhort) {
        tv_count.setText(count + "、");
        tv_exhort.setText(exhort);
        return this;
    }

    public void done(LinearLayout ll) {
        ll.addView(this);
    }
}
