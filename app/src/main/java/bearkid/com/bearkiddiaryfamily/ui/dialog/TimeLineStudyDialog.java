package bearkid.com.bearkiddiaryfamily.ui.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gc.materialdesign.views.ButtonFlat;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.bean.TimeLine;
import bearkid.com.bearkiddiaryfamily.ui.view.IconButton;

/**
 * Created by admin on 2016/7/15.
 */
public class TimeLineStudyDialog extends TimeLineDialog {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_timeline_study, container, false);
        initView(view);
        return view;
    }

    private ButtonFlat btn_ok;
    private IconButton btn_pic;
    private EditText et_content;
    private ImageView iv_pic1, iv_pic2, iv_pic3;

    private void initView(View v) {
        btn_ok = (ButtonFlat) v.findViewById(R.id.btn_timeline_ok);
        btn_pic = (IconButton) v.findViewById(R.id.ib_timeline_choose_pic);
        et_content = (EditText) v.findViewById(R.id.et_timeline_content);
        iv_pic1 = (ImageView) v.findViewById(R.id.iv_timeline_pic1);
        iv_pic2 = (ImageView) v.findViewById(R.id.iv_timeline_pic2);
        iv_pic3 = (ImageView) v.findViewById(R.id.iv_timeline_pic3);
    }

    @Override
    protected void setPic1(String picPath) {
        iv_pic1.setVisibility(View.VISIBLE);
        Glide.with(this).load(picPath).into(iv_pic1);
    }

    @Override
    protected void setPic2(String picPath) {
        iv_pic2.setVisibility(View.VISIBLE);
        Glide.with(this).load(picPath).into(iv_pic2);
    }

    @Override
    protected void setPic3(String picPath) {
        iv_pic3.setVisibility(View.VISIBLE);
        Glide.with(this).load(picPath).into(iv_pic3);
    }

    @Override
    protected ButtonFlat getPositiveButton() {
        return btn_ok;
    }

    @Override
    protected IconButton getChoosePictureButton() {
        return btn_pic;
    }

    @Override
    public String getContent() {
        return et_content.getText().toString();
    }
}
