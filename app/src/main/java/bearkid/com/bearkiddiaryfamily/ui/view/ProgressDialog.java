package bearkid.com.bearkiddiaryfamily.ui.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import bearkid.com.bearkiddiaryfamily.R;

/**
 * Created by admin on 2016/9/7.
 */
public class ProgressDialog {

    public static AlertDialog build(Context context) {
        return new AlertDialog
                .Builder(context)
                .setView(LayoutInflater.from(context).inflate(R.layout.dialog_progress_bar, null))
                .create();
    }
}
