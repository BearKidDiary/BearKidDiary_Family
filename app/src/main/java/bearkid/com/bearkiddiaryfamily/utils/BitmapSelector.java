package bearkid.com.bearkiddiaryfamily.utils;

import java.util.List;

/**
 * 图片选择器的对内接口，
 * 当更换图片选择器的时候只需要实现BitmapSelector即可
 */
public interface BitmapSelector {
    void openGallery(int maxPicNum, CallBack callBack);

    interface CallBack {
        void finish(List<String> path, int picNum);

        void error(String msg);
    }
}