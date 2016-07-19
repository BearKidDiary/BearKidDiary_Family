package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.presenter.PersonInfoPresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.iactivity.IPersonInfoView;

public class PersonInfoActivity extends BaseActivity implements IPersonInfoView, View.OnClickListener {

    private RelativeLayout avatarRlayout;
    private RelativeLayout nameRlayout;
    private RelativeLayout phoneRlayout;
    private RelativeLayout addressRlayout;
    private RelativeLayout emailRlayout;
    private TextView nameTv;
    private TextView phoneTv;
    private TextView addressTv;
    private TextView emailTv;

    private LinearLayout nameLlayout;
    private LinearLayout addressLlayout;
    private LinearLayout emailLlayout;

    private Button nameBtn;
    private Button addressBtn;
    private Button emailBtn;
    private EditText nameEt;
    private EditText addressEt;
    private EditText emailEt;

    //返回按钮
    private ImageView backImg;

    private PersonInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);

        initPresenter();
        initView();
    }

    private final void initPresenter() {
        presenter = new PersonInfoPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_title_back_personinfo:
                finish();
                break;
            case R.id.rlayout_personinfo_name:
                expandView(nameLlayout);
                break;
            case R.id.rlayout_personinfo_address:
                expandView(addressLlayout);
                break;
            case R.id.rlayout_personinfo_email:
                expandView(emailLlayout);
                break;
            case R.id.btn_personinfo_name_yes:
                if (!getEditName().equals("")){
                    presenter.update(PersonInfoPresenter.UPDATE_NAME);
                }
                expandView(nameLlayout);
                nameEt.setText("");
                break;
            case R.id.btn_personinfo_address_yes:
                if (!getEditAddress().equals("")){
                    presenter.update(PersonInfoPresenter.UPDATE_ADDRESS);
                }
                expandView(addressLlayout);
                addressEt.setText("");
                break;
            case R.id.btn_personinfo_email_yes:
                if (!getEditEmail().equals("")){
                    presenter.update(PersonInfoPresenter.UPDATE_EMAIL);
                }
                expandView(emailLlayout);
                emailEt.setText("");
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

    @Override
    public String getEditName() {
        return nameEt.getText().toString();
    }

    @Override
    public String getEditAddress() {
        return addressEt.getText().toString();
    }

    @Override
    public String getEditEmail() {
        return emailEt.getText().toString();
    }

    @Override
    public Context getViewContext() {
        return PersonInfoActivity.this;
    }


    public void initView(){
        avatarRlayout = (RelativeLayout) findViewById(R.id.rlayout_personinfo_avatar);
        nameRlayout = (RelativeLayout) findViewById(R.id.rlayout_personinfo_name);
        phoneRlayout = (RelativeLayout) findViewById(R.id.rlayout_personinfo_phone);
        addressRlayout = (RelativeLayout) findViewById(R.id.rlayout_personinfo_address);
        emailRlayout = (RelativeLayout) findViewById(R.id.rlayout_personinfo_email);
        backImg = (ImageView) findViewById(R.id.img_title_back_personinfo);
        nameTv = (TextView) findViewById(R.id.txt_personinfo_name);
        phoneTv = (TextView) findViewById(R.id.txt_personinfo_phone);
        addressTv = (TextView) findViewById(R.id.txt_personinfo_address);
        emailTv = (TextView) findViewById(R.id.txt_personinfo_email);

        nameLlayout = (LinearLayout) findViewById(R.id.ll_personinfo_edit_name);
        addressLlayout = (LinearLayout) findViewById(R.id.ll_personinfo_edit_address);
        emailLlayout = (LinearLayout) findViewById(R.id.ll_personinfo_edit_email);
        nameBtn = (Button) findViewById(R.id.btn_personinfo_name_yes);
        addressBtn = (Button) findViewById(R.id.btn_personinfo_address_yes);
        emailBtn = (Button) findViewById(R.id.btn_personinfo_email_yes);
        nameEt = (EditText) findViewById(R.id.et_personinfo_name);
        addressEt = (EditText) findViewById(R.id.et_personinfo_address);
        emailEt = (EditText) findViewById(R.id.et_personinfo_email);

        avatarRlayout.setOnClickListener(this);
        nameRlayout.setOnClickListener(this);
        phoneRlayout.setOnClickListener(this);
        addressRlayout.setOnClickListener(this);
        emailRlayout.setOnClickListener(this);
        backImg.setOnClickListener(this);
        nameBtn.setOnClickListener(this);
        addressBtn.setOnClickListener(this);
        emailBtn.setOnClickListener(this);

        presenter.init();
    }

    private void expandView(LinearLayout ll) {
        if (ll.getLayoutParams().height == 0) {
            ValueAnimator animator = ValueAnimator.ofInt(0, 200).setDuration(300);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ViewGroup.LayoutParams lp = ll.getLayoutParams();
                    lp.height = (int) valueAnimator.getAnimatedValue();
                    ll.setLayoutParams(lp);
                }
            });
            animator.start();
        } else {
            ValueAnimator animator = ValueAnimator.ofInt(200, 0).setDuration(200);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ViewGroup.LayoutParams lp = ll.getLayoutParams();
                    lp.height = (int) valueAnimator.getAnimatedValue();
                    ll.setLayoutParams(lp);
                }
            });
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    ViewGroup.LayoutParams lp = ll.getLayoutParams();
                    lp.height = 0;
                    ll.setLayoutParams(lp);
                }
            });
            animator.start();
        }
    }
}
