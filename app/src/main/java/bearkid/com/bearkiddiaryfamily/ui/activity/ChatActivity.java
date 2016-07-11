package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import bearkid.com.bearkiddiaryfamily.R;

public class ChatActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }
    
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ChatActivity.class);
        context.startActivity(intent);
    }
}
