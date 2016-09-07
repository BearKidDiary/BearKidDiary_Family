package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.widgets.ProgressDialog;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.bean.Kid;
import bearkid.com.bearkiddiaryfamily.presenter.AddKidPresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IAddKidView;
import bearkid.com.bearkiddiaryfamily.utils.DateTimePickerUtil;

public class AddKidActivity extends BaseActivity implements IAddKidView, View.OnClickListener{

    protected TextView titleTv;
    protected TextView confirmTv;
    protected EditText nameEt;
    protected Button male, female;
    protected TextView birthdayTv;
    protected EditText askEt;
    public static final String MALE = "男";
    public static final String FEMALE = "女";
    protected String gender;
    private long birthday;

    public static final int ADD_KID = 0;
    public static final int UPDATE_INFO = 1;
    //判断是添加孩子操作还是更新孩子信息操作
    private int type;
    private Kid kid;


    protected ProgressDialog progressDialog;
    private AddKidPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kid);
        type = getIntent().getIntExtra("type", ADD_KID);
        initPresenter();
        initView();
    }

    private void initPresenter() {
        presenter = new AddKidPresenter(this);
    }

    private void initView() {
        titleTv = (TextView) this.findViewById(R.id.tv_title);
        confirmTv = (TextView) this.findViewById(R.id.tv_add_kid_confirm);
        nameEt = (EditText) this.findViewById(R.id.et_kid_name);
        male = (Button) this.findViewById(R.id.btn_kid_gender_male);
        female = (Button) this.findViewById(R.id.btn_kid_gender_female);
        birthdayTv = (TextView) this.findViewById(R.id.tv_kid_birthday);
        askEt = (EditText) this.findViewById(R.id.et_kid_ask);

        male.setOnClickListener(this);
        female.setOnClickListener(this);
        birthdayTv.setOnClickListener(this);

        progressDialog = new ProgressDialog(AddKidActivity.this, "添加中", getResources().getColor(R.color.colorPrimary));

        if (type == ADD_KID) {
            titleTv.setText("添加孩子");
            confirmTv.setOnClickListener(v -> {
                presenter.addKid();
            });
            male.callOnClick();
        } else if (type == UPDATE_INFO) {
            titleTv.setText("修改孩子信息");
            kid = (Kid) getIntent().getSerializableExtra("kid");
            confirmTv.setOnClickListener(v -> {
                presenter.updateInfo();
            });
            nameEt.setText(kid.getKname());
            if (kid.getKsex().equals(MALE)) {
                male.callOnClick();
            } else {
                female.callOnClick();
            }
            birthdayTv.setText(DateTimePickerUtil.getFormatDate(kid.getKbirthday()));
            askEt.setText(kid.getKask());
        }
    }

    public static void startActivity(Fragment fragment, int requestCode) {
        Intent intent = new Intent(fragment.getContext(), AddKidActivity.class);
        intent.putExtra("type", ADD_KID);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void startActivity(BaseActivity activity, int requestCode, Kid kid) {
        Intent intent = new Intent(activity, AddKidActivity.class);
        intent.putExtra("type", UPDATE_INFO);
        intent.putExtra("kid", kid);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_kid_gender_male:
                gender = MALE;
                male.setBackgroundResource(R.color.gender_choose);
                female.setBackgroundResource(R.color.gender_normal);
                break;
            case R.id.btn_kid_gender_female:
                gender = FEMALE;
                female.setBackgroundResource(R.color.gender_choose);
                male.setBackgroundResource(R.color.gender_normal);
                break;
            case R.id.tv_kid_birthday:
                DateTimePickerUtil.showDatePicker(AddKidActivity.this, (date, realTime) -> {
                    birthdayTv.setText(date);
                    birthday = realTime;
                });
                break;
            default:
                break;
        }
    }

    @Override
    public Long getKidId() {
        Long kidId = null;
        if (kid != null) {
            kidId = kid.getKid();
        }
        return kidId;
    }

    @Override
    public String getKidName() {
        return nameEt.getText().toString();
    }

    @Override
    public String getKidGender() {
        return this.gender;
    }

    @Override
    public long getKidBirthday() {
        return this.birthday;
    }

    @Override
    public String getKidAsk() {
        return askEt.getText().toString();
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
        AddKidActivity.this.setResult(Activity.RESULT_OK);
        this.finish();
    }
}
