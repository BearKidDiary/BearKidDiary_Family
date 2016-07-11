package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gc.materialdesign.views.Card;

import bearkid.com.bearkiddiaryfamily.R;

public class CourseActivity extends AppCompatActivity {

    private ImageView iv_back;
    private TextView tv_name, tv_phone, tv_course_desc, tv_course_time, tv_course_area;
    private Card card_teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        initView();
    }

    private final void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_course_area = (TextView) findViewById(R.id.tv_course_area);
        tv_course_desc = (TextView) findViewById(R.id.tv_course_desc);
        tv_course_time = (TextView) findViewById(R.id.tv_course_time);
        tv_name = (TextView) findViewById(R.id.tv_course_teacher_name);
        tv_phone = (TextView) findViewById(R.id.tv_course_teacher_phone);
        card_teacher = (Card) findViewById(R.id.card_course_teacher);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        card_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, CourseActivity.class));
    }
}
