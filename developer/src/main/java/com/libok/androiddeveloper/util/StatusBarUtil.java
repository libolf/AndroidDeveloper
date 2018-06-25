package com.libok.androiddeveloper.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

/**
 * @author liboK
 * @date 2018/6/25 0025 5:28
 * @e-mail libolf@outlook.com
 * @description 沉浸化、透明化状态栏
 */
public class StatusBarUtil {

    private static final String TAG = "StatusBarUtil";

    /**
     * 透明化状态栏，只改变状态栏，不改变导航栏，在API21以上生效
     *
     * @param activity 获取Window
     */
    public static void immerseStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 透明化状态栏和导航栏，在API21以上生效，ToolBar会多加上个PaddingBottom
     * 当有ToolBar时，最好不用fitsSystemWindows，因为控制不好PaddingBottom，可以在此处获取状态栏高度，并手动为ToolBar设置
     *
     * @param activity 获取Window
     */
    public static void immerseStatusAndNavigationBar(Activity activity, Toolbar toolbar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            int paddingTop = toolbar.getPaddingTop();
            int paddingBottom = toolbar.getPaddingBottom();
            L.e(TAG, "immerseStatusAndNavigationBar: " + paddingTop + " " + paddingBottom);
//            toolbar.setPadding(0, paddingTop, 0, 0);
            toolbar.setPadding(0, 120, 0, 0);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 沉浸化，状态栏和导航栏只有在主动滑动时才会出现，并在一定时间自动消失，适合启动图和导航页
     *
     * @param activity 获取Window
     */
    public static void immerseAll(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    /**
     * 另一种方式的透明化状态栏，在4.4是渐变，在5.0以上是有个阴影
     * @param activity
     */
    public static void transparentStatus(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
