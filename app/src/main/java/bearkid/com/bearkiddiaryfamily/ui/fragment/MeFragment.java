package bearkid.com.bearkiddiaryfamily.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.adapter.ViewHolderAdapter;
import bearkid.com.bearkiddiaryfamily.model.QRCodeModel;
import bearkid.com.bearkiddiaryfamily.model.bean.Kid;
import bearkid.com.bearkiddiaryfamily.presenter.MePresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.AddKidActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.ContactsListActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.KidInfoActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.MainActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.MyQRCodeActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.PersonalInfoActivity;
import bearkid.com.bearkiddiaryfamily.ui.fragment.ifragment.IMeFragment;
import bearkid.com.bearkiddiaryfamily.ui.view.CircleImageview;
import bearkid.com.bearkiddiaryfamily.utils.ContactsType;
import bearkid.com.bearkiddiaryfamily.utils.ScreenMetricsUtils;

/**
 * Created by admin on 2016/7/7.
 */
public class MeFragment extends BaseFragment implements IMeFragment, View.OnClickListener {

    private Context context;
    protected CircleImageview avatarImg;

    protected RelativeLayout scanRlayout;//扫一扫
    protected RelativeLayout qrRlayout;//二维码
    protected RelativeLayout contactsRlayout;//联系人
    protected RelativeLayout settingRlayout;//设置

    private GridView kidsGView;
    GridViewAdapter adapter;
    private List<Kid> myKids = new ArrayList<>();
    protected MePresenter presenter;

    private static final int REQUEST_QR_CODE = 200;//扫一扫二维码的请求码
    private static final int REQUEST_ADD_KID = 201;//添加孩子

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("MeFragment", "onCreateView");
        View view = inflater.inflate(R.layout.frament_me, container, false);
        context = getContext();

        initView(view);
        initPresenter();
        return view;
    }

    private void initPresenter() {
        presenter = new MePresenter(this);
        presenter.loadKidsInfo();
    }

    /**
     * 扫一扫，我二维码，联系人，设置，的点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_me_avatar:
                PersonalInfoActivity.startActivity(context);
                break;
            case R.id.rlayout_me_scan:
                QRCodeModel.scanQRCode(this, REQUEST_QR_CODE);
                break;
            case R.id.rlayout_me_qr:
                MyQRCodeActivity.startActivity(context);
                break;
            case R.id.rlayout_me_contacts:
                ContactsListActivity.startActivity(context, ContactsType.CHECK);
                break;
            case R.id.rlayout_me_setting:
                break;
        }
    }

    @Override
    public void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        switch (request) {
            case REQUEST_QR_CODE:
                if (result == Activity.RESULT_OK) {
                    //TODO:处理扫描后获得的内容
                    Bitmap QRcode = QRCodeModel.getBitmap(data);
                    String content = QRCodeModel.getContent(data);
                }
                break;
            case REQUEST_ADD_KID:
                if (result == Activity.RESULT_OK) {
                    presenter.loadKidsInfo();
                }
                break;
            default:
                break;
        }
    }

    private void initView(View view) {
        scanRlayout = (RelativeLayout) view.findViewById(R.id.rlayout_me_scan);
        qrRlayout = (RelativeLayout) view.findViewById(R.id.rlayout_me_qr);
        contactsRlayout = (RelativeLayout) view.findViewById(R.id.rlayout_me_contacts);
        settingRlayout = (RelativeLayout) view.findViewById(R.id.rlayout_me_setting);
        avatarImg = (CircleImageview) view.findViewById(R.id.img_me_avatar);
        kidsGView = (GridView) view.findViewById(R.id.gv_kids);

        scanRlayout.setOnClickListener(this);
        qrRlayout.setOnClickListener(this);
        contactsRlayout.setOnClickListener(this);
        settingRlayout.setOnClickListener(this);
        avatarImg.setOnClickListener(this);

        adapter = new GridViewAdapter(context, myKids);
        kidsGView.setAdapter(adapter);
        resetGridView();
    }

    private void resetGridView() {
        int size = myKids.size() + 1;
        int length = 80;
        float density = ScreenMetricsUtils.getDensity((MainActivity) getActivity());
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        kidsGView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        kidsGView.setColumnWidth(itemWidth); // 设置列表项宽
        kidsGView.setHorizontalSpacing(5); // 设置列表项水平间距
        kidsGView.setStretchMode(GridView.NO_STRETCH);
        kidsGView.setNumColumns(size); // 设置列数量=列表集合数
    }

    @Override
    public void refreshKidList(List<Kid> kidList) {
        this.myKids.clear();
        this.myKids.addAll(kidList);
        resetGridView();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public class GridViewAdapter extends ViewHolderAdapter<GridViewAdapter.KidsViewHolder, Kid> {

        public GridViewAdapter(Context context, List<Kid> list) {
            super(context, list);
        }

        @Override
        public int getCount() {
            return myKids.size() + 1;
        }

        @Override
        public KidsViewHolder onCreateViewHolder(ViewGroup parent, int position) {
            View view = inflate(R.layout.item_kids, parent);
            return new KidsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(KidsViewHolder holder, int position) {
            if (position == getCount() - 1) {
                holder.avatar.setImageResource(R.drawable.ic_me_add);
                holder.avatar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddKidActivity.startActivity(MeFragment.this, REQUEST_ADD_KID);
                    }
                });
            } else {
//                avatar.setImageResource(getItem(position);
                holder.avatar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        KidInfoActivity.startActivity(context, myKids.get(position));
                    }
                });
            }
        }

        public class KidsViewHolder extends ViewHolderAdapter.ViewHolder {
            public CircleImageview avatar;

            public KidsViewHolder(View view) {
                super(view);
                avatar = (CircleImageview) view.findViewById(R.id.civ_kid);
            }
        }
    }

}
