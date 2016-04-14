package com.yjt.frame.widget.titlebar.baritem;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RelativeLayout;

import com.yjt.frame.widget.titlebar.TitleBarConfig;
import com.yjt.frame.widget.titlebar.TitleBarView;
import com.yjt.frame.widget.titlebar.entity.BarMainSubEntity;

/**
 * Created by yujiangtao on 16/4/13.
 */
public class BarMainSubItem extends TextViewItem {
    BarMainSubEntity entity;
    int maintextsize;
    int subtextsize;

    public BarMainSubItem(TitleBarView titleBarView, BarMainSubEntity textbean) {
        super(titleBarView, textbean);
        
        entity = textbean;
        maintextsize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                TitleBarConfig.TEXTSIZE_TITLE_MAIN,mcontext.getResources().getDisplayMetrics());
        subtextsize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                TitleBarConfig.TEXTSIZE_TITLE_SUB,mcontext.getResources().getDisplayMetrics());
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
                textView.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
//                if(backable){
//                    Drawable drawable= mcontext.getResources().getDrawable(TitleBarConfig.BACK_BUTTON_RES);
//                    // 这一步必须要做,否则不会显示.
//                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                    textView.setCompoundDrawables(drawable,null,null,null);
//                }
                break;
            case Right:
                lp=getRightLayoutParams();
                textView.setPadding(padding, 0, padding, 0);
                break;
            case Center:
                lp=getCenterLayoutParams();
                textView.setGravity(Gravity.CENTER);
                break;
            default:throw new RuntimeException("BarPosition 不存在");

        }

        textView.setLayoutParams(lp);

        textView.setTextColor(textcolor);
        textView.setId(id);
        AbsoluteSizeSpan mainspan = new AbsoluteSizeSpan(maintextsize);
        AbsoluteSizeSpan subspan = new AbsoluteSizeSpan(subtextsize);
        String text = entity.maintitletext+"\n"+entity.subtitletext;
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(mainspan, 0, entity.maintitletext.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //0.5f表示默认字体大小的一半
        spannableString.setSpan(subspan,entity.maintitletext.length()+1 ,
                text.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //0.5f表示默认字体大小的一半
        textView.append(spannableString);

        textView.setLineSpacing(0f,1.2f);
        if(clickable) {
            textView.setClickable(true);
            textView.setOnClickListener(titleBarView);
            textView.setBackgroundDrawable(backstateD);
        }
    }


}
