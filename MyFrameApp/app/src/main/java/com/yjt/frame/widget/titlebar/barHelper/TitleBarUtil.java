package com.yjt.frame.widget.titlebar.barHelper;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;

import com.yjt.frame.widget.titlebar.TitleBarConfig;

/**
 * Created by yujiangtao on 16/4/11.
 */
public class TitleBarUtil {

    public static ColorDrawable pressedDrawable =
            new ColorDrawable(Color.parseColor(TitleBarConfig.DEFAULT_PRESSED_COLOR));
    public static ColorDrawable nomalDrawable =
            new ColorDrawable(Color.parseColor(TitleBarConfig.DEFAULT_NORMAL_COLOR));
    public static ColorDrawable focusedDrawable =
            new ColorDrawable(Color.parseColor(TitleBarConfig.DEFAULT_FOCUSED_COLOR));

    /**
     * 获取标题栏按钮的背景图片列表
     *
     * @return StateListDrawable
     */
    public static StateListDrawable getDrawableSelector() {
        //当XML的设定是false时，就需要使用资源符号的负值来设定。
        StateListDrawable drawable = new StateListDrawable();
        // View.PRESSED_ENABLED_STATE_SET
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        // View.ENABLED_SELECTED_STATE_SET
        drawable.addState(new int[]{android.R.attr.state_selected}, focusedDrawable);
        // View.ENABLED_STATE_SET
        drawable.addState(new int[]{android.R.attr.state_enabled}, nomalDrawable);
        // View.FOCUSED_STATE_SET
        drawable.addState(new int[]{android.R.attr.state_focused}, focusedDrawable);

        drawable.addState(new int[]{}, nomalDrawable);

        return drawable;

    }

    /**
     * 获取标题栏按钮的背景颜色列表
     *
     * @return ColorStateList
     */

    public static ColorStateList getColorSelector() {
        int[] colors = new int[]{Color.parseColor(TitleBarConfig.DEFAULT_PRESSED_COLOR),
                Color.parseColor(TitleBarConfig.DEFAULT_FOCUSED_COLOR),
                Color.parseColor(TitleBarConfig.DEFAULT_FOCUSED_COLOR),
                Color.parseColor(TitleBarConfig.DEFAULT_NORMAL_COLOR)};
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_pressed};
        states[1] = new int[]{android.R.attr.state_focused};
        states[2] = new int[]{android.R.attr.state_selected};
        states[3] = new int[]{};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }


}
