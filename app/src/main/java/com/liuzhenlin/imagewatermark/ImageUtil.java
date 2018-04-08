package com.liuzhenlin.imagewatermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;

import static com.liuzhenlin.imagewatermark.DensityUtil.dp2px;
import static com.liuzhenlin.imagewatermark.DensityUtil.sp2px;

public class ImageUtil {
    // 抗锯齿、防抖动、滤波处理
    private static final int HIGH_QUALITY_DRAW_FLAG_PAINT =
            Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG;

    private ImageUtil() throws IllegalAccessException {
        throw new IllegalAccessException("no instance!");
    }

    /**
     * 设置水印图片在左上角
     *
     * @param paddingLeft dp
     * @param paddingTop  dp
     */
    public static Bitmap createWatermarkLeftTop(Context context, Bitmap src, Bitmap watermark,
                                                int paddingLeft, int paddingTop) {
        return createWatermarkBitmap(src, watermark,
                dp2px(context, paddingLeft), dp2px(context, paddingTop));
    }

    /**
     * 设置水印图片到右上角
     */
    public static Bitmap createWatermarkRightTop(Context context, Bitmap src, Bitmap watermark,
                                                 int paddingRight, int paddingTop) {
        return createWatermarkBitmap(src, watermark,
                src.getWidth() - watermark.getWidth() - dp2px(context, paddingRight),
                dp2px(context, paddingTop));
    }

    /**
     * 设置水印图片到中间
     */
    public static Bitmap createWatermarkCenter(Bitmap src, Bitmap watermark) {
        return createWatermarkBitmap(src, watermark,
                (src.getWidth() - watermark.getWidth()) / 2,
                (src.getHeight() - watermark.getHeight()) / 2);
    }

    /**
     * 设置水印图片到左下角
     */
    public static Bitmap createWatermarkLeftBottom(Context context, Bitmap src, Bitmap watermark,
                                                   int paddingLeft, int paddingBottom) {
        return createWatermarkBitmap(src, watermark, dp2px(context, paddingLeft),
                src.getHeight() - watermark.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 设置水印图片在右下角
     */
    public static Bitmap createWatermarkRightBottom(Context context, Bitmap src, Bitmap watermark,
                                                    int paddingRight, int paddingBottom) {
        return createWatermarkBitmap(src, watermark,
                src.getWidth() - watermark.getWidth() - dp2px(context, paddingRight),
                src.getHeight() - watermark.getHeight() - dp2px(context, paddingBottom));
    }

    private static Bitmap createWatermarkBitmap(Bitmap src, Bitmap watermark, int paddingLeft, int paddingTop) {
        if (src == null) {
            return null;
        }
        Bitmap.Config bitmapConfig = src.getConfig();
        if (bitmapConfig == null) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
        final int width = src.getWidth();
        final int height = src.getHeight();
        // 创建一个新的和SRC长度宽度一样的位图
        Bitmap newb = Bitmap.createBitmap(width, height, bitmapConfig);

        // 将该图片作为画布
        Canvas canvas = new Canvas(newb);
        // 在画布（0，0）坐标上开始绘制原始图片
        canvas.drawBitmap(src, 0, 0, null);
        // 在画布上绘制水印图片
        canvas.drawBitmap(watermark, paddingLeft, paddingTop, null);
        // 保存
        canvas.save(Canvas.ALL_SAVE_FLAG);
        return newb;
    }

    /**
     * 绘制文字到左上角
     *
     * @param size        sp
     * @param paddingLeft dp
     * @param paddingTop  dp
     * @see Paint#ANTI_ALIAS_FLAG 消除锯齿
     */
    public static Bitmap drawTextToLeftTop(Context context, Bitmap bitmap, String text,
                                           int size, @ColorInt int color, int paddingLeft, int paddingTop) {
        Paint paint = new Paint(HIGH_QUALITY_DRAW_FLAG_PAINT);
        paint.setTextSize(sp2px(context, size));
        paint.setColor(color);

        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        return drawTextToBitmap(bitmap, text, paint, dp2px(context, paddingLeft),
                dp2px(context, paddingTop) + bounds.height());
    }

    /**
     * 绘制文字到右上方
     */
    public static Bitmap drawTextToRightTop(Context context, Bitmap bitmap, String text,
                                            int size, @ColorInt int color, int paddingRight, int paddingTop) {
        Paint paint = new Paint(HIGH_QUALITY_DRAW_FLAG_PAINT);
        paint.setTextSize(sp2px(context, size));
        paint.setColor(color);

        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        return drawTextToBitmap(bitmap, text, paint,
                bitmap.getWidth() - bounds.width() - dp2px(context, paddingRight),
                dp2px(context, paddingTop) + bounds.height());
    }

    /**
     * 绘制文字到中间
     */
    public static Bitmap drawTextToCenter(Context context, Bitmap bitmap, String text,
                                          int size, @ColorInt int color) {
        Paint paint = new Paint(HIGH_QUALITY_DRAW_FLAG_PAINT);
        paint.setTextSize(sp2px(context, size));
        paint.setColor(color);

        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        return drawTextToBitmap(bitmap, text, paint,
                (bitmap.getWidth() - bounds.width()) / 2,
                (bitmap.getHeight() + bounds.height()) / 2);
    }

    /**
     * 绘制文字到左下方
     */
    public static Bitmap drawTextToLeftBottom(Context context, Bitmap bitmap, String text,
                                              int size, @ColorInt int color, int paddingLeft, int paddingBottom) {
        Paint paint = new Paint(HIGH_QUALITY_DRAW_FLAG_PAINT);
        paint.setTextSize(sp2px(context, size));
        paint.setColor(color);

        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        return drawTextToBitmap(bitmap, text, paint, dp2px(context, paddingLeft),
                bitmap.getHeight() - dp2px(context, paddingBottom));
    }

    /**
     * 绘制文字到右下角
     */
    public static Bitmap drawTextToRightBottom(Context context, Bitmap bitmap, String text,
                                               int size, @ColorInt int color, int paddingRight, int paddingBottom) {
        Paint paint = new Paint(HIGH_QUALITY_DRAW_FLAG_PAINT);
        paint.setTextSize(sp2px(context, size));
        paint.setColor(color);

        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        return drawTextToBitmap(bitmap, text, paint,
                bitmap.getWidth() - bounds.width() - dp2px(context, paddingRight),
                bitmap.getHeight() - dp2px(context, paddingBottom));
    }

    // 图片上绘制文字
    private static Bitmap drawTextToBitmap(Bitmap bitmap, String text, Paint paint,
                                           int paddingLeft, int paddingTop) {
        Bitmap.Config bitmapConfig = bitmap.getConfig();
        if (bitmapConfig == null) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(text, paddingLeft, paddingTop, paint);
        return bitmap;
    }

    /**
     * 缩放图片
     *
     * @param w px
     * @param h px
     */
    public static Bitmap scaleWithWH(Bitmap src, double w, double h) {
        if (w == 0 || h == 0 || src == null) {
            return src;
        }
        // 记录src的宽高
        final int width = src.getWidth();
        final int height = src.getHeight();

        // 计算缩放比例
        final float widthScale = (float) (w / width);
        final float heightScale = (float) (h / height);

        // 创建一个matrix容器
        Matrix matrix = new Matrix();
        // 缩放
        matrix.postScale(widthScale, heightScale);
        // 创建缩放后的图片
        return Bitmap.createBitmap(src, 0, 0, width, height, matrix, true);
    }
}