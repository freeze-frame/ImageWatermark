package com.liuzhenlin.imagewatermark;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;

/**
 * Created on 2017/10/11. </br>
 * Copyright (c) 2017 刘振林.All rights reserved.
 *
 * @author 刘振林
 */

public class DensityUtil {
    private DensityUtil() throws IllegalAccessException {
        throw new IllegalAccessException("no instance!");
    }

    public static int dp2px(@NonNull Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(@NonNull Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(@NonNull Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(@NonNull Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getScreenWidth(@NonNull Activity activity) {
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        if (SDK_INT >= JELLY_BEAN_MR1) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        }
        return dm.widthPixels;
    }

    public static int getScreenHeight(@NonNull Activity activity) {
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        if (SDK_INT >= JELLY_BEAN_MR1) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        }
        return dm.heightPixels;
    }
}
