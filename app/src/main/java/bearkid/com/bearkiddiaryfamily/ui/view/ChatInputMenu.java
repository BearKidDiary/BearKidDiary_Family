package bearkid.com.bearkiddiaryfamily.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import bearkid.com.bearkiddiaryfamily.R;

/**
 * 聊天界面底部菜单栏
 */
public class ChatInputMenu extends LinearLayout {
    protected LayoutInflater layoutInflater;
    protected FrameLayout primaryMenuContainer;
    protected FrameLayout emojiMenuContainer;
    protected FrameLayout extendMenuContainer;
    protected ChatPrimaryMenu primaryMenu;
    protected ChatEmojiMenu emojiMenu;
    protected ChatExtendMenu extendMenu;


    private Context context;
    private boolean inited;

    public ChatInputMenu(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public ChatInputMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ChatInputMenu(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.widget_chat_input_menu, this);
        primaryMenuContainer = (FrameLayout) findViewById(R.id.primary_menu_container);
        emojiMenuContainer = (FrameLayout) findViewById(R.id.emoji_menu_container);
        extendMenuContainer = (FrameLayout) findViewById(R.id.extend_menu_container);
    }

    public void initView() {
        if (inited) {
            return;
        }
        //primaryMenu = (ChatPrimaryMenu) layoutInflater.inflate(R.layout.ease_layout_chat_primary_menu, null);
        //primaryMenuContainer.addView(primaryMenu);
        //emojiMenu = (ChatEmojiMenu)  layoutInflater.inflate(R.layout.ease_layout_chat_primary_menu, null);
        //emojiMenuContainer.addView(emojiMenu);

        inited = true;
    }
}
