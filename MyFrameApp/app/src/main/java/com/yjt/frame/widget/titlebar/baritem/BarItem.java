package com.yjt.frame.widget.titlebar.baritem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

import com.yjt.frame.R;
import com.yjt.frame.widget.titlebar.barHelper.BarPosition;
import com.yjt.frame.widget.titlebar.barHelper.BarType;
import com.yjt.frame.widget.titlebar.TitleBarConfig;
import com.yjt.frame.widget.titlebar.barHelper.TitleBarUtil;
import com.yjt.frame.widget.titlebar.TitleBarView;

/**
 * Created by yujiangtao on 16/4/11.
 * 标题栏ITEM的基类，也是模板类
 */
public abstract class BarItem {
    protected TitleBarView titleBarView;
    protected Drawable backstateD= TitleBarUtil.getDrawableSelector();
    protected boolean clickable=true;
    protected int id;
    protected Context mcontext;
    protected BarPosition bp;
    protected BarType barType;


    public BarItem(TitleBarView titleBarView){
        this.titleBarView=titleBarView;
        this.mcontext=titleBarView.getContext();
    }

    /**
     * 为View设定属性和param
     */
    protected abstract void buildView();

    /**
     * 返回具体的View
     * @return
     */

    protected abstract View getItemView();

    /**
     * 得到View的宽度
     * @return
     */
    protected abstract int getWidth();
    /**
     * 得到View的高度
     * @return
     */
    protected int getHeight(){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TitleBarConfig.DEFAULT_ITEM_HEIGHT, mcontext.getResources().getDisplayMetrics());
    }

    /**
     * 返回设置后的View
     * @return
     */
    public View getItem(){
        buildView();
        return getItemView();
    }
    /**
     * 生成左侧LayoutParmas
     * @return
     */
    protected RelativeLayout.LayoutParams getLeftLayoutParams(){
        RelativeLayout.LayoutParams lp=null;
        if(barType==BarType.TCustomView) lp = (RelativeLayout.LayoutParams)
                getItemView().getLayoutParams();
        else lp =
                new RelativeLayout.LayoutParams(getWidth(),getHeight());
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        if (id == R.id.titlebar_left_2)
            lp.addRule(RelativeLayout.RIGHT_OF, R.id.titlebar_left_1);
        else lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        return lp;
    }
    /**
     * 生成右侧LayoutParmas
     * @return
     */
    protected RelativeLayout.LayoutParams getRightLayoutParams(){
        RelativeLayout.LayoutParams lp=null;
        if(barType==BarType.TCustomView) lp = (RelativeLayout.LayoutParams)
                getItemView().getLayoutParams();
        else lp =
                new RelativeLayout.LayoutParams(getWidth(),getHeight());
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        if (id == R.id.titlebar_right_2)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_1);
        else if(id ==R.id.titlebar_right_3)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_2);
        else lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        return lp;
    }

    /**
     * 生成中间LayoutParmas
     * @return
     */
    protected RelativeLayout.LayoutParams getCenterLayoutParams(){
        RelativeLayout.LayoutParams lp=null;
        if(barType==BarType.TCustomView) lp = (RelativeLayout.LayoutParams)
                getItemView().getLayoutParams();
        else lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        return lp;
    }
}
