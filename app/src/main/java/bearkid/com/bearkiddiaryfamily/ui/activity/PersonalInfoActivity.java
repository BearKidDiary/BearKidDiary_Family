package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.presenter.PersonInfoPresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IPersonalInfoView;
import bearkid.com.bearkiddiaryfamily.utils.BitmapSelector;
import bearkid.com.bearkiddiaryfamily.utils.GalleryFinalHelper;

public class PersonalInfoActivity extends BaseActivity implements IPersonalInfoView, View.OnClickListener {

    private static final int REQUEST_CODE_AVATAR = 732;
    private ArrayList<String> mResults;

    protected RelativeLayout avatarRlayout;
    protected LinearLayout nameLlayout;
    protected LinearLayout genderLlayout;
    //    protected LinearLayout phoneLlayout;
    protected LinearLayout addressLlayout;
    protected LinearLayout emailLlayout;

    private ImageView avatarImg;
    private TextView nameTv;
    private TextView genderTv;
    private TextView phoneTv;
    private TextView addressTv;
    private TextView emailTv;

    private PersonInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        initView();
        initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.init();
    }

    private void initPresenter() {
        presenter = new PersonInfoPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_personal_info_avatar://更换头像
                new GalleryFinalHelper(getContext()).openGallery(1, new BitmapSelector.CallBack() {
                    @Override
                    public void finish(List<String> path, int picNum) {
                        String image = path.get(0);
                        Bitmap bitmap = BitmapFactory.decodeFile(image);
                        Drawable drawable = new BitmapDrawable(bitmap);
                        avatarImg.setImageDrawable(drawable);
                    }

                    @Override
                    public void error(String msg) {

                    }
                });

                break;
            case R.id.ll_personal_info_name://编辑姓名
                PersonalInfoEditActivity.startActivity(PersonalInfoActivity.this,
                        PersonalInfoEditActivity.TYPE_NAME,
                        nameTv.getText().toString());
                break;
            case R.id.ll_personal_info_gender://编辑性别
                PersonalInfoEditActivity.startActivity(PersonalInfoActivity.this,
                        PersonalInfoEditActivity.TYPE_GENDER,
                        genderTv.getText().toString());
                break;
            case R.id.ll_personal_info_address://编辑地址
                PersonalInfoEditActivity.startActivity(PersonalInfoActivity.this,
                        PersonalInfoEditActivity.TYPE_ADDRESS,
                        addressTv.getText().toString());
                break;
            case R.id.ll_personal_info_email://编辑邮箱
                PersonalInfoEditActivity.startActivity(PersonalInfoActivity.this,
                        PersonalInfoEditActivity.TYPE_EMAIL,
                        emailTv.getText().toString());
                break;
        }

    }

    @Override
    public Bitmap getAvatar() {
        return null;
    }

    @Override
    public void setName(String name) {
        nameTv.setText(name);
    }

    @Override
    public void setGender(String gender) {
        genderTv.setText(gender);
    }

    @Override
    public void setPhone(String name) {
        phoneTv.setText(name);
    }

    @Override
    public void setAddress(String address) {
        addressTv.setText(address);
    }

    @Override
    public void setEmail(String email) {
        emailTv.setText(email);
    }


    public void initView() {
        avatarRlayout = (RelativeLayout) findViewById(R.id.rl_personal_info_avatar);
        nameLlayout = (LinearLayout) findViewById(R.id.ll_personal_info_name);
        genderLlayout = (LinearLayout) findViewById(R.id.ll_personal_info_gender);
//        phoneLlayout = (LinearLayout) findViewById(R.id.ll_personal_info_phone);
        addressLlayout = (LinearLayout) findViewById(R.id.ll_personal_info_address);
        emailLlayout = (LinearLayout) findViewById(R.id.ll_personal_info_email);

        avatarImg = (ImageView) findViewById(R.id.iv_personal_info_avatar);
        nameTv = (TextView) findViewById(R.id.tv_personal_info_name);
        genderTv = (TextView) findViewById(R.id.tv_personal_info_gender);
        phoneTv = (TextView) findViewById(R.id.tv_personal_info_phone);
        addressTv = (TextView) findViewById(R.id.tv_personal_info_address);
        emailTv = (TextView) findViewById(R.id.tv_personal_info_email);

        avatarRlayout.setOnClickListener(this);
        nameLlayout.setOnClickListener(this);
        genderLlayout.setOnClickListener(this);
        addressLlayout.setOnClickListener(this);
        emailLlayout.setOnClickListener(this);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, PersonalInfoActivity.class));
    }
}
