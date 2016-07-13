package bearkid.com.bearkiddiaryfamily.ui.view.chatwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;

/**
 * Created by admin on 2016/7/11.
 */
public class ChatEmojiconMenu extends LinearLayout{
    protected EmojiconMenuListener listener;


    private int emojiconColumns;
    private int bigEmojiconColumns;
    private final int defaultBigColumns = 4;
    private final int defaultColumns = 7;
    private EmojiconScrollTabBar tabBar;
    private EmojiconIndicatorView indicatorView;
    private EmojiconPagerView pagerView;

    private List<EmojiconGroupEntity> emojiconGroupList = new ArrayList<EmojiconGroupEntity>();

    public ChatEmojiconMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public ChatEmojiconMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ChatEmojiconMenu(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.widget_emojicon, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EmojiconMenu);
        emojiconColumns = ta.getInt(R.styleable.EmojiconMenu_emojiconColumns, defaultColumns);
        bigEmojiconColumns = ta.getInt(R.styleable.EmojiconMenu_bigEmojiconRows, defaultBigColumns);
        ta.recycle();

        pagerView = (EmojiconPagerView) findViewById(R.id.pager_view);
        indicatorView = (EmojiconIndicatorView) findViewById(R.id.indicator_view);
        tabBar = (EmojiconScrollTabBar) findViewById(R.id.tab_bar);

    }

    public void init(List<EmojiconGroupEntity> groupEntities){
        if(groupEntities == null || groupEntities.size() == 0){
            return;
        }
        for(EmojiconGroupEntity groupEntity : groupEntities){
            emojiconGroupList.add(groupEntity);
            tabBar.addTab(groupEntity.getIcon());
        }

        pagerView.setPagerViewListener(new mEmojiconPagerViewListener());
        pagerView.init(emojiconGroupList, emojiconColumns,bigEmojiconColumns);

        tabBar.setTabBarItemClickListener(new EmojiconScrollTabBar.ScrollTabBarItemClickListener() {

            @Override
            public void onItemClick(int position) {
                pagerView.setGroupPostion(position);
            }
        });

    }


    /**
     * 添加表情组
     * @param groupEntity
     */
    public void addEmojiconGroup(EmojiconGroupEntity groupEntity){
        emojiconGroupList.add(groupEntity);
        pagerView.addEmojiconGroup(groupEntity, true);
        tabBar.addTab(groupEntity.getIcon());
    }

    /**
     * 添加一系列表情组
     * @param groupEntitieList
     */
    public void addEmojiconGroup(List<EmojiconGroupEntity> groupEntitieList){
        for(int i= 0; i < groupEntitieList.size(); i++){
            EmojiconGroupEntity groupEntity = groupEntitieList.get(i);
            emojiconGroupList.add(groupEntity);
            pagerView.addEmojiconGroup(groupEntity, i == groupEntitieList.size()-1 ? true : false);
            tabBar.addTab(groupEntity.getIcon());
        }

    }

    /**
     * 移除表情组
     * @param position
     */
    public void removeEmojiconGroup(int position){
        emojiconGroupList.remove(position);
        pagerView.removeEmojiconGroup(position);
        tabBar.removeTab(position);
    }

    public void setTabBarVisibility(boolean isVisible){
        if(!isVisible){
            tabBar.setVisibility(View.GONE);
        }else{
            tabBar.setVisibility(View.VISIBLE);
        }
    }


    private class mEmojiconPagerViewListener implements EmojiconPagerView.EmojiconPagerViewListener {

        @Override
        public void onPagerViewInited(int groupMaxPageSize, int firstGroupPageSize) {
            indicatorView.init(groupMaxPageSize);
            indicatorView.updateIndicator(firstGroupPageSize);
            tabBar.selectedTo(0);
        }

        @Override
        public void onGroupPositionChanged(int groupPosition, int pagerSizeOfGroup) {
            indicatorView.updateIndicator(pagerSizeOfGroup);
            tabBar.selectedTo(groupPosition);
        }

        @Override
        public void onGroupInnerPagePostionChanged(int oldPosition, int newPosition) {
            indicatorView.selectTo(oldPosition, newPosition);
        }

        @Override
        public void onGroupPagePostionChangedTo(int position) {
            indicatorView.selectTo(position);
        }

        @Override
        public void onGroupMaxPageSizeChanged(int maxCount) {
            indicatorView.updateIndicator(maxCount);
        }

        @Override
        public void onDeleteImageClicked() {
            if(listener != null){
                listener.onDeleteImageClicked();
            }
        }

        @Override
        public void onExpressionClicked(Emojicon emojicon) {
            if(listener != null){
                listener.onExpressionClicked(emojicon);
            }
        }

    }

    /**
     * 设置回调监听
     * @param listener:表情监听
     */
    public void setEmojiconMenuListener(EmojiconMenuListener listener){
        this.listener = listener;
    }

    public interface EmojiconMenuListener{
        /**
         * 表情被点击
         * @param chatEmoji:表情类
         */
        void onExpressionClicked(Emojicon chatEmoji);
        /**
         * 删除按钮被点击
         */
        void onDeleteImageClicked();
    }
}
