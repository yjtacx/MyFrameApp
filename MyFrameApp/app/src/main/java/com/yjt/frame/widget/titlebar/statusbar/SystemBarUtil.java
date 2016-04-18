package com.yjt.frame.widget.titlebar.statusbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yjt.frame.widget.titlebar.TitleBarView;

/**
 * Created by yujiangtao on 16/4/15.
 */
public class SystemBarUtil {

    public SystemBarTintManager tintManager=null;

    public SystemBarUtil(Activity activity,TitleBarView view){
        View rootview = (View) view.getParent();
//        if(Build.VERSION.SDK_INT>)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            rootview.setFitsSystemWindows(true);
        }
        initSystemBar(activity);
    }


    private void initSystemBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }
        tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
        // 使用颜色资源
        tintManager.setStatusBarTintResource(Color.TRANSPARENT);
    }

    @TargetApi(19)
    private static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public SystemBarTintManager getSystemBarTintManager(){
        return tintManager;
    }
}
