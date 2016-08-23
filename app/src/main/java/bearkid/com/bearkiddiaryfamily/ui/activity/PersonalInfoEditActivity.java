package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.widgets.ProgressDialog;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.presenter.PersonInfoEditPresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IPersonalInfoEditView;

/**
 * Created by YarenChoi on 2016/8/12.
 * 修改个人信息界面
 */
public class PersonalInfoEditActivity extends BaseActivity implements IPersonalInfoEditView, View.OnClickListener{
    private static final String TYPE = "editType";
    private static final String MSG = "editMsg";
    public static final int TYPE_NAME = 0;
    public static final int TYPE_GENDER = 1;
    public static final int TYPE_ADDRESS = 2;
    public static final int TYPE_EMAIL = 3;
    public static final String FEMALE = "女";
    public static final String MALE = "男";

    protected int type;
    protected TextView tv_title;
    protected TextView tv_hint;
    protected EditText et_content;
    protected ImageView iv_clear;
    protected Button btn_male;
    protected Button btn_female;
    protected ProgressDialog progressDialog;

    private PersonInfoEditPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info_edit);
        initView();
        initPresenter();
    }

    private void initPresenter() {
        presenter = new PersonInfoEditPresenter(this);
    }

    private void initView() {
        tv_title = (TextView) this.findViewById(R.id.tv_title);
        tv_hint = (TextView) findViewById(R.id.tv_personal_info_hint);
        et_content = (EditText) findViewById(R.id.et_personal_info);
        iv_clear = (ImageView) findViewById(R.id.iv_personal_info_clear);
        btn_male = (Button) findViewById(R.id.btn_personal_info_gender_male);
        btn_female = (Button) findViewById(R.id.btn_personal_info_gender_female);

        progressDialog = new ProgressDialog(this, "加载中，请稍候", R.color.colorPrimary);

        this.findViewById(R.id.tv_personal_info_confirm).setOnClickListener(this);
        iv_clear.setOnClickListener(this);
        btn_male.setOnClickListener(this);
        btn_female.setOnClickListener(this);

        et_content.setText(getIntent().getStringExtra(MSG));
        et_content.setSelection(getIntent().getStringExtra(MSG).length());
        type = getIntent().getIntExtra(TYPE, TYPE_NAME);
        switch (type) {
            case TYPE_NAME:
                tv_title.setText(R.string.personal_info_edit_name);
                tv_hint.setText(R.string.personal_info_name);
                break;
            case TYPE_GENDER:
                tv_title.setText(R.string.personal_info_edit_gender);
                tv_hint.setText(R.string.personal_info_gender);
                et_content.setVisibility(View.GONE);
                iv_clear.setVisibility(View.GONE);
                btn_male.setVisibility(View.VISIBLE);
                btn_female.setVisibility(View.VISIBLE);
                if (getIntent().getStringExtra(MSG).equals(MALE)) {
                    btn_male.callOnClick();
                } else if (getIntent().getStringExtra(MSG).equals(FEMALE)){
                    btn_female.callOnClick();
                }
                break;
            case TYPE_ADDRESS:
                tv_title.setText(R.string.personal_info_edit_address);
                tv_hint.setText(R.string.personal_info_address);
                break;
            case TYPE_EMAIL:
                tv_title.setText(R.string.personal_info_edit_email);
                tv_hint.setText(R.string.personal_info_email);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_personal_info_confirm://确认修改操作
                presenter.update(type);
                break;
            case R.id.iv_personal_info_clear:
                et_content.setText("");
                break;
            case R.id.btn_personal_info_gender_male:
                btn_male.setBackgroundResource(R.color.gender_choose);
                btn_female.setBackgroundResource(R.color.gender_normal);
                break;
            case R.id.btn_personal_info_gender_female:
                btn_female.setBackgroundResource(R.color.gender_choose);
                btn_male.setBackgroundResource(R.color.gender_normal);
                break;
            default:
                break;
        }
    }

    public static void startActivity(Context context, int type, String msg) {
        Intent intent = new Intent(context, PersonalInfoEditActivity.class);
        intent.putExtra(TYPE, type);
        intent.putExtra(MSG, msg);
        context.startActivity(intent);
    }

    @Override
    public String getEditContent() {
        return et_content.getText().toString();
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showResult(String result) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    @Override
    public void exit() {
        finish();
    }
}
