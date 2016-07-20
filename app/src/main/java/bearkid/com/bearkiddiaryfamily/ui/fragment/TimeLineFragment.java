package bearkid.com.bearkiddiaryfamily.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.nineoldandroids.animation.ObjectAnimator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import bearkid.com.bearkiddiaryfamily.model.bean.TimeLine;
import bearkid.com.bearkiddiaryfamily.model.bean.User;
import bearkid.com.bearkiddiaryfamily.presenter.TimeLinePresenter;
import bearkid.com.bearkiddiaryfamily.ui.activity.DataAnalysisActivity;
import bearkid.com.bearkiddiaryfamily.ui.activity.FamilyActivity;
import bearkid.com.bearkiddiaryfamily.ui.fragment.ifragment.ITimeLineFragment;
import bearkid.com.bearkiddiaryfamily.ui.view.IconButton;
import bearkid.com.bearkiddiaryfamily.ui.view.RefreshRecyclerView;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

/**
 * 时间轴界面
 */
public class TimeLineFragment extends BaseFragment implements ITimeLineFragment {

    private List<String> childName = Arrays.asList("王小宝", "王小帅", "丫丫");//孩子列表
    private String[] menuItems = new String[]{"数据分析", "风采展示", "添加孩子", "编辑家庭成员"};//标题栏菜单项
    private final float textSize = 20f;//标题栏孩子名字的字体大小
    private float showButtonX, hideButtonX;//浮动按钮的动画位移位置
    private List<TimeLine> list = new ArrayList<>();//显示的时间轴数据
    private TimeLineAdapter mAdpater;
    private TimeLinePresenter presenter;

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
        initPresenter();
        initView(view);
        return view;
    }

    private final void initPresenter() {
        presenter = new TimeLinePresenter(this);
    }

    private final void initView(View v) {
        ib_menu = (IconButton) v.findViewById(R.id.ib_child_menu);
        btn_search = (ButtonFloat) v.findViewById(R.id.btn_timeline_search);
        spinner_name = (AppCompatSpinner) v.findViewById(R.id.spinner_child_name);
        rrv_timeline = (RefreshRecyclerView) v.findViewById(R.id.rv_timeline);

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
            String title = item.getTitle().toString();
            if (title.equals(menuItems[0])) {
                DataAnalysisActivity.startActivity(getContext());
            } else if (title.equals(menuItems[1])) {

            } else if (title.equals(menuItems[2])) {

            } else if (title.equals(menuItems[3])) {
                FamilyActivity.startActivity(getContext());
            }
            return true;
        });

        //时间轴列表
        rrv_timeline.setAdapter(mAdpater = new TimeLineAdapter());
        rrv_timeline.setOnRefreshListener(top -> {
            if (top) presenter.refresh();
            else presenter.loadMore();
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
        rrv_timeline.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

        presenter.init();
    }

    //控件
    private RefreshRecyclerView rrv_timeline;
    private ButtonFloat btn_search;
    private AppCompatSpinner spinner_name;
    private IconButton ib_menu;
    private PopupMenu popupMenu;

    @Override
    public void setRefreshing(boolean refreshing) {
        rrv_timeline.setRefreshing(refreshing);
    }

    @Override
    public void updateTimeLines(List<TimeLine> timeLines) {
        list.addAll(timeLines);
    }

    @Override
    public void setTimeLines(List<TimeLine> timeLines) {
        list = timeLines;
    }

    @Override
    public void notifyChanged() {
        mAdpater.notifyDataSetChanged();
    }

    class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder> {

        private final LruCache<String, Bitmap> bitmapCache;

        public TimeLineAdapter() {
            int maxMemory = (int) Runtime.getRuntime().maxMemory();
            int mCacheSize = maxMemory / 8;
            bitmapCache = new LruCache<String, Bitmap>(mCacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getByteCount();
                }
            };
        }

        @Override
        public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_time_line, parent, false);
            return new TimeLineViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TimeLineViewHolder holder, int position) {
            TimeLine timeLine = list.get(position);

            /*1.发布的内容*/
            final String content = timeLine.getReleasecontent();
            holder.tv_content.setText(content);

            /*2.发布的类型*/
            final String type = timeLine.getType();
            holder.tv_tag.setText(type);

            /*3.发布的类型的图标*/
            //TODO: 发布类型的图标
            Integer logoType = timeLine.getTypelogo();

            /*4.发布的人*/
            String userName = timeLine.getAuthor().getUname();
            holder.tv_author.setText(userName);

            /*5.发布时间*/
            String date = timeLine.getReleasetime().getDate();
            holder.tv_time.setText(date);

            /*6.发布的图片 先显示缓存中存在的图片*/
            if (timeLine.getImage1() != null) {//如果有图片
                holder.ll_picGroup.setVisibility(View.VISIBLE);
                holder.pic1.setOnClickListener(v -> {
                    final String url = timeLine.getImage1().getUrl();
                    presenter.showBigImage(bitmapCache.get(url), url);
                });
                showPicture(timeLine.getImage1(), holder.pic1, R.drawable.image_default);
            } else {
                holder.ll_picGroup.setVisibility(View.GONE);
            }

            if (timeLine.getImage2() != null) {
                holder.pic2.setOnClickListener(v -> {
                    final String url = timeLine.getImage2().getUrl();
                    presenter.showBigImage(bitmapCache.get(url), url);
                });
                showPicture(timeLine.getImage2(), holder.pic2, R.drawable.image_default);
            } else {
                holder.pic2.setVisibility(View.INVISIBLE);
            }

            if (timeLine.getImage3() != null) {
                holder.pic3.setOnClickListener(v -> {
                    final String url = timeLine.getImage3().getUrl();
                    presenter.showBigImage(bitmapCache.get(url), url);
                });
                showPicture(timeLine.getImage3(), holder.pic3, R.drawable.image_default);
            } else {
                holder.pic3.setVisibility(View.INVISIBLE);
            }
        }

        private void showPicture(BmobFile file, ImageView view, int defaultResourse) {
            if (!showCachePicture(file, view)) {
                view.setImageResource(defaultResourse);
                if (!showDiskPicture(file, view))
                    showNetworkPicture(file, view);
            }
        }

        private boolean showCachePicture(BmobFile pic, ImageView view) {
            view.setVisibility(View.VISIBLE);
            final String url = pic.getUrl();
            Bitmap bitmap;
            if ((bitmap = bitmapCache.get(url)) != null) {
                view.setImageBitmap(bitmap);
                return true;
            }
            return false;
        }

        private boolean showDiskPicture(BmobFile file, ImageView view) {
            File pic = file.getLocalFile();
            if (pic == null) return false;

            Bitmap bitmap = BitmapFactory.decodeFile(pic.getAbsolutePath());
            view.setImageBitmap(bitmap);
            return true;
        }

        private void showNetworkPicture(BmobFile file, ImageView view) {
            final String url = file.getUrl();
            view.setTag(url);
            file.download(new DownloadFileListener() {
                @Override
                public void done(String path, BmobException e) {
                    Bitmap pic = BitmapFactory.decodeFile(path);
                    if (pic != null) {
                        bitmapCache.put(url, pic);
                        if (view.getTag().equals(url)) {
                            view.setImageBitmap(pic);
                        }
                    } else {
                        showCachePicture(file, view);
                    }
                }

                @Override
                public void onProgress(Integer integer, long l) {
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class TimeLineViewHolder extends RecyclerView.ViewHolder {
            IconButton pic1, pic2, pic3;
            LinearLayout ll_picGroup;
            TextView tv_tag, tv_author, tv_content, tv_time;
            ImageView iv_icon;

            public TimeLineViewHolder(View view) {
                super(view);
                pic1 = (IconButton) view.findViewById(R.id.ib_timeline_pic1);
                pic2 = (IconButton) view.findViewById(R.id.ib_timeline_pic2);
                pic3 = (IconButton) view.findViewById(R.id.ib_timeline_pic3);
                ll_picGroup = (LinearLayout) view.findViewById(R.id.ll_timeline_pic_group);
                tv_tag = (TextView) view.findViewWithTag(R.id.tv_timeline_tag);
                tv_author = (TextView) view.findViewById(R.id.tv_timeline_author);
                tv_content = (TextView) view.findViewById(R.id.tv_timeline_content);
                tv_time = (TextView) view.findViewById(R.id.tv_timeline_time);
                iv_icon = (ImageView) view.findViewById(R.id.iv_timeline_icon);
            }
        }
    }
}
