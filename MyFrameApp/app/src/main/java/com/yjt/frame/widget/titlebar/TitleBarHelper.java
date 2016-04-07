package com.yjt.frame.widget.titlebar;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yjt.frame.R;
import com.yjt.frame.util.ToastUtil;

/**
 * Created by yujiangtao on 16/3/31.
 */
public class TitleBarHelper {
    TitleBarEleUtil titlebarUtil = null;
    Context mcontext = null;
    TitleBarView titleBarView;

    public TitleBarHelper(TitleBarView titleBarView) {
        this.mcontext = titleBarView.getContext();
        this.titleBarView = titleBarView;
        titlebarUtil = new TitleBarEleUtil(titleBarView);
    }

    public TitleBarEleUtil getTitlebarUtil() {
        return titlebarUtil;
    }

    /**
     * 设置标题栏的左中右View
     *
     * @param tp
     * @param id
     * @param res
     * @param type
     */

    public void setNormalView(BarPosition tp, int id, int res, BarType type) {
        BarItem item = new BarItem();
        item.itemtype = type;
        item.res = res;
        item.id = id;
        switch (tp) {
            case Left:
                item.clickable = true;
                setView(BarPosition.Left, titleBarView.findViewById(id), item);
//                setLeftView(item);
                break;
            case Center:
                item.clickable = false;
                setView(BarPosition.Center, titleBarView.findViewById(id), item);
//                setCenterView(item);
                break;
            case Right:
                item.clickable = true;
                setView(BarPosition.Right, titleBarView.findViewById(id), item);
//                setRightView(item);
                break;
            case LeftMainTitle:
                item.clickable = false;
                setView(BarPosition.LeftMainTitle, titleBarView.findViewById(R.id.titlebar_leftmaintitle), item);
//                setLeftMainTitle(item);
                break;
            case LeftSubTitle:
                item.clickable = false;
                setView(BarPosition.LeftSubTitle, titleBarView.findViewById(R.id.titlebar_leftsubtitle), item);
//                setLeftSubTitle(item);
                break;
        }
    }

    /**
     * 设置标题栏的左中右的自定义Biew
     *
     * @param tp
     * @param id
     * @param view
     */
    public void setCustomView(BarPosition tp, int id, View view) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        BarItem item = new BarItem();
        item.itemtype = BarType.TCustomView;
        item.view = view;
        item.clickable = false;
        item.id = id;
        switch (tp) {
            case Left:
                setView(BarPosition.Left, titleBarView.findViewById(id), item);
//                setLeftView(item);
                break;
            case Center:
                setView(BarPosition.Center, titleBarView.findViewById(id), item);
//                setCenterView(item);
                break;
            case Right:
                setView(BarPosition.Right, titleBarView.findViewById(id), item);
//                setRightView(item);
                break;
        }
    }
    /**
     * 添加或者更新view
     *
     * @param bp
     * @param view
     * @param item
     */
    public void setView(BarPosition bp, View view, BarItem item) {
        if (view == null) addView(bp, view, item);
        else updateView(bp, view, item);
    }


    public void addView(BarPosition tp, View view, BarItem baritem) {
        try {
            switch (tp) {
                case Left:
                    titlebarUtil.addLeftView(baritem);
                    break;
                case Center:
                    titlebarUtil.addCenterView(baritem);
                    break;
                case Right:
                    titlebarUtil.addRightView(baritem);
                    break;
                case LeftMainTitle:
                    titlebarUtil.addLeftMainTitle(baritem);
                    break;
                case LeftSubTitle:
                    titlebarUtil.addLeftSubTitle(baritem);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.showToast("View类型不一致");
        }
    }

    public void updateView(BarPosition tp, View view, BarItem baritem) {
        try {
            switch (baritem.itemtype) {
                case TBackText:
                case TTextView:
                    ((TextView) view).setText(mcontext.getResources().getString(baritem.res));
                    if (baritem.clickable) {
                        ((TextView) view).setOnClickListener(titleBarView);
                        ((TextView) view).setBackgroundResource(baritem.backres);
                    } else {
                        ((TextView) view).setOnClickListener(null);
                    }
                    break;
                case TImageView:
                    ((ImageView) view).setImageResource(baritem.res);
                    if (baritem.clickable) {
                        ((ImageView) view).setOnClickListener(titleBarView);
                        ((ImageView) view).setBackgroundResource(baritem.backres);
                    } else {
                        ((ImageView) view).setOnClickListener(null);
                    }
                    break;
                case TCustomView:
                    this.titleBarView.removeView(view);
                    switch (tp) {
                        case Left:
                            this.titlebarUtil.addLeftView(baritem);
                            break;
                        case Center:
                            titlebarUtil.addCenterView(baritem);
                            break;
                        case Right:
                            titlebarUtil.addRightView(baritem);
                            break;
                    }

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.showToast("View类型不一致");
        }

    }
}
