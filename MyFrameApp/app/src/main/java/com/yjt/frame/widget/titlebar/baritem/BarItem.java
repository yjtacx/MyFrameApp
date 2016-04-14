package com.yjt.frame.widget.titlebar.baritem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.RelativeLayout;

import com.yjt.frame.widget.titlebar.BarPosition;
import com.yjt.frame.widget.titlebar.TitleBarUtil;
import com.yjt.frame.widget.titlebar.TitleBarView;

/**
 * Created by yujiangtao on 16/4/11.
 */
public abstract class BarItem {
    protected TitleBarView titleBarView;
    protected Drawable backstateD= TitleBarUtil.getDrawableSelector();

    protected boolean clickable=true;
    protected int id;
    protected Context mcontext;
    protected BarPosition bp;

    protected abstract void initItemView();
    protected abstract View getItemView();
    protected abstract RelativeLayout.LayoutParams getLeftLayoutParams();
    protected abstract RelativeLayout.LayoutParams getRightLayoutParams();
    protected abstract RelativeLayout.LayoutParams getCenterLayoutParams();


    public BarItem(TitleBarView titleBarView){
        this.titleBarView=titleBarView;
        this.mcontext=titleBarView.getContext();
    }
    public View getItem(){
        initItemView();
        return getItemView();
    }



}
