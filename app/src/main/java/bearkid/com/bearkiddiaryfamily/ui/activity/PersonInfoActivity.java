package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IPersonInfoView;

public class PersonInfoActivity extends BaseActivity implements IPersonInfoView, View.OnClickListener {

    private RelativeLayout avatarRlayout;
    private RelativeLayout nameRlayout;
    private RelativeLayout phoneRlayout;
    private RelativeLayout addressRlayout;
    private RelativeLayout emailRlayout;

    private ImageView backImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);

        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_title_back:
                finish();
                break;
        }

    }

    @Override
    public Bitmap getAvatar() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getPhone() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    public void initView(){
        avatarRlayout = (RelativeLayout) findViewById(R.id.rlayout_personinfo_avatar);
        nameRlayout = (RelativeLayout) findViewById(R.id.rlayout_personinfo_name);
        phoneRlayout = (RelativeLayout) findViewById(R.id.rlayout_personinfo_phone);
        addressRlayout = (RelativeLayout) findViewById(R.id.rlayout_personinfo_address);
        emailRlayout = (RelativeLayout) findViewById(R.id.rlayout_personinfo_email);
        backImg = (ImageView) findViewById(R.id.img_title_back);

        avatarRlayout.setOnClickListener(this);
        nameRlayout.setOnClickListener(this);
        phoneRlayout.setOnClickListener(this);
        addressRlayout.setOnClickListener(this);
        emailRlayout.setOnClickListener(this);
        backImg.setOnClickListener(this);
    }
}
