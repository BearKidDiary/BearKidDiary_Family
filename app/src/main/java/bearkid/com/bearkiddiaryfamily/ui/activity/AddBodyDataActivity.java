package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.widgets.ProgressDialog;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.presenter.AddBodyDataPresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IAddBodyDataView;
import bearkid.com.bearkiddiaryfamily.utils.DateTimePickerUtil;

public class AddBodyDataActivity extends BaseActivity implements IAddBodyDataView, View.OnClickListener{

    protected TextView confirmTv;
    protected EditText heightEt;
    protected EditText weightEt;
    protected EditText visionLeftEt;
    protected EditText visionRightEt;
    protected TextView heightDateTv;
    protected TextView weightDateTv;
    protected TextView visionDateTv;
    protected ProgressDialog progressDialog;

    private Long kidId;
    private Long heightDate;
    private Long weightDate;
    private Long visionDate;

    private AddBodyDataPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_body_data);
        kidId = getIntent().getLongExtra("kidId", -1);
        initView();
        initPresenter();
    }

    private void initPresenter() {
        presenter = new AddBodyDataPresenter(AddBodyDataActivity.this, kidId);
    }

    private void initView() {
        confirmTv = (TextView) this.findViewById(R.id.tv_body_confirm);
        heightEt = (EditText) this.findViewById(R.id.et_body_height);
        weightEt = (EditText) this.findViewById(R.id.et_body_weight);
        visionLeftEt = (EditText) this.findViewById(R.id.et_body_vision_left);
        visionRightEt = (EditText) this.findViewById(R.id.et_body_vision_right);
        heightDateTv = (TextView) this.findViewById(R.id.tv_body_height_date);
        weightDateTv = (TextView) this.findViewById(R.id.tv_body_weight_date);
        visionDateTv = (TextView) this.findViewById(R.id.tv_body_vision_date);

        progressDialog = new ProgressDialog(AddBodyDataActivity.this, "加载中，请稍候", R.color.colorPrimary);

        confirmTv.setOnClickListener(this);
        heightDateTv.setOnClickListener(this);
        weightDateTv.setOnClickListener(this);
        visionDateTv.setOnClickListener(this);
    }

    public static void startActivity(BaseActivity activity, int requestCode, Long kidId) {
        Intent intent = new Intent(activity, AddBodyDataActivity.class);
        intent.putExtra("kidId", kidId);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public void showProgress() {
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showResult(String result) {
        Looper.prepare();
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        Looper.loop();
    }

    @Override
    public String getHeight() {
        return heightEt.getText().toString();
    }

    @Override
    public String getWeight() {
        return weightEt.getText().toString();
    }

    @Override
    public String getLeftVision() {
        return visionLeftEt.getText().toString();
    }

    @Override
    public String getRightVision() {
        return visionRightEt.getText().toString();
    }

    @Override
    public Long getHeightDate() {
        return heightDate;
    }

    @Override
    public Long getWeightDate() {
        return weightDate;
    }

    @Override
    public Long getVisionDate() {
        return visionDate;
    }

    @Override
    public void exit() {
        AddBodyDataActivity.this.setResult(Activity.RESULT_OK);
        this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_body_confirm:
                presenter.addBodyData();
                break;
            case R.id.tv_body_height_date:
                DateTimePickerUtil.showDatePicker(AddBodyDataActivity.this, new DateTimePickerUtil.OnDateSetListener() {
                    @Override
                    public void onDateSet(String date, long realTime) {
                        heightDateTv.setText(date);
                        heightDate = realTime;
                    }
                });
                break;
            case R.id.tv_body_weight_date:
                DateTimePickerUtil.showDatePicker(AddBodyDataActivity.this, new DateTimePickerUtil.OnDateSetListener() {
                    @Override
                    public void onDateSet(String date, long realTime) {
                        weightDateTv.setText(date);
                        weightDate = realTime;
                    }
                });
                break;
            case R.id.tv_body_vision_date:
                DateTimePickerUtil.showDatePicker(AddBodyDataActivity.this, new DateTimePickerUtil.OnDateSetListener() {
                    @Override
                    public void onDateSet(String date, long realTime) {
                        visionDateTv.setText(date);
                        visionDate = realTime;
                    }
                });
                break;
            default:
                break;
        }

    }
}
