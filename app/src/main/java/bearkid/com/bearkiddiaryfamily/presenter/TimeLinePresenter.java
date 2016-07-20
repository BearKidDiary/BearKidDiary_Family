package bearkid.com.bearkiddiaryfamily.presenter;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import bearkid.com.bearkiddiaryfamily.ui.fragment.TimeLineFragment;
import bearkid.com.bearkiddiaryfamily.ui.fragment.ifragment.ITimeLineFragment;

/**
 * Created by admin on 2016/7/20.
 */
public class TimeLinePresenter {
    private ITimeLineFragment view;
    private int pageSize = 10;
    private int pageNum = 1;

    public TimeLinePresenter(TimeLineFragment view) {
        this.view = view;
    }

    public void init() {

    }

    public void refresh() {

    }

    public void loadMore() {

    }

    public void showBigImage(@Nullable Bitmap bitmap, String url) {

    }
}
