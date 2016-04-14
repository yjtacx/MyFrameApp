package com.yjt.frame.widget.titlebar.baritem;

import com.yjt.frame.widget.titlebar.TitleBarView;
import com.yjt.frame.widget.titlebar.entity.BarTextEntity;

/**
 * Created by yujiangtao on 16/4/11.
 */
public class BackTextViewItem extends TextViewItem {


    public BackTextViewItem(TitleBarView titleBarView, BarTextEntity textbean){
        super(titleBarView,textbean);
        this.backable = true;
    }
}
