package bearkid.com.bearkiddiaryfamily.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.ui.view.CircleImageview;

/**
 * Created by admin on 2016/7/7.
 */
public class MeFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private LinearLayout mychildrenLlayout;

    private RelativeLayout scanRlayout;//扫一扫
    private RelativeLayout qrRlayout;//二维码
    private RelativeLayout contactsRlayout;//联系人
    private RelativeLayout settingRlayout;//设置

    private ImageView addImg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        switch (v.getId()){
            case R.id.img_me_add:
                addChild();
                break;
            case R.id.rlayout_me_scan:
                Toast.makeText(context,"扫一扫",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rlayout_me_qr:
                break;
            case R.id.rlayout_me_contacts:
                break;
            case R.id.rlayout_me_setting:
                break;
        }
    }

    /**
     * 点击加号图标，添加头像
     * 添加孩子
     */
    private void addChild(){
        CircleImageview imageview = new CircleImageview(context);
        imageview.setImageResource(R.drawable.avatar);
        float density = getDensity();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)(64 * density),(int) (64 * density));
        params.leftMargin = 8;
        mychildrenLlayout.addView(imageview,0,params);
    }

    /**
     * 获取屏幕密度
     * @return
     */
    private float getDensity(){
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

        scanRlayout.setOnClickListener(this);
        qrRlayout.setOnClickListener(this);
        contactsRlayout.setOnClickListener(this);
        settingRlayout.setOnClickListener(this);
        addImg.setOnClickListener(this);
    }
}