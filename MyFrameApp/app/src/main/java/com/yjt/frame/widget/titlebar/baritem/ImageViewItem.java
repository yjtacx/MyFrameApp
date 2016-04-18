package com.yjt.frame.widget.titlebar.baritem;

import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yjt.frame.widget.titlebar.barHelper.BarType;
import com.yjt.frame.widget.titlebar.TitleBarConfig;
import com.yjt.frame.widget.titlebar.TitleBarView;
import com.yjt.frame.widget.titlebar.barentity.BarImageEntity;

/**
 * Created by yujiangtao on 16/4/11.
 */
public class ImageViewItem extends BarItem {

    private ImageView imageView;
    private int src;

    public ImageViewItem(TitleBarView titleBarView, BarImageEntity itemImage){
        super(titleBarView);
        this.bp=itemImage.barPosition;
        this.src=itemImage.src;
        this.id=itemImage.id;
        this.clickable=itemImage.clickable;
        imageView = new ImageView(mcontext);
        barType= BarType.TImageView;
    }
    @Override
    protected void buildView() {
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TitleBarConfig.DEFAULT_ITEM_BUTTON_PADDING,mcontext.getResources().getDisplayMetrics());
        RelativeLayout.LayoutParams lp;
        switch(bp){
            case Left:
                lp=getLeftLayoutParams();
                imageView.setPadding(padding, padding, padding, padding);
                break;
            case Right:
                lp=getRightLayoutParams();
                imageView.setPadding(padding, padding, padding, padding);
                break;
            case Center:
                lp=getCenterLayoutParams();
                break;
            default:throw new RuntimeException("BarPosition 不存在");

        }
        imageView.setLayoutParams(lp);
        imageView.setId(id);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(src);

        if (clickable) {
            imageView.setClickable(true);
            imageView.setOnClickListener(titleBarView);
            imageView.setBackgroundDrawable(backstateD);
        }
    }

    @Override
    protected View getItemView() {
        return imageView;
    }

    @Override
    protected int getWidth() {
        return (int) TypedValue.applyDimension(TypedValue.
                                COMPLEX_UNIT_DIP, TitleBarConfig.DEFAULT_ITEM_HEIGHT,mcontext.getResources().getDisplayMetrics());
    }

    @Override
    protected int getHeight() {
        return (int) TypedValue.applyDimension(TypedValue.
                                COMPLEX_UNIT_DIP, TitleBarConfig.DEFAULT_ITEM_HEIGHT,mcontext.getResources().getDisplayMetrics());
    }



}
