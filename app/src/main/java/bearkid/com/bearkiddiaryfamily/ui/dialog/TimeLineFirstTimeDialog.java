package bearkid.com.bearkiddiaryfamily.ui.dialog;


import android.os.Bundle;
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
 * @author zy
 */
public class TimeLineFirstTimeDialog extends TimeLineDialog {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_timeline_first_time, container, false);
        initView(view);
        return view;
    }

    private ButtonFlat btn_ok;
    private IconButton btn_pic;
    private EditText et_content;

    private void initView(View v) {
        btn_ok = (ButtonFlat) v.findViewById(R.id.btn_timeline_ok);
        btn_pic = (IconButton) v.findViewById(R.id.ib_timeline_choose_pic);
        et_content = (EditText) v.findViewById(R.id.et_timeline_content);
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
