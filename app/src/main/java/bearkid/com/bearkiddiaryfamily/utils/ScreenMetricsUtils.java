package bearkid.com.bearkiddiaryfamily.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import bearkid.com.bearkiddiaryfamily.ui.activity.BaseActivity;

/**
 * Created by admin on 2016/8/23.
 */
public class ScreenMetricsUtils {

    /**
     *
     * @param context
     * @return 屏幕大小
     */
    public static DisplayMetrics getScreenMetrics(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    public static float dp2px(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    /**
     * 获取屏幕密度
     *
     * @return
     */
    public static float getDensity(BaseActivity activity) {
        float density;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        density = displayMetrics.density;
        return density;
    }
}
