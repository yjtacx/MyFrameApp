package com.yjt.frame.widget.titlebar.baritem;

import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yjt.frame.R;
import com.yjt.frame.widget.titlebar.TitleBarConfig;
import com.yjt.frame.widget.titlebar.TitleBarView;
import com.yjt.frame.widget.titlebar.entity.BarTextEntity;

/**
 * Created by yujiangtao on 16/4/11.
 */
public class TextViewItem extends BarItem {
    protected TextView textView=null;
    protected int textcolor;
    protected String text;
    protected boolean backable=false;


    public TextViewItem(TitleBarView titleBarView, BarTextEntity textbean){
        super(titleBarView);
        this.bp=textbean.barPosition;
        this.text=textbean.text;
        this.id=textbean.id;
         this.textcolor=textbean.textColor;
        this.clickable=textbean.clickable;
        this.backable = textbean.backable;
        textView = new TextView(mcontext);
    }


    @Override
    protected void initItemView() {

        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TitleBarConfig.ITEM_TEXT_PADDING,mcontext.getResources().getDisplayMetrics());
        RelativeLayout.LayoutParams lp;
        switch(bp){
            case Left:
                lp=getLeftLayoutParams();
                textView.setPadding(padding, 0, padding, 0);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TitleBarConfig.TEXTSIZE_BUTTON);
                if(backable){
                    Drawable drawable= mcontext.getResources().getDrawable(TitleBarConfig.BACK_BUTTON_RES);
                    // 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    textView.setCompoundDrawables(drawable,null,null,null);
                }
                break;
            case Right:
               lp=getRightLayoutParams();
                textView.setPadding(padding, 0, padding, 0);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TitleBarConfig.TEXTSIZE_BUTTON);
                break;
            case Center:
                lp=getCenterLayoutParams();
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TitleBarConfig.TEXTSIZE_TITLE);
                break;
            default:throw new RuntimeException("BarPosition 不存在");

        }

        textView.setLayoutParams(lp);

        textView.setTextColor(textcolor);
        textView.setGravity(Gravity.CENTER);
        textView.setId(id);
        textView.setText(text);
        if(clickable) {
            textView.setClickable(true);
            textView.setOnClickListener(titleBarView);
            textView.setBackgroundDrawable(backstateD);
        }
    }

    @Override
    protected View getItemView() {
        return textView;
    }

    /**
     * 左侧
     * @return
     */
    protected RelativeLayout.LayoutParams getLeftLayoutParams(){
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        TitleBarConfig.ITEM_HEIGHT, mcontext.getResources().getDisplayMetrics()));
        if (id == R.id.titlebar_left_2)
            lp.addRule(RelativeLayout.RIGHT_OF, R.id.titlebar_left_1);
        else lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        return lp;
    }

    /**
     * 中间
     * @return
     */
    protected RelativeLayout.LayoutParams getCenterLayoutParams(){
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        return lp;

    }

    /**
     * 右侧
     * @return
     */
    protected RelativeLayout.LayoutParams getRightLayoutParams(){
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        TitleBarConfig.ITEM_HEIGHT,
                        mcontext.getResources().getDisplayMetrics()));
        if (id == R.id.titlebar_right_2)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_1);
        else if (id == R.id.titlebar_right_3)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_2);
        else lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        return lp;
    }

}
