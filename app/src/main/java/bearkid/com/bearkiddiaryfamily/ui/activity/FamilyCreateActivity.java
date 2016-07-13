package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.CheckBox;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.ui.view.IconButton;

public class FamilyCreateActivity extends BaseActivity {

    private ImageView iv_back;
    private IconButton ib_add;
    private ButtonFlat btn_create;
    private EditText et_name;
    private RadioButton rb_father, rb_mother;
    private RecyclerView rv_members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_create);

        initView();
    }

    private final void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        btn_create = (ButtonFlat) findViewById(R.id.btn_family_create);
        ib_add = (IconButton) findViewById(R.id.ib_family_add_member);
        et_name = (EditText) findViewById(R.id.et_family_name);
        rb_father = (RadioButton) findViewById(R.id.rb_family_father);
        rb_mother = (RadioButton) findViewById(R.id.rb_family_mother);
        rv_members = (RecyclerView) findViewById(R.id.rv_family_member);

        iv_back.setOnClickListener(v -> finish());
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, FamilyCreateActivity.class));
    }
}
