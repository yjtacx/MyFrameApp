package com.yjt.frame.widget.titlebar.entity;

import com.yjt.frame.widget.titlebar.BarPosition;
import com.yjt.frame.widget.titlebar.BarType;

/**
 * Created by yujiangtao on 16/3/31.
 */
public class BaseBarEntity {
    public BarPosition barPosition;
    public BarType itemtype= BarType.TImageView;
    //是否可点击
    public boolean clickable=true;
    public int id;

}
