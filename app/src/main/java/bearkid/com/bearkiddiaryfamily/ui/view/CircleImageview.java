package bearkid.com.bearkiddiaryfamily.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by admin on 2016/7/7.
 * 1、先画一个圆
 * 2、修改Paint的图层混合模式
 * 3、最后画bitmap
 */
public class CircleImageview extends ImageView {

    public CircleImageview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageview(Context context){super(context);}
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        //获取Drawable对象
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }

        //得到图片
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        if (b == null) {
            return;
        }
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        int w = getWidth(), h = getHeight();
        int diameter = w >= h ? h : w;
        Bitmap roundBitmap = getCroppedBitmap(bitmap, diameter);
        canvas.drawBitmap(roundBitmap, 0, 0, null);
    }

    /*
     * 获取圆形图像
     */
    private Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius)
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        else
            sbmp = bmp;

        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();

        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        canvas.drawARGB(0, 0, 0, 0);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(sbmp.getWidth() / 2, sbmp.getHeight() / 2,
                sbmp.getWidth() / 2 - 6f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);
        drawCircleBorder(canvas, sbmp.getWidth() / 2 - 6f, color,
                sbmp.getWidth() / 2, sbmp.getHeight() / 2);
        return output;
    }

    private void drawCircleBorder(Canvas canvas, float radius, int color,
                                  float defaultWidth, float defaultHeight) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1.0f);
        canvas.drawCircle(defaultWidth, defaultHeight, radius, paint);
    }

}
