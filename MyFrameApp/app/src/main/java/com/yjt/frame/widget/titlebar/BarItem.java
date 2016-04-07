package com.yjt.frame.widget.titlebar;

import android.view.View;
import com.yjt.frame.R;

/**
 * Created by yujiangtao on 16/3/31.
 */
public class BarItem {
    public BarType itemtype= BarType.TImageView;
    public int res=0;//若是textview,res就是R.string.xx;若是imageview,res就是src;
    public View view=null;//若是自定view，则将view赋给它
    public int backres= R.drawable.selector_head_black_btn;//标题栏顶部按钮背景
    //是否可点击
    public boolean clickable=true;
    public int colorres=R.color.white;
    public int id;

}
