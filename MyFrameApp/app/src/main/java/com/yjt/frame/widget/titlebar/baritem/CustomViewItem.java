package com.yjt.frame.widget.titlebar.baritem;

import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

import com.yjt.frame.widget.titlebar.barHelper.BarType;
import com.yjt.frame.widget.titlebar.TitleBarConfig;
import com.yjt.frame.widget.titlebar.TitleBarView;
import com.yjt.frame.widget.titlebar.barentity.BarCustomViewEntity;

/**
 * Created by yujiangtao on 16/4/11.
 */
public class CustomViewItem extends BarItem {

    private View view;


    public CustomViewItem(TitleBarView titleBarView, BarCustomViewEntity entity) {
        super(titleBarView);
        if(entity.view.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            this.view = entity.view;
        }else{
            RelativeLayout.LayoutParams parmas = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout layout = new RelativeLayout(mcontext);
            layout.setLayoutParams(parmas);
            layout.addView(entity.view);
            this.view=layout;
        }
        this.bp = entity.barPosition;
        this.id = entity.id;
        this.clickable = entity.clickable;
        barType = BarType.TCustomView;
    }

    @Override
    protected void buildView() {
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TitleBarConfig.DEFAULT_ITEM_BUTTON_PADDING, mcontext.getResources().getDisplayMetrics());
        RelativeLayout.LayoutParams lp = null;
        switch (bp) {
            case Left:
                lp =  getLeftLayoutParams();
                view.setPadding(padding, 0, padding, 0);
                break;
            case Right:
                lp = getRightLayoutParams();
                view.setPadding(padding, 0, padding, 0);
                break;
            case Center:
                lp = getCenterLayoutParams();
                break;
            default:
                throw new RuntimeException("BarPosition 不存在");

        }
        view.setLayoutParams(lp);
        view.setId(id);
        if (clickable) {
            view.setClickable(true);
            view.setOnClickListener(titleBarView);
            view.setBackgroundDrawable(backstateD);
        }

    }

    @Override
    protected View getItemView() {
        return view;
    }

    @Override
    protected int getWidth() {
        return RelativeLayout.LayoutParams.WRAP_CONTENT;
    }



}
