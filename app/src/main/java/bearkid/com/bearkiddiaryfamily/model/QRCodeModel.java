package bearkid.com.bearkiddiaryfamily.model;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Map;

import bearkid.com.qrcodelib.zxing.android.CaptureActivity;

/**
 * 处理二维码相关的功能
 * 包括扫描二维码获取对应的字符串
 * 已经生成一个指定字符串的二维码
 */
public class QRCodeModel {
    /**
     * 打开扫描二维码的相机界面
     * 通过requestCode在onActivityResult获取扫描结果
     * 可通过getBitmap和getContent方法获取二维码图片和二维码解析内容
     */
    public static final void scanQRCode(Fragment fragment, int requestCode) {
        fragment.startActivityForResult(new Intent(fragment.getContext(), CaptureActivity.class), requestCode);
    }

    /**
     * 打开扫描二维码的相机界面
     * 通过requestCode在onActivityResult获取扫描结果
     * 可通过getBitmap和getContent方法获取二维码图片和二维码解析内容
     */
    public static final void scanQRCode(Activity activity, int requestCode) {
        activity.startActivityForResult(new Intent(activity, CaptureActivity.class), requestCode);
    }

    public static final Bitmap getBitmap(Intent data) {
        return data.getParcelableExtra("codedBitmap");
    }

    public static final String getContent(Intent data) {
        return data.getStringExtra("codedContent");
    }

    /**
     * 生成二维码Bitmap
     *
     * @param content   内容
     * @param widthPix  图片宽度
     * @param heightPix 图片高度
     * @param logoBm    二维码中心的Logo图标（可以为null）
     * @return 生成的二维码 可能为空
     */
    public static Bitmap createQRCode(String content, int widthPix, int heightPix, Bitmap logoBm) {
        try {
            if (content == null || content.trim().equals("")) {
                return null;
            }

            //配置参数
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //容错级别
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            //设置空白边距的宽度
            //hints.put(EncodeHintType.MARGIN, 2); //default is 4

            // 图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, widthPix, heightPix, hints);
            int[] pixels = new int[widthPix * heightPix];
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (int y = 0; y < heightPix; y++) {
                for (int x = 0; x < widthPix; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * widthPix + x] = 0xff000000;
                    } else {
                        pixels[y * widthPix + x] = 0xffffffff;
                    }
                }
            }

            // 生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, widthPix, 0, 0, widthPix, heightPix);

            if (logoBm != null) {
                bitmap = addLogo(bitmap, logoBm);
            }

            return bitmap;
        } catch (Exception e) {
            Log.e("zy", "生成二维码失败" + e.toString());
        }
        return null;
    }

    /**
     * 在二维码中间添加Logo图案
     */
    private static Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }

        if (logo == null) {
            return src;
        }

        //获取图片的宽高
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }

        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }

        //logo大小为二维码整体大小的1/5
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            Log.e("zy", "生成二维码时添加logo图案失败：" + e.toString());
        }

        return bitmap;
    }
}
