package bearkid.com.bearkiddiaryfamily.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.ImageViewTarget;

import java.util.ArrayList;
import java.util.List;

import bearkid.com.bearkiddiaryfamily.R;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFImageView;

public class GalleryFinalHelper implements BitmapSelector {

    private FunctionConfig functionConfig = null;
    private CallBack cb = null;

    public GalleryFinalHelper(Context context) {
        final int primary = context.getResources().getColor(R.color.colorPrimary);
        final int primaryDark = context.getResources().getColor(R.color.colorPrimaryDark);
        //设置主题
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(primary)
                .setTitleBarTextColor(Color.WHITE)
                .setTitleBarIconColor(Color.BLACK)
                .setFabNornalColor(primary)
                .setFabPressedColor(primaryDark)
                .setCheckNornalColor(Color.WHITE)
                .setCheckSelectedColor(primary)
//                .setIconBack(R.mipmap.ic_action_previous_item)
//                .setIconRotate(R.mipmap.ic_action_repeat)
//                .setIconCrop(R.mipmap.ic_action_crop)
//                .setIconCamera(R.mipmap.ic_action_camera)
                .build();
        //配置功能
        functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setEnablePreview(true)
                .build();
        //配置imageloader
        CoreConfig coreConfig = new CoreConfig.Builder(context, new GlideImageLoader(), theme)
                .setNoAnimcation(true)//关闭动画
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);
    }

    @Override
    public void openGallery(int maxPicNum, @NonNull CallBack callBack) {
        if (maxPicNum <= 0) throw new IllegalArgumentException("maxPicNum should large than 0");
        if (callBack == null) throw new NullPointerException("callBack can not be null");
        cb = callBack;
        if (maxPicNum == 1) {
            GalleryFinal.openGallerySingle(203, mHandler);
        } else {
            GalleryFinal.openGalleryMuti(203, maxPicNum, mHandler);
        }
    }

    private GalleryFinal.OnHanlderResultCallback mHandler = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            List<String> path = new ArrayList<>();
            for (PhotoInfo info : resultList) {
                path.add(info.getPhotoPath());
            }
            cb.finish(path, resultList.size());
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            cb.error(errorMsg);
        }
    };

    private static class GlideImageLoader implements cn.finalteam.galleryfinal.ImageLoader {
        @Override
        public void displayImage(Activity activity, String path, final GFImageView imageView, Drawable defaultDrawable, int width, int height) {
            Glide.with(activity)
                    .load("file://" + path)
                    .placeholder(defaultDrawable)
                    .error(defaultDrawable)
                    .override(width, height)
                    .diskCacheStrategy(DiskCacheStrategy.NONE) //不缓存到SD卡
                    .skipMemoryCache(true)
                    //.centerCrop()
                    .into(new ImageViewTarget<GlideDrawable>(imageView) {
                        @Override
                        protected void setResource(GlideDrawable resource) {
                            imageView.setImageDrawable(resource);
                        }

                        @Override
                        public void setRequest(Request request) {
                            imageView.setTag(R.id.image_tag, request);
                        }

                        @Override
                        public Request getRequest() {
                            return (Request) imageView.getTag(R.id.image_tag);
                        }
                    });
        }

        @Override
        public void clearMemoryCache() {
        }
    }
}
