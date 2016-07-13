package bearkid.com.bearkiddiaryfamily.ui.view.chatwidget;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.ui.view.chatwidget.ChatExtendMenu.ChatExtendMenuItemClickListener;

/**
 * 聊天界面底部菜单栏
 */
public class ChatInputMenu extends LinearLayout {
    protected LayoutInflater layoutInflater;
    protected FrameLayout primaryMenuContainer;
    protected FrameLayout emojiconMenuContainer;
    protected FrameLayout extendMenuContainer;
    protected ChatPrimaryMenu primaryMenu;
    protected ChatEmojiconMenu emojiconMenu;
    protected ChatExtendMenu extendMenu;

    private Handler handler = new Handler();
    private ChatInputMenuListener listener;
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
        emojiconMenuContainer = (FrameLayout) findViewById(R.id.emojicon_menu_container);
        extendMenuContainer = (FrameLayout) findViewById(R.id.extend_menu_container);

        // 扩展按钮栏
        extendMenu = (ChatExtendMenu) findViewById(R.id.extend_menu);


    }

    /**
     * init view 此方法需放在registerExtendMenuItem后面及setCustomEmojiconMenu，
     * setCustomPrimaryMenu(如果需要自定义这两个menu)后面
     * @param emojiconGroupList 表情组类别，传null使用easeui默认的表情
     */
    public void init(List<EmojiconGroupEntity> emojiconGroupList) {
        if(inited){
            return;
        }

        primaryMenu = (ChatPrimaryMenu) layoutInflater.inflate(R.layout.widget_chat_primary_menu_item, null);
        primaryMenuContainer.addView(primaryMenu);

        // 表情栏，没有自定义的用默认的
        if(emojiconMenu == null){
            emojiconMenu = (ChatEmojiconMenu) layoutInflater.inflate(R.layout.widget_emojicon_menu, null);
            if(emojiconGroupList == null){
                emojiconGroupList = new ArrayList<EmojiconGroupEntity>();
                emojiconGroupList.add(new EmojiconGroupEntity(R.drawable.ee_1,  Arrays.asList(EmojiconDefaultDatas.getData())));
            }
            ((ChatEmojiconMenu)emojiconMenu).init(emojiconGroupList);
        }
        emojiconMenuContainer.addView(emojiconMenu);

        processChatMenu();
        // 初始化extendmenu
        extendMenu.init();

        inited = true;
    }

    public void init(){
        init(null);
    }


    /**
     * 注册扩展菜单的item
     *
     * @param name
     *            item名字
     * @param drawableRes
     *            item背景
     * @param itemId
     *            id
     * @param listener
     *            item点击事件
     */
    public void registerExtendMenuItem(String name, int drawableRes, int itemId,
                                       ChatExtendMenuItemClickListener listener) {
        extendMenu.registerMenuItem(name, drawableRes, itemId, listener);
    }

    /**
     * 注册扩展菜单的item
     *
     * @param nameRes
     *            item名字
     * @param drawableRes
     *            item背景
     * @param itemId
     *            id
     * @param listener
     *            item点击事件
     */
    public void registerExtendMenuItem(int nameRes, int drawableRes, int itemId,
                                       ChatExtendMenu.ChatExtendMenuItemClickListener listener) {
        extendMenu.registerMenuItem(nameRes, drawableRes, itemId, listener);
    }


    protected void processChatMenu() {
        // 发送消息栏
        primaryMenu.setChatPrimaryMenuListener(new ChatPrimaryMenu.ChatPrimaryMenuListener() {

            @Override
            public void onSendBtnClicked(String content) {
                if (listener != null)
                    listener.onSendMessage(content);
            }

            @Override
            public void onToggleExtendClicked() {
                toggleMore();
            }

            @Override
            public void onToggleEmojiconClicked() {
                toggleEmojicon();
            }

            @Override
            public void onEditTextClicked() {
                hideExtendMenuContainer();
            }

        });

        // emojicon menu
        emojiconMenu.setEmojiconMenuListener(new ChatEmojiconMenu.EmojiconMenuListener() {

            @Override
            public void onExpressionClicked(Emojicon emojicon) {
                if(emojicon.getType() != Emojicon.Type.BIG_EXPRESSION){
                    if(emojicon.getEmojiText() != null){
                        primaryMenu.onEmojiconInputEvent(EmojiconUtils.getSmiledText(context,emojicon.getEmojiText()));
                    }
                }else{
                    if(listener != null){
                        listener.onBigExpressionClicked(emojicon);
                    }
                }
            }

            @Override
            public void onDeleteImageClicked() {
                primaryMenu.onEmojiconDeleteEvent();
            }
        });

    }

    /**
     * 显示或隐藏图标按钮页
     *
     */
    protected void toggleMore() {
        if (extendMenuContainer.getVisibility() == View.GONE) {
            hideKeyboard();
            handler.postDelayed(new Runnable() {
                public void run() {
                    extendMenuContainer.setVisibility(View.VISIBLE);
                    extendMenu.setVisibility(View.VISIBLE);
                    emojiconMenu.setVisibility(View.GONE);
                }
            }, 50);
        } else {
            if (emojiconMenu.getVisibility() == View.VISIBLE) {
                emojiconMenu.setVisibility(View.GONE);
                extendMenu.setVisibility(View.VISIBLE);
            } else {
                extendMenuContainer.setVisibility(View.GONE);
            }

        }

    }

    /**
     * 显示或隐藏表情页
     */
    protected void toggleEmojicon() {
        if (extendMenuContainer.getVisibility() == View.GONE) {
            hideKeyboard();
            handler.postDelayed(new Runnable() {
                public void run() {
                    extendMenuContainer.setVisibility(View.VISIBLE);
                    extendMenu.setVisibility(View.GONE);
                    emojiconMenu.setVisibility(View.VISIBLE);
                }
            }, 50);
        } else {
            if (emojiconMenu.getVisibility() == View.VISIBLE) {
                extendMenuContainer.setVisibility(View.GONE);
                emojiconMenu.setVisibility(View.GONE);
            } else {
                extendMenu.setVisibility(View.GONE);
                emojiconMenu.setVisibility(View.VISIBLE);
            }

        }
    }

    /**
     * 隐藏软键盘
     */
    private void hideKeyboard() {
        primaryMenu.hideKeyboard();
    }

    /**
     * 隐藏整个扩展按钮栏(包括表情栏)
     */
    public void hideExtendMenuContainer() {
        extendMenu.setVisibility(View.GONE);
        emojiconMenu.setVisibility(View.GONE);
        extendMenuContainer.setVisibility(View.GONE);
        primaryMenu.onExtendMenuContainerHide();
    }

    /**
     * 系统返回键被按时调用此方法
     *
     * @return 返回false表示返回键时扩展菜单栏时打开状态，true则表示按返回键时扩展栏是关闭状态<br/>
     *         如果返回时打开状态状态，会先关闭扩展栏再返回值
     */
    public boolean onBackPressed() {
        if (extendMenuContainer.getVisibility() == View.VISIBLE) {
            hideExtendMenuContainer();
            return false;
        } else {
            return true;
        }

    }


    public void setChatInputMenuListener(ChatInputMenuListener listener) {
        this.listener = listener;
    }

    public interface ChatInputMenuListener {
        /**
         * 发送消息按钮点击
         *
         * @param content
         *            文本内容
         */
        void onSendMessage(String content);

        /**
         * 大表情被点击
         * @param emojicon
         */
        void onBigExpressionClicked(Emojicon emojicon);

        /**
         * 长按说话按钮touch事件
         * @param v
         * @param event
         * @return
         */
        boolean onPressToSpeakBtnTouch(View v, MotionEvent event);
    }
}
