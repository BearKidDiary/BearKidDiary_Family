package bearkid.com.bearkiddiaryfamily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.ui.view.chatwidget.ChatInputMenu;

public class ChatActivity extends BaseActivity implements View.OnClickListener{

    protected ChatInputMenu inputMenu;
    protected InputMethodManager inputManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initView();
    }

    private void initView() {
        ImageView back = (ImageView) findViewById(R.id.activity_chat_back);
        back.setOnClickListener(this);

        inputMenu = (ChatInputMenu) findViewById(R.id.input_menu);
        inputMenu.init(null);

        inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ChatActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_chat_back:
                finish();
                break;
            default:
                break;
        }
    }
}
