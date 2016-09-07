package bearkid.com.bearkiddiaryfamily.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.bean.Height;
import bearkid.com.bearkiddiaryfamily.model.bean.Vision;
import bearkid.com.bearkiddiaryfamily.model.bean.Weight;
import bearkid.com.bearkiddiaryfamily.utils.DateTimePickerUtil;

/**
 * Created by YarenChoi on 2016/7/20.
 * 孩子身体数据
 * 身高
 * 体重
 * 视力
 */
public class BodyDataView extends LinearLayout {
    private static final int HEIGHT = 0;
    private static final int WEIGHT = 1;
    private static final int VISION = 2;

    int type;

    public BodyDataView(Context context) {
        this(context, null);
    }

    public BodyDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseStyle(context, attrs);
    }

    public BodyDataView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseStyle(context, attrs);
    }

    void parseStyle(Context context, AttributeSet attrs) {
        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.BodyDataView, 0, 0);
        type = attr.getInt(R.styleable.BodyDataView_type, 0);
        attr.recycle();
    }

    public void clear() {
        this.removeAllViewsInLayout();
    }

    public void show(List data) {
        if ( data == null) {
            return;
        }
        switch (type) {
            case HEIGHT:
                for (Object object:data) {
                    View content = inflate(this.getContext(), R.layout.item_body_data, null);
                    TextView tv_data1 = (TextView) content.findViewById(R.id.tv_data_1);
                    TextView tv_data2 = (TextView) content.findViewById(R.id.tv_data_2);
                    TextView tv_date = (TextView) content.findViewById(R.id.tv_date);
                    String value = ((Height)object).getHheight() + "m";
                    tv_data1.setText(value);
                    tv_data2.setVisibility(GONE);
                    tv_date.setText(DateTimePickerUtil.getFormatDate(((Height)object).getHtime()));
                    this.addView(content);
                }
                break;
            case WEIGHT:
                for (Object object:data) {
                    View content = inflate(this.getContext(), R.layout.item_body_data, null);
                    TextView tv_data1 = (TextView) content.findViewById(R.id.tv_data_1);
                    TextView tv_data2 = (TextView) content.findViewById(R.id.tv_data_2);
                    TextView tv_date = (TextView) content.findViewById(R.id.tv_date);
                    String value = ((Weight)object).getWweight() + "kg";
                    tv_data1.setText(value);
                    tv_data2.setVisibility(GONE);
                    tv_date.setText(DateTimePickerUtil.getFormatDate(((Weight)object).getWtime()));
                    this.addView(content);
                }
                break;
            case VISION:
                for (Object object:data) {
                    View content = inflate(this.getContext(), R.layout.item_body_data, null);
                    TextView tv_data1 = (TextView) content.findViewById(R.id.tv_data_1);
                    TextView tv_data2 = (TextView) content.findViewById(R.id.tv_data_2);
                    TextView tv_date = (TextView) content.findViewById(R.id.tv_date);
                    String leftValue = "左眼" + ((Vision)object).getVleft();
                    String rightValue = "右眼" + ((Vision)object).getVright();
                    tv_data1.setText(leftValue);
                    tv_data2.setText(rightValue);
                    tv_date.setText(DateTimePickerUtil.getFormatDate(((Vision)object).getVtime()));
                    this.addView(content);
                }
                break;
            default:
                break;
        }
    }

}
