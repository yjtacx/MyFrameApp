package com.yjt.frame.widget.titlebar.baritem;

import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

import com.yjt.frame.R;
import com.yjt.frame.widget.titlebar.TitleBarConfig;
import com.yjt.frame.widget.titlebar.TitleBarView;
import com.yjt.frame.widget.titlebar.entity.BarCustomViewEntity;

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
    }

    @Override
    protected void initItemView() {
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                TitleBarConfig.ITEM_BUTTON_PADDING, mcontext.getResources().getDisplayMetrics());
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
    protected RelativeLayout.LayoutParams getLeftLayoutParams() {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (id == R.id.titlebar_left_2)
            lp.addRule(RelativeLayout.RIGHT_OF, R.id.titlebar_left_1);
        else lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        return lp;
    }

    @Override
    protected RelativeLayout.LayoutParams getRightLayoutParams() {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (id == R.id.titlebar_right_2)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_1);
        else if (id == R.id.titlebar_right_3)
            lp.addRule(RelativeLayout.LEFT_OF, R.id.titlebar_right_2);

        else lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        return lp;
    }

    @Override
    protected RelativeLayout.LayoutParams getCenterLayoutParams() {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        return lp;
    }
}
