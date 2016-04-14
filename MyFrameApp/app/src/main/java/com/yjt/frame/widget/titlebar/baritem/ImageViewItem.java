package com.yjt.frame.widget.titlebar.baritem;

import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.yjt.frame.R;
import com.yjt.frame.widget.titlebar.TitleBarConfig;
import com.yjt.frame.widget.titlebar.TitleBarView;
import com.yjt.frame.widget.titlebar.entity.BarImageEntity;

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
    }
    @Override
    protected void initItemView() {

        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TitleBarConfig.ITEM_BUTTON_PADDING,mcontext.getResources().getDisplayMetrics());
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
    protected RelativeLayout.LayoutParams getLeftLayoutParams() {
        RelativeLayout.LayoutParams lp =
                new RelativeLayout.LayoutParams(
                        (int) TypedValue.applyDimension(TypedValue.
                                COMPLEX_UNIT_DIP, TitleBarConfig.ITEM_HEIGHT,mcontext.getResources().getDisplayMetrics()),
                        (int) TypedValue.applyDimension(TypedValue.
                                COMPLEX_UNIT_DIP, TitleBarConfig.ITEM_HEIGHT,mcontext.getResources().getDisplayMetrics()));
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        if (id == R.id.titlebar_left_2)
            lp.addRule(RelativeLayout.RIGHT_OF, R.id.titlebar_left_1);
        else lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        return lp;
    }

    @Override
    protected RelativeLayout.LayoutParams getRightLayoutParams() { RelativeLayout.LayoutParams lp =
            new RelativeLayout.LayoutParams(
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                            TitleBarConfig.ITEM_HEIGHT, mcontext.getResources().getDisplayMetrics())
                    ,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                            TitleBarConfig.ITEM_HEIGHT, mcontext.getResources().getDisplayMetrics()));
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        if (id == R.id.titlebar_right_2)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_1);
        else if(id ==R.id.titlebar_right_3)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_2);
        else lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        return lp;
    }

    @Override
    protected RelativeLayout.LayoutParams getCenterLayoutParams() {
        RelativeLayout.LayoutParams lp = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        return lp;
    }

}
