package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import bearkid.com.bearkiddiaryfamily.R;

public class AddBodyDataActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_body_data);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, AddBodyDataActivity.class));
    }
}
