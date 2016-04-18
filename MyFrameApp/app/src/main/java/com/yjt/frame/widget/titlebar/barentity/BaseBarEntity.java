package com.yjt.frame.widget.titlebar.barentity;

import com.yjt.frame.widget.titlebar.barHelper.BarPosition;
import com.yjt.frame.widget.titlebar.barHelper.BarType;

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
