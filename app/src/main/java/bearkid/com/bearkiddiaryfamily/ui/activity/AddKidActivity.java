package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    protected TextView birthday;
    public static final String MALE = "男";
    public static final String FEMALE = "女";
    protected String gender;

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
        birthday = (TextView) findViewById(R.id.tv_kid_birthday);

        confirm.setOnClickListener(this);
        male.setOnClickListener(this);
        female.setOnClickListener(this);
        birthday.setOnClickListener(this);

        progressDialog = new ProgressDialog(this, "添加中", R.color.colorPrimary);

        male.callOnClick();
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, AddKidActivity.class));
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
                DateTimePickerUtil.showDatePicker(AddKidActivity.this, new DateTimePickerUtil.OnDateSetListener() {
                    @Override
                    public void onDateSet(String date) {
                        birthday.setText(date);
                    }
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
        return gender;
    }

    @Override
    public long getKidBirthday() {
        return 0;
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
