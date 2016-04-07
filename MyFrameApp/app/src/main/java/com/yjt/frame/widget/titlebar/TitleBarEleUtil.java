package com.yjt.frame.widget.titlebar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yjt.frame.R;

/**
 * Created by yujiangtao on 16/3/30.
 */
public class TitleBarEleUtil {



    private Context mcontext = null;
    private TitleBarView titleBarView;


    public TitleBarEleUtil(TitleBarView titlebar) {
        this.titleBarView = titlebar;
        this.mcontext = titlebar.getContext();
    }

    /**
     * 添加左侧View，可以有两个
     *
     * @param item
     */
    public void addLeftView(BarItem item) {
        int leftid = R.id.titlebar_left_1;
        if (titleBarView.findViewById(leftid) != null) leftid = R.id.titlebar_left_2;
        switch (item.itemtype) {
            case TImageView:
                addLeftImageView(leftid, item);
                break;
            case TTextView:
                addLeftTextView(leftid, item);
                break;
            case TProgressBar:
                addLeftProgressView(leftid, item);
                break;
            case TCustomView:
                addLeftCustomView(leftid, item);
                break;
            case TBackText:
                addLeftBackTextView(item);
                break;
        }
    }

    /**
     * 添加右侧view，可以有两个
     *
     * @param item
     */
    public void addRightView(BarItem item) {
        int rightid = R.id.titlebar_right_1;
        if (titleBarView.findViewById(R.id.titlebar_right_2) != null) rightid = R.id.titlebar_right_3;
        else if(titleBarView.findViewById(R.id.titlebar_right_1)!=null)rightid = R.id.titlebar_right_2;
        switch (item.itemtype) {
            case TImageView:
                addRightImageView(rightid, item);
                break;
            case TTextView:
                addRightTextView(rightid, item);
                break;
            case TProgressBar:
                addRightProgressView(rightid, item);
                break;
            case TCustomView:
                addRightCustomView(rightid, item);
                break;
        }
    }

    /**
     * 添加中间View
     *
     * @param item
     */
    public void addCenterView(BarItem item) {
        switch (item.itemtype) {
            case TImageView:
                addCenterImageView(item);
                break;
            case TTextView:
                addCenterTextView(item);
                break;
            case TProgressBar:
//                addCenterProgressView(item);
                break;
            case TCustomView:
                addCenterCustomView(item);
                break;
        }
    }

    /***
     * 添加标题栏左侧图片按钮，最多可以添加两个
     *
     * @param leftid
     * @param baritem
     */
    public void addLeftImageView(int leftid, BarItem baritem) {
        ImageView imageView = new ImageView(mcontext);

        RelativeLayout.LayoutParams lp =
                new RelativeLayout.LayoutParams(
                        (int) TypedValue.applyDimension(TypedValue.
                                COMPLEX_UNIT_DIP, TittleBarConfig.ITEM_HEIGHT,mcontext.getResources().getDisplayMetrics()),
                        (int) TypedValue.applyDimension(TypedValue.
                                COMPLEX_UNIT_DIP,TittleBarConfig.ITEM_HEIGHT,mcontext.getResources().getDisplayMetrics()));
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        if (leftid == R.id.titlebar_left_2)
            lp.addRule(RelativeLayout.RIGHT_OF, R.id.titlebar_left_1);
        else lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        imageView.setLayoutParams(lp);
        imageView.setId(leftid);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setBackgroundResource(R.drawable.selector_head_black_btn);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TittleBarConfig.ITEM_BUTTON_PADDING,mcontext.getResources().getDisplayMetrics());
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setImageDrawable(mcontext.getResources().getDrawable(baritem.res));

        titleBarView.addView(imageView);
        if (baritem.clickable) {
            imageView.setOnClickListener(titleBarView);
            imageView.setBackgroundResource(baritem.backres);
        }
    }

    /**
     * 添加左侧文字按钮
     *
     * @param leftid
     * @param baritem
     */
    public void addLeftTextView(int leftid, BarItem baritem) {
        TextView textView = new TextView(mcontext);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        TittleBarConfig.ITEM_HEIGHT,mcontext.getResources().getDisplayMetrics()));
        if (leftid == R.id.titlebar_left_2)
            lp.addRule(RelativeLayout.RIGHT_OF, R.id.titlebar_left_1);
        else lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        textView.setLayoutParams(lp);
        textView.setTextColor(mcontext.getResources().getColor(baritem.colorres));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TittleBarConfig.TEXTSIZE_BUTTON);
        textView.setId(leftid);
        textView.setGravity(Gravity.CENTER);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TittleBarConfig.ITEM_TEXT_PADDING,mcontext.getResources().getDisplayMetrics());
        textView.setPadding(padding, 0, padding, 0);
        textView.setText(mcontext.getResources().getString(baritem.res));
        titleBarView.addView(textView);
        if (baritem.clickable) {
            textView.setOnClickListener(titleBarView);
            textView.setBackgroundResource(baritem.backres);
        }
    }

    public void addLeftBackTextView(BarItem baritem){
        TextView textView = new TextView(mcontext);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        TittleBarConfig.ITEM_HEIGHT,mcontext.getResources().getDisplayMetrics()));
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        textView.setLayoutParams(lp);
        textView.setTextColor(mcontext.getResources().getColor(baritem.colorres));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TittleBarConfig.TEXTSIZE_BUTTON);
        textView.setId(R.id.titlebar_left_1);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        Drawable drawable= mcontext.getResources().getDrawable(R.mipmap.back_icon_normal);
/// 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable,null,null,null);
//        textView.setCompoundDrawablePadding();
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TittleBarConfig.ITEM_TEXT_PADDING,mcontext.getResources().getDisplayMetrics());
        textView.setPadding(padding, 0, padding, 0);
        textView.setText(mcontext.getResources().getString(baritem.res));
        titleBarView.addView(textView);
        if (baritem.clickable) {
            textView.setOnClickListener(titleBarView);
            textView.setBackgroundResource(baritem.backres);
        }
    }

    /**
     * 添加左侧圆形进度框
     *
     * @param leftid
     * @param baritem
     */
    public void addLeftProgressView(int leftid, BarItem baritem) {
        ProgressBar pbar = new ProgressBar(mcontext);
        pbar.setId(leftid);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        TittleBarConfig.ITEM_HEIGHT,mcontext.getResources().getDisplayMetrics()),
        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TittleBarConfig.ITEM_HEIGHT,mcontext.getResources().getDisplayMetrics()));
        if (leftid == R.id.titlebar_left_2)
            lp.addRule(RelativeLayout.RIGHT_OF, R.id.titlebar_left_1);
        else lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        if (baritem.res != 0)
            pbar.setIndeterminateDrawable(mcontext.getResources().getDrawable(baritem.res));
        pbar.setLayoutParams(lp);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TittleBarConfig.ITEM_BUTTON_PADDING,mcontext.getResources().getDisplayMetrics());
        pbar.setPadding(padding, padding, padding, padding);
        titleBarView.addView(pbar);
        if (baritem.clickable)
            pbar.setOnClickListener(titleBarView);
    }

    /**
     * 添加左侧自定义view
     *
     * @param leftid
     * @param item
     */
    public void addLeftCustomView(int leftid, BarItem item) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) item.view.getLayoutParams();
        if (leftid == R.id.titlebar_left_2)
            lp.addRule(RelativeLayout.RIGHT_OF, R.id.titlebar_left_1);
        else lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        item.view.setId(leftid);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TittleBarConfig.ITEM_BUTTON_PADDING,mcontext.getResources().getDisplayMetrics());
        item.view.setPadding(padding, 0, padding, 0);
        titleBarView.addView(item.view);
        if (item.clickable)
            item.view.setOnClickListener(titleBarView);
    }

    /***
     * 添加标题栏右侧图片按钮，最多可以添加两个
     *
     * @param rightid
     * @param baritem
     */
    public void addRightImageView(int rightid, BarItem baritem) {
        ImageView imageView = new ImageView(mcontext);

        RelativeLayout.LayoutParams lp =
                new RelativeLayout.LayoutParams(
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                TittleBarConfig.ITEM_HEIGHT, mcontext.getResources().getDisplayMetrics())
                        ,
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                TittleBarConfig.ITEM_HEIGHT, mcontext.getResources().getDisplayMetrics()));
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        if (rightid == R.id.titlebar_right_2)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_1);
        else if(rightid ==R.id.titlebar_right_3)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_2);
        else lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        imageView.setLayoutParams(lp);
        imageView.setId(rightid);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TittleBarConfig.ITEM_BUTTON_PADDING,mcontext.getResources().getDisplayMetrics());
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setImageDrawable(mcontext.getResources().getDrawable(baritem.res));
        titleBarView.addView(imageView);
        if (baritem.clickable) {
            imageView.setBackgroundResource(baritem.backres);
            imageView.setOnClickListener(titleBarView);
        }
    }


    /**
     * 添加右侧文字按钮
     *
     * @param righttid
     * @param baritem
     */
    public void addRightTextView(int righttid, BarItem baritem) {

        TextView textView = new TextView(mcontext);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        TittleBarConfig.ITEM_HEIGHT,mcontext.getResources().getDisplayMetrics()));

        if (righttid == R.id.titlebar_right_2)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_1);
        else if(righttid ==R.id.titlebar_right_3)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_2);
        else lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        textView.setLayoutParams(lp);
        textView.setTextColor(mcontext.getResources().getColor(baritem.colorres));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TittleBarConfig.TEXTSIZE_BUTTON);
        textView.setGravity(Gravity.CENTER);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TittleBarConfig.ITEM_TEXT_PADDING,mcontext.getResources().getDisplayMetrics());
        textView.setPadding(padding, 0, padding, 0);
        textView.setId(righttid);
        textView.setText(mcontext.getResources().getString(baritem.res));
        titleBarView.addView(textView);
        if (baritem.clickable) {
            textView.setBackgroundResource(baritem.backres);
            textView.setOnClickListener(titleBarView);
        }
    }

    /**
     * 添加左侧圆形进度框
     *
     * @param rightid
     * @param baritem
     */
    public void addRightProgressView(int rightid, BarItem baritem) {
        ProgressBar pbar = new ProgressBar(mcontext);
        pbar.setId(rightid);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        TittleBarConfig.ITEM_HEIGHT,mcontext.getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        TittleBarConfig.ITEM_HEIGHT,mcontext.getResources().getDisplayMetrics()));
        if (rightid == R.id.titlebar_right_2)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_1);
        else if(rightid ==R.id.titlebar_right_3)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_2);

        else lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        if (baritem.res != 0)
            pbar.setIndeterminateDrawable(mcontext.getResources().getDrawable(baritem.res));
        pbar.setLayoutParams(lp);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TittleBarConfig.ITEM_BUTTON_PADDING,mcontext.getResources().getDisplayMetrics());
        pbar.setPadding(padding, padding, padding, padding);
        titleBarView.addView(pbar);
        if (baritem.clickable)
            pbar.setOnClickListener(titleBarView);
    }

    /**
     * 添加右侧自定义view
     *
     * @param rightid
     * @param item
     */
    public void addRightCustomView(int rightid, BarItem item) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) item.view.getLayoutParams();
        if (rightid == R.id.titlebar_right_2)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_1);
        else if(rightid ==R.id.titlebar_right_3)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_2);

        else lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        item.view.setId(rightid);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TittleBarConfig.ITEM_BUTTON_PADDING,mcontext.getResources().getDisplayMetrics());
        item.view.setPadding(padding, 0, padding, 0);
        titleBarView.addView(item.view);
        if (item.clickable)
            item.view.setOnClickListener(titleBarView);
    }

    /***
     * 添加标题栏中间图片
     *
     * @param baritem
     */
    public void addCenterImageView(BarItem baritem) {
        ImageView imageView = new ImageView(mcontext);
        RelativeLayout.LayoutParams lp = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(lp);
        if (baritem.clickable) {
            imageView.setOnClickListener(titleBarView);
            imageView.setBackgroundResource(baritem.backres);
        }
        imageView.setId(R.id.titlebar_center);
        imageView.setImageDrawable(mcontext.getResources().getDrawable(baritem.res));
        titleBarView.addView(imageView);
    }

    /***
     * 添加标题栏中间文字
     *
     * @param baritem
     */
    public void addCenterTextView(BarItem baritem) {
        TextView textView = new TextView(mcontext);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        textView.setLayoutParams(lp);
        textView.setTextColor(mcontext.getResources().getColor(baritem.colorres));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TittleBarConfig.TEXTSIZE_TITLE);
        if (baritem.clickable) {
            textView.setOnClickListener(titleBarView);
            textView.setBackgroundResource(baritem.backres);
        }
        textView.setId(R.id.titlebar_center);
        textView.setText(mcontext.getResources().getString(baritem.res));
        titleBarView.addView(textView);
    }

    /**
     * 添加标题栏中间自定义view
     *
     * @param item
     */
    public void addCenterCustomView(BarItem item) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) item.view.getLayoutParams();
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        item.view.setId(R.id.titlebar_center);
        titleBarView.addView(item.view);
        if (item.clickable)
            item.view.setOnClickListener(titleBarView);
    }

    /**
     * 添加左侧主标题
     * @param baritem
     */
    public void addLeftMainTitle(BarItem baritem){
        TextView textView = new TextView(mcontext);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        TittleBarConfig.ITEM_HEIGHT,mcontext.getResources().getDisplayMetrics()));
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        if(titleBarView.findViewById(R.id.titlebar_left_1)==null){
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        }else{
            lp.addRule(RelativeLayout.RIGHT_OF, R.id.titlebar_left_1);
        }
        LinearLayout linear=new LinearLayout(mcontext);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setLayoutParams(lp);

        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,0,5.0f));
        linear.addView(textView);
        textView.setTextColor(mcontext.getResources().getColor(baritem.colorres));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TittleBarConfig.TEXTSIZE_TITLE_MAIN);
        textView.setId(R.id.titlebar_leftmaintitle);
        textView.setGravity(Gravity.CENTER);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TittleBarConfig.ITEM_BUTTON_PADDING,mcontext.getResources().getDisplayMetrics());
        textView.setPadding(padding, 0, padding, 0);
        textView.setText(mcontext.getResources().getString(baritem.res));
        titleBarView.addView(linear);
        if (baritem.clickable) {
            textView.setOnClickListener(titleBarView);
            textView.setBackgroundResource(baritem.backres);
        }
    }

    /**
     * 添加左侧次标题
     * @param baritem
     */
    public void addLeftSubTitle(BarItem baritem){
        if(titleBarView.findViewById(R.id.titlebar_leftmaintitle)==null)return;
        LinearLayout linear = (LinearLayout) titleBarView.findViewById(R.id.titlebar_leftmaintitle).getParent();

        TextView textView = new TextView(mcontext);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,0,3.0f));
        linear.addView(textView);
        textView.setTextColor(mcontext.getResources().getColor(baritem.colorres));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TittleBarConfig.TEXTSIZE_TITLE_SUB);
        textView.setId(R.id.titlebar_leftsubtitle);
        textView.setGravity(Gravity.TOP);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TittleBarConfig.ITEM_BUTTON_PADDING,mcontext.getResources().getDisplayMetrics());
        textView.setPadding(padding, 0, padding, 0);
        textView.setText(mcontext.getResources().getString(baritem.res));
        if (baritem.clickable) {
            textView.setOnClickListener(titleBarView);
            textView.setBackgroundResource(baritem.backres);
        }
    }

    /**
     * 移除掉左侧View
     */
    public void removeLeftView() {
        View left1 = this.titleBarView.findViewById(R.id.titlebar_left_1);
        if (left1 == null) return;
        this.titleBarView.removeView(left1);
        View left2 = this.titleBarView.findViewById(R.id.titlebar_left_2);
        if (left2 == null) return;
        this.titleBarView.removeView(left2);
    }

    /**
     * 移除掉右侧View
     */
    public void removeRightView() {
        View right1 = this.titleBarView.findViewById(R.id.titlebar_right_1);
        if (right1 == null) return;
        this.titleBarView.removeView(right1);
        View right2 = this.titleBarView.findViewById(R.id.titlebar_right_2);
        if (right2 == null) return;
        this.titleBarView.removeView(right2);
        View right3 = this.titleBarView.findViewById(R.id.titlebar_right_3);
        if (right3 == null) return;
        this.titleBarView.removeView(right3);
    }

    /**
     * 移除掉中间View
     */
    public void removeCenterView() {
        View centerview = this.titleBarView.findViewById(R.id.titlebar_center);
        if (centerview == null) return;
        this.titleBarView.removeView(centerview);
    }

}
