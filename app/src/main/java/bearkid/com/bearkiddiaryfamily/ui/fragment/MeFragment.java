package bearkid.com.bearkiddiaryfamily.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.QRCodeModel;
import bearkid.com.bearkiddiaryfamily.ui.activity.ContactsListActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.KidInfoActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.PersonalInfoActivity;
import bearkid.com.bearkiddiaryfamily.ui.view.CircleImageview;
import bearkid.com.bearkiddiaryfamily.utils.ContactsType;

/**
 * Created by admin on 2016/7/7.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {

    private Context context;
    private LinearLayout mychildrenLlayout;
    private CircleImageview avatarImg;

    private RelativeLayout scanRlayout;//扫一扫
    private RelativeLayout qrRlayout;//二维码
    private RelativeLayout contactsRlayout;//联系人
    private RelativeLayout settingRlayout;//设置

    private ImageView addImg;
    private Intent intent;

    private static final int requestCode = 200;//扫一扫二维码的请求码

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_me, container, false);
        context = getContext();

        initView(view);

        return view;
    }

    /**
     * 扫一扫，我二维码，联系人，设置，的点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_me_avatar:
                Log.d("avatarImg", "点击");
                intent = new Intent(context, PersonalInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.img_me_add:
                addChild();
                break;
            case R.id.rlayout_me_scan:
                QRCodeModel.scanQRCode(this, requestCode);
                break;
            case R.id.rlayout_me_qr:
                break;
            case R.id.rlayout_me_contacts:
                intent = new Intent(context, ContactsListActivity.class);
                intent.putExtra("ListType", ContactsType.CHECK);
                startActivity(intent);
                break;
            case R.id.rlayout_me_setting:
                break;
        }
    }

    @Override
    public void onActivityResult(int request, int result, Intent data) {
        if (request == requestCode && result == Activity.RESULT_OK) {
            //TODO:处理扫描后获得的内容
            Bitmap QRcode = QRCodeModel.getBitmap(data);
            String content = QRCodeModel.getContent(data);
            Log.i("zy", "扫描成功：" + content);
        } else {
            super.onActivityResult(request, result, data);
        }
    }

    /**
     * 点击加号图标，添加头像
     * 添加孩子
     */
    private void addChild() {
        CircleImageview imageview = new CircleImageview(context);
        imageview.setImageResource(R.drawable.avatar);
        float density = getDensity();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (64 * density), (int) (64 * density));
        params.leftMargin = 8;
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KidInfoActivity.startActivity(getContext());
            }
        });
        mychildrenLlayout.addView(imageview, 1, params);
    }

    /**
     * 获取屏幕密度
     *
     * @return
     */
    private float getDensity() {
        float density;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        density = displayMetrics.density;
        return density;
    }

    private void initView(View view) {
        mychildrenLlayout = (LinearLayout) view.findViewById(R.id.llayout_me_mychildren);

        scanRlayout = (RelativeLayout) view.findViewById(R.id.rlayout_me_scan);
        qrRlayout = (RelativeLayout) view.findViewById(R.id.rlayout_me_qr);
        contactsRlayout = (RelativeLayout) view.findViewById(R.id.rlayout_me_contacts);
        settingRlayout = (RelativeLayout) view.findViewById(R.id.rlayout_me_setting);
        addImg = (ImageView) view.findViewById(R.id.img_me_add);
        avatarImg = (CircleImageview) view.findViewById(R.id.img_me_avatar);

        scanRlayout.setOnClickListener(this);
        qrRlayout.setOnClickListener(this);
        contactsRlayout.setOnClickListener(this);
        settingRlayout.setOnClickListener(this);
        addImg.setOnClickListener(this);
        avatarImg.setOnClickListener(this);
    }

}
