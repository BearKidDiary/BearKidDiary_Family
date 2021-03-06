package bearkid.com.bearkiddiaryfamily.ui.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import bearkid.com.bearkiddiaryfamily.R;


/**
 * Created by Administrator on 2016/7/2.
 */
public class RefreshRecyclerView extends SwipeRefreshLayout implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private Context context;
    private OnRefreshListener listener;
    private LinearLayoutManager layoutManager;
    private boolean isLoadingMore = false;

    public RefreshRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private final void init(Context context) {
        this.context = context;

        //setProgressBackgroundColorSchemeResource(R.color.colorPrimary);
        setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        setOnRefreshListener(this);

        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(layoutManager = new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getAdapter() != null && recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    int lastPosition = layoutManager.findLastVisibleItemPosition();
                    int itemCount = recyclerView.getAdapter().getItemCount();

                    if (lastPosition >= itemCount - 2 && dy > 0) {
                        if (!isLoadingMore) {
                            isLoadingMore = true;
                            if (listener != null) listener.onRefresh(false);
                        }
                    }
                }
            }
        });

        addView(recyclerView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        if (refreshing == false)
            isLoadingMore = false;
        super.setRefreshing(refreshing);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decoration) {
        recyclerView.addItemDecoration(decoration);
    }

    public void removeItemDecoration(RecyclerView.ItemDecoration decor) {
        recyclerView.removeItemDecoration(decor);
    }

    public int getLastVisiblePosition() {
        return layoutManager.findLastVisibleItemPosition();
    }

    public void setLastVisiblePosition(int position) {
        recyclerView.scrollToPosition(position);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    public void setItemAnimator(RecyclerView.ItemAnimator animator) {
        recyclerView.setItemAnimator(animator);
    }

    public void addOnScrollListener(RecyclerView.OnScrollListener listener) {
        recyclerView.addOnScrollListener(listener);
    }

    public void removeOnScrollListener(RecyclerView.OnScrollListener listener) {
        recyclerView.removeOnScrollListener(listener);
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.listener = listener;
    }

    @Override
    public void onRefresh() {
        if (listener != null) listener.onRefresh(true);
    }

    public interface OnRefreshListener {
        void onRefresh(boolean top);
    }
}
