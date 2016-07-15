package bearkid.com.bearkiddiaryfamily.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gc.materialdesign.utils.Utils;
import com.gc.materialdesign.views.AutoHideButtonFloat;
import com.gc.materialdesign.views.ButtonFloat;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.Arrays;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.ui.activity.ContactsListActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.DataAnalysisActivity;
import bearkid.com.bearkiddiaryfamily.ui.view.IconButton;
import bearkid.com.bearkiddiaryfamily.ui.view.RefreshRecyclerView;

/**
 * 时间轴界面
 */
public class TimeLineFragment extends BaseFragment {

    private List<String> childName = Arrays.asList("王小宝", "王小帅", "丫丫");
    private String[] menuItems = new String[]{"查看详情", "添加孩子", "编辑家庭成员"};
    private final float textSize = 20f;
    private float showButtonX, hideButtonX;

    private final static int SHOW_FLOAT_BUTTON = 1;
    private final static int HIDE_FLOAT_BUTTON = 2;
    private Handler handler = new Handler(new Handler.Callback() {//处理当列表滑动时的浮动按钮的显示和隐藏
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == SHOW_FLOAT_BUTTON) {
                ObjectAnimator.ofFloat(btn_search, "x", btn_search.getX(), showButtonX)
                        .setDuration(300).start();
            } else if (msg.what == HIDE_FLOAT_BUTTON) {
                ObjectAnimator.ofFloat(btn_search, "x", btn_search.getX(), hideButtonX)
                        .setDuration(300).start();
            }
            return false;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_line, container, false);
        initView(view);
        return view;
    }

    private final void initView(View v) {
        ib_menu = (IconButton) v.findViewById(R.id.ib_child_menu);
        btn_search = (ButtonFloat) v.findViewById(R.id.btn_timeline_search);
        spinner_name = (AppCompatSpinner) v.findViewById(R.id.spinner_child_name);
        rv_timeline = (RefreshRecyclerView) v.findViewById(R.id.rv_timeline);

        //点击名字时的下拉列表，可选择当前用户的不同孩子
        spinner_name.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, childName) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(getContext());
                tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tv.setText(childName.get(position));
                Log.i("zy", "spinner" + childName.get(position) + " pos:" + position);
                tv.setTextSize(textSize);
                tv.setTextColor(0xffffffff);
                return tv;
            }
        });

        //名字旁边的菜单按钮 包括"查看详情", "添加孩子", "编辑家庭成员"
        ib_menu.setOnClickListener(view -> popupMenu.show());
        popupMenu = new PopupMenu(getContext(), ib_menu);
        for (int i = 0; i < menuItems.length; i++)
            popupMenu.getMenu().add(menuItems[i]);
        popupMenu.setOnMenuItemClickListener(item -> {
            //TODO:根据菜单item的title来跳转到其他页面
            if (item.getTitle().toString().equals("查看详情")){
                DataAnalysisActivity.startActivity(getContext());
            }
            return true;
        });

        //时间轴列表
        rv_timeline.setAdapter(new TimeLineAdapter());
        rv_timeline.setOnRefreshListener(top -> {
            handler.postDelayed(() -> rv_timeline.setRefreshing(false), 2000);
        });

        //当时间轴往下滚时隐藏搜索按钮 向上划时显示
        btn_search.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                showButtonX = btn_search.getX();
                btn_search.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        final WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        hideButtonX = manager.getDefaultDisplay().getWidth();
        rv_timeline.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {//停下来了
                    Message msg = new Message();
                    msg.what = SHOW_FLOAT_BUTTON;
                    handler.sendMessageDelayed(msg, 700);
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {//拖动列表
                    handler.removeMessages(SHOW_FLOAT_BUTTON);
                    Message msg = new Message();
                    msg.what = HIDE_FLOAT_BUTTON;
                    handler.sendMessage(msg);
                }
            }
        });
    }

    //控件
    private RefreshRecyclerView rv_timeline;
    private ButtonFloat btn_search;
    private AppCompatSpinner spinner_name;
    private IconButton ib_menu;
    private PopupMenu popupMenu;

    class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder> {

        @Override
        public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_time_line, parent, false);
            return new TimeLineViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return 13;
        }

        class TimeLineViewHolder extends RecyclerView.ViewHolder {

            public TimeLineViewHolder(View view) {
                super(view);
            }
        }
    }
}
