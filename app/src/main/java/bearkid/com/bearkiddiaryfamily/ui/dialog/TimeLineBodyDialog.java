package bearkid.com.bearkiddiaryfamily.ui.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gc.materialdesign.views.ButtonFlat;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.ui.view.IconButton;

/**
 * Created by admin on 2016/7/15.
 */
public class TimeLineBodyDialog extends TimeLineDialog {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_timeline_body, container, false);
        initView(view);
        return view;
    }

    private ButtonFlat btn_ok;
    private EditText et_height, et_weight, et_visionLeft, et_visionRight;

    private void initView(View v) {
        btn_ok = (ButtonFlat) v.findViewById(R.id.btn_timeline_ok);
        et_height = (EditText) v.findViewById(R.id.et_body_height);
        et_weight = (EditText) v.findViewById(R.id.et_body_weight);
        et_visionLeft = (EditText) v.findViewById(R.id.et_body_vision_left);
        et_visionRight = (EditText) v.findViewById(R.id.et_body_vision_right);
    }

    @Override
    protected ButtonFlat getPositiveButton() {
        return btn_ok;
    }

    @Override
    protected IconButton getChoosePictureButton() {
        return null;
    }

    @Override
    public String getContent() {
        StringBuilder sb = new StringBuilder("新增身体数据——");
        String data;
        if (!(data = et_height.getText().toString()).equals("")) {
            sb.append(" 身高: " + data);
        }
        if (!(data = et_weight.getText().toString()).equals("")) {
            sb.append(" 体重: " + data);
        }
        if (!(data = et_visionLeft.getText().toString()).equals("")) {
            sb.append(" 左眼视力: " + data);
        }
        if (!(data = et_visionRight.getText().toString()).equals("")) {
            sb.append(" 右眼视力: " + data);
        }
        return sb.toString();
    }
}
