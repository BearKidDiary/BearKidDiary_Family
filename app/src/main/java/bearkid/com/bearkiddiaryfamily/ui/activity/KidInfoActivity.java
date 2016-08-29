package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.bean.Kid;
import bearkid.com.bearkiddiaryfamily.presenter.KidInfoPresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IKidInfoView;
import bearkid.com.bearkiddiaryfamily.ui.view.BodyDataView;
import bearkid.com.bearkiddiaryfamily.utils.DateTimePickerUtil;

public class KidInfoActivity extends BaseActivity implements IKidInfoView, View.OnClickListener{
    TextView edit;

    ImageView avatar;
    TextView name;
    TextView birth;
    ImageView sex;
    TextView exhort;

    LinearLayout ll_kid_info;//编辑个人资料
    RelativeLayout list_1, list_2, list_3, list_4;//数据标题列表
    ImageView icon1, icon2, icon3, icon4;//展开图标
    BodyDataView heightView, weightView, visionView;

    private static final int REQUEST_ADD_DATA = 11;
    private static final int REQUEST_UPDATE_INFO = 12;

    protected boolean heightVisible = true, weightVisible = true, visionVisible = true, exhortVisible = true;
    private Kid kid;
    private KidInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_info);
        kid = (Kid) getIntent().getSerializableExtra("kid");
        initView();
        initPresenter();
    }

    private void initPresenter() {
        presenter = new KidInfoPresenter(this, kid.getKid());
    }

    private void initView() {
        ll_kid_info = (LinearLayout) this.findViewById(R.id.ll_kid_info);
        edit = (TextView) findViewById(R.id.tv_kid_info_edit);
        avatar = (ImageView) findViewById(R.id.iv_kid_info_avatar);
        name = (TextView) findViewById(R.id.tv_kid_info_name);
        birth = (TextView) findViewById(R.id.tv_kid_info_birth);
        sex = (ImageView) findViewById(R.id.iv_kid_info_sex);
        list_1 = (RelativeLayout) findViewById(R.id.rl_kid_info_list_1);
        list_2 = (RelativeLayout) findViewById(R.id.rl_kid_info_list_2);
        list_3 = (RelativeLayout) findViewById(R.id.rl_kid_info_list_3);
        list_4 = (RelativeLayout) findViewById(R.id.rl_kid_info_list_4);
        icon1 = (ImageView) findViewById(R.id.iv_kid_info_list_1);
        icon2 = (ImageView) findViewById(R.id.iv_kid_info_list_2);
        icon3 = (ImageView) findViewById(R.id.iv_kid_info_list_3);
        icon4 = (ImageView) findViewById(R.id.iv_kid_info_list_4);
        heightView = (BodyDataView) findViewById(R.id.bdv_height);
        weightView = (BodyDataView) findViewById(R.id.bdv_weight);
        visionView = (BodyDataView) findViewById(R.id.bdv_vision);
        exhort = (TextView) findViewById(R.id.tv_exhort);

        ll_kid_info.setOnClickListener(this);
        edit.setOnClickListener(this);
        list_1.setOnClickListener(this);
        list_2.setOnClickListener(this);
        list_3.setOnClickListener(this);
        list_4.setOnClickListener(this);

        name.setText(kid.getKname());

        if (kid.getKbirthday() != null) {
            birth.setText(DateTimePickerUtil.getFormatDate(kid.getKbirthday()));
        }

        if (kid.getKsex() != null) {
            if (kid.getKsex().equals("男")) {
                sex.setImageResource(R.drawable.male);
            } else {
                sex.setImageResource(R.drawable.female);
            }
        }

        if (kid.getKask() != null) {
            setExhort(kid.getKask());
        }

    }


    @Override
    public void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        switch (request) {
            case REQUEST_ADD_DATA:
                if (result == Activity.RESULT_OK) {
                    presenter.init();
                }
                break;
            case REQUEST_UPDATE_INFO:
                if (result == Activity.RESULT_OK) {
                    // TODO: 2016/8/29 刷新孩子信息
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_kid_info_edit://添加身体数据
                AddBodyDataActivity.startActivity(KidInfoActivity.this, REQUEST_ADD_DATA, kid.getKid());
                break;
            case R.id.ll_kid_info://修改孩子信息
                AddKidActivity.startActivity(this, REQUEST_UPDATE_INFO, kid);
                break;
            case R.id.rl_kid_info_list_1:
                expandDataList(heightVisible, heightView, icon1);
                heightVisible = !heightVisible;
                break;
            case R.id.rl_kid_info_list_2:
                expandDataList(weightVisible, weightView, icon2);
                weightVisible = !weightVisible;
                break;
            case R.id.rl_kid_info_list_3:
                expandDataList(visionVisible, visionView, icon3);
                visionVisible = !visionVisible;
                break;
            case R.id.rl_kid_info_list_4:
                expandDataList(exhortVisible, exhort, icon4);
                exhortVisible = !exhortVisible;
                break;
            default:
                break;
        }
    }

    public static void startActivity(Context context, Kid kid){
        Intent intent = new Intent(context, KidInfoActivity.class);
        intent.putExtra("kid", kid);
        context.startActivity(intent);
    }

    private void expandDataList(boolean isExpand, View view, ImageView iv) {
        if (isExpand) {
            view.setVisibility(View.GONE);
            iv.setBackgroundResource(R.drawable.arrow_down);
        } else {
            view.setVisibility(View.VISIBLE);
            iv.setBackgroundResource(R.drawable.arrow_up);
        }
    }

    @Override
    public void showHeight(List heightList) {
        heightView.show(heightList);
    }

    @Override
    public void clearHeightData() {
        heightView.clear();
    }

    @Override
    public void showWeight(List weightList) {
        weightView.show(weightList);
    }

    @Override
    public void clearWeightData() {
        weightView.clear();
    }

    @Override
    public void showVision(List visionList) {
        visionView.show(visionList);
    }

    @Override
    public void clearVisionData() {
        visionView.clear();
    }

    @Override
    public void setExhort(String exhort) {
        this.exhort.setText(exhort);
    }
}
