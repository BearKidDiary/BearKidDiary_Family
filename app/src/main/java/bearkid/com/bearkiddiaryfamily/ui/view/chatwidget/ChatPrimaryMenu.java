package bearkid.com.bearkiddiaryfamily.ui.view.chatwidget;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import bearkid.com.bearkiddiaryfamily.R;

/**
 * Created by admin on 2016/7/11.
 * 输入框主界面
 */
public class ChatPrimaryMenu extends RelativeLayout implements OnClickListener{
    private EditText editText;
    private RelativeLayout edittext_layout;
    private View buttonSend;
    private ImageView faceNormal;
    private ImageView faceChecked;
    private Button buttonMore;
    private RelativeLayout faceLayout;
    private Context context;

    private ChatPrimaryMenuListener listener;
    protected Activity activity;
    protected InputMethodManager inputManager;

    public ChatPrimaryMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public ChatPrimaryMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatPrimaryMenu(Context context) {
        super(context);
        init(context, null);
    }

    private void init(final Context context, AttributeSet attrs) {
        this.context = context;
        this.activity = (Activity) context;
        inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        LayoutInflater.from(context).inflate(R.layout.widget_chat_primary_menu, this);
        editText = (EditText) findViewById(R.id.cpm_sendmessage);
        edittext_layout = (RelativeLayout) findViewById(R.id.cpm_edittext_layout);
        buttonSend = findViewById(R.id.cpm_btn_send);
        faceNormal = (ImageView) findViewById(R.id.cpm_face_normal);
        faceChecked = (ImageView) findViewById(R.id.cpm_face_checked);
        faceLayout = (RelativeLayout) findViewById(R.id.cpm_face);
        buttonMore = (Button) findViewById(R.id.cpm_btn_more);

        edittext_layout.setBackgroundResource(R.drawable.chat_input_bar_bg_normal);
        buttonSend.setOnClickListener(this);
        buttonMore.setOnClickListener(this);
        faceLayout.setOnClickListener(this);
        editText.setOnClickListener(this);
        editText.requestFocus();

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    edittext_layout.setBackgroundResource(R.drawable.chat_input_bar_bg_active);
                } else {
                    edittext_layout.setBackgroundResource(R.drawable.chat_input_bar_bg_normal);
                }

            }
        });
        // 监听文字框
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    buttonMore.setVisibility(View.GONE);
                    buttonSend.setVisibility(View.VISIBLE);
                } else {
                    buttonMore.setVisibility(View.VISIBLE);
                    buttonSend.setVisibility(View.GONE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    /**
     * 表情输入
     * @param emojiContent
     */
    public void onEmojiconInputEvent(CharSequence emojiContent){
        editText.append(emojiContent);
    }

    /**
     * 表情删除
     */
    public void onEmojiconDeleteEvent(){
        if (!TextUtils.isEmpty(editText.getText())) {
            KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
            editText.dispatchKeyEvent(event);
        }
    }

    /**
     * 点击事件
     * @param view
     */
    @Override
    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.cpm_btn_send) {
            if(listener != null){
                String s = editText.getText().toString();
                editText.setText("");
                listener.onSendBtnClicked(s);
            }
        } else if (id == R.id.cpm_btn_more) {
            edittext_layout.setVisibility(View.VISIBLE);
            showNormalFaceImage();
            if(listener != null)
                listener.onToggleExtendClicked();
        } else if (id == R.id.cpm_sendmessage) {
            edittext_layout.setBackgroundResource(R.drawable.chat_input_bar_bg_active);
            faceNormal.setVisibility(View.VISIBLE);
            faceChecked.setVisibility(View.INVISIBLE);
            if(listener != null)
                listener.onEditTextClicked();
        } else if (id == R.id.cpm_face) {
            toggleFaceImage();
            if(listener != null){
                listener.onToggleEmojiconClicked();
            }
        } else {
        }
    }

    protected void toggleFaceImage(){
        if(faceNormal.getVisibility() == View.VISIBLE){
            showSelectedFaceImage();
        }else{
            showNormalFaceImage();
        }
    }

    private void showNormalFaceImage(){
        faceNormal.setVisibility(View.VISIBLE);
        faceChecked.setVisibility(View.INVISIBLE);
    }

    private void showSelectedFaceImage(){
        faceNormal.setVisibility(View.INVISIBLE);
        faceChecked.setVisibility(View.VISIBLE);
    }

    public void onExtendMenuContainerHide() {
        showNormalFaceImage();
    }

    public void hideKeyboard() {
        if (activity.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (activity.getCurrentFocus() != null)
                inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void setChatPrimaryMenuListener(ChatPrimaryMenuListener listener) {
        this.listener = listener;
    }

    public interface ChatPrimaryMenuListener{
        /**
         * 发送按钮点击事件
         * @param content 发送内容
         */
        void onSendBtnClicked(String content);

        /**
         * 隐藏或显示扩展menu按钮点击点击事件
         */
        void onToggleExtendClicked();

        /**
         * 隐藏或显示表情栏按钮点击事件
         */
        void onToggleEmojiconClicked();

        /**
         * 文字输入框点击事件
         */
        void onEditTextClicked();

    }
}
