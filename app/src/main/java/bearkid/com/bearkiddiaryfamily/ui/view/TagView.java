package bearkid.com.bearkiddiaryfamily.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import bearkid.com.bearkiddiaryfamily.R;

/**
 * 家庭成员信息页面的身份标签
 */
public class TagView extends TextView {

    private final static int background = R.drawable.tag;
    private final Matrix matrix = new Matrix();
    private Bitmap bg;

    public TagView(Context context) {
        super(context);
        init();
    }

    public TagView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TagView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    Paint paint = new Paint();

    private final void init() {
        bg = BitmapFactory.decodeResource(getResources(), background);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        matrix.setScale(w * 1f / bg.getWidth(), h * 1f / bg.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bg, matrix, paint);
        super.onDraw(canvas);
    }
}
