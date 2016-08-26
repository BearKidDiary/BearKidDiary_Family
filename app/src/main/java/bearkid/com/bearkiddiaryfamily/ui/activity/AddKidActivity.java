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
import bearkid.com.bearkiddiaryfamily.presenter.AddKidPresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IAddKidView;
import bearkid.com.bearkiddiaryfamily.utils.DateTimePickerUtil;

public class AddKidActivity extends BaseActivity implements IAddKidView, View.OnClickListener{

    protected TextView confirm;
    protected EditText name;
    protected Button male, female;
    protected TextView birthdayTv;
    public static final String MALE = "男";
    public static final String FEMALE = "女";
    protected String gender;
    private long birthday;

    protected ProgressDialog progressDialog;
    private AddKidPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kid);

        initView();
        initPresenter();
    }

    private void initPresenter() {
        presenter = new AddKidPresenter(this);
    }

    private void initView() {
        confirm = (TextView) findViewById(R.id.tv_add_kid_confirm);
        name = (EditText) findViewById(R.id.et_kid_name);
        male = (Button) findViewById(R.id.btn_kid_gender_male);
        female = (Button) findViewById(R.id.btn_kid_gender_female);
        birthdayTv = (TextView) findViewById(R.id.tv_kid_birthday);

        confirm.setOnClickListener(this);
        male.setOnClickListener(this);
        female.setOnClickListener(this);
        birthdayTv.setOnClickListener(this);

        progressDialog = new ProgressDialog(this, "添加中", R.color.colorPrimary);

        male.callOnClick();
    }

    public static void startActivity(Fragment fragment, int requestCode) {
        fragment.startActivityForResult(new Intent(fragment.getContext(), AddKidActivity.class), requestCode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_kid_confirm:
                if (presenter != null) {
                    presenter.addKid();
                }
                break;
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
    public String getKidName() {
        return name.getText().toString();
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
