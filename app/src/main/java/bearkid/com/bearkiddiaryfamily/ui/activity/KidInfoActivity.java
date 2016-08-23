package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IKidInfoView;
import bearkid.com.bearkiddiaryfamily.ui.view.KidInfoView;

public class KidInfoActivity extends BaseActivity implements IKidInfoView, View.OnClickListener{
    TextView edit;

    ImageView avatar;
    TextView name;
    TextView birth;
    ImageView sex;

    RelativeLayout list_1, list_2, list_3;
    ImageView list_1_iv, list_2_iv, list_3_iv;
    ImageView list_1_add, list_2_add, list_3_add;
    LinearLayout ll_1, ll_2, ll_3;

    protected boolean list_1_expand, list_2_expand, list_3_expand;
    protected boolean isEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_info);
        initView();
    }

    private void initView() {
        edit = (TextView) findViewById(R.id.tv_kid_info_edit);
        avatar = (ImageView) findViewById(R.id.iv_kid_info_avatar);
        name = (TextView) findViewById(R.id.tv_kid_info_name);
        birth = (TextView) findViewById(R.id.tv_kid_info_birth);
        sex = (ImageView) findViewById(R.id.iv_kid_info_sex);
        list_1 = (RelativeLayout) findViewById(R.id.rl_kid_info_list_1);
        list_2 = (RelativeLayout) findViewById(R.id.rl_kid_info_list_2);
        list_3 = (RelativeLayout) findViewById(R.id.rl_kid_info_list_3);
        list_1_iv = (ImageView) findViewById(R.id.iv_kid_info_list_1);
        list_2_iv = (ImageView) findViewById(R.id.iv_kid_info_list_2);
        list_3_iv = (ImageView) findViewById(R.id.iv_kid_info_list_3);
        list_1_add = (ImageView) findViewById(R.id.iv_kid_info_add_list_1);
        list_2_add = (ImageView) findViewById(R.id.iv_kid_info_add_list_2);
        list_3_add = (ImageView) findViewById(R.id.iv_kid_info_add_list_3);
        ll_1 = (LinearLayout) findViewById(R.id.ll_kid_info_list_1);
        ll_2 = (LinearLayout) findViewById(R.id.ll_kid_info_list_2);
        ll_3 = (LinearLayout) findViewById(R.id.ll_kid_info_list_3);

        edit.setOnClickListener(this);
        list_1.setOnClickListener(this);
        list_2.setOnClickListener(this);
        list_3.setOnClickListener(this);
        list_1_add.setOnClickListener(this);
        list_2_add.setOnClickListener(this);
        list_3_add.setOnClickListener(this);

        list_1_expand = true;
        list_2_expand = true;
        list_3_expand = true;
        isEdit = false;
        list_1_add.setVisibility(View.GONE);
        list_2_add.setVisibility(View.GONE);
        list_3_add.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_kid_info_edit:
                if (isEdit) {
                    edit.setText("编辑");
                    list_1_add.setVisibility(View.GONE);
                    list_2_add.setVisibility(View.GONE);
                    list_3_add.setVisibility(View.GONE);
                    isEdit = !isEdit;
                } else {
                    edit.setText("完成");
                    list_1_add.setVisibility(View.VISIBLE);
                    list_2_add.setVisibility(View.VISIBLE);
                    list_3_add.setVisibility(View.VISIBLE);
                    isEdit = !isEdit;
                }
                break;
            case R.id.rl_kid_info_list_1:
                if (list_1_expand) {
                    ll_1.setVisibility(View.GONE);
                    list_1_iv.setBackgroundResource(R.drawable.arrow_down);
                    list_1_expand = !list_1_expand;
                } else {
                    ll_1.setVisibility(View.VISIBLE);
                    list_1_iv.setBackgroundResource(R.drawable.arrow_up);
                    list_1_expand = !list_1_expand;
                }
                break;
            case R.id.rl_kid_info_list_2:
                if (list_2_expand) {
                    ll_2.setVisibility(View.GONE);
                    list_2_iv.setBackgroundResource(R.drawable.arrow_down);
                    list_2_expand = !list_2_expand;
                } else {
                    ll_2.setVisibility(View.VISIBLE);
                    list_2_iv.setBackgroundResource(R.drawable.arrow_up);
                    list_2_expand = !list_2_expand;
                }
                break;
            case R.id.rl_kid_info_list_3:
                if (list_3_expand) {
                    ll_3.setVisibility(View.GONE);
                    list_3_iv.setBackgroundResource(R.drawable.arrow_down);
                    list_3_expand = !list_3_expand;
                } else {
                    ll_3.setVisibility(View.VISIBLE);
                    list_3_iv.setBackgroundResource(R.drawable.arrow_up);
                    list_3_expand = !list_3_expand;
                }
                break;
            case R.id.iv_kid_info_add_list_1:
                break;
            case R.id.iv_kid_info_add_list_2:
                break;
            case R.id.iv_kid_info_add_list_3:
                break;
            default:
                break;
        }
    }

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, KidInfoActivity.class));
    }

    @Override
    public void showHeightAndWeight(int height, int weight, String date) {
        new KidInfoView(KidInfoActivity.this)
                .init(KidInfoView.HEIGHTANDWEIGHT)
                .setHWValue(height, weight, date)
                .done(ll_1);
    }

    @Override
    public void showVision(double left, double right, String date) {
        new KidInfoView(KidInfoActivity.this)
                .init(KidInfoView.VISION)
                .setVValue(left, right, date)
                .done(ll_2);
    }

    @Override
    public void showExhort(int count, String exhort) {
        new KidInfoView(KidInfoActivity.this)
                .init(KidInfoView.EXHORT)
                .setExhort(count, exhort)
                .done(ll_3);
    }
}
