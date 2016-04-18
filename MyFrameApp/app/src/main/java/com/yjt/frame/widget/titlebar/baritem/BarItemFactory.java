package com.yjt.frame.widget.titlebar.baritem;

import android.view.View;

import com.yjt.frame.widget.titlebar.TitleBarView;
import com.yjt.frame.widget.titlebar.barentity.BarCustomViewEntity;
import com.yjt.frame.widget.titlebar.barentity.BarImageEntity;
import com.yjt.frame.widget.titlebar.barentity.BarMainSubEntity;
import com.yjt.frame.widget.titlebar.barentity.BarTextEntity;
import com.yjt.frame.widget.titlebar.barentity.BaseBarEntity;

/**
 * Created by yujiangtao on 16/4/11.
 */
public class BarItemFactory {

    public View createBarItem(TitleBarView titleBarView, BaseBarEntity barItem){
        BarItem item=null;
        switch (barItem.itemtype){
            case TTextView:
            case TBackText:
                BarTextEntity textitem= (BarTextEntity) barItem;
                item= new TextViewItem(titleBarView, textitem);
                break;
            case TImageView:
                BarImageEntity imageitem = (BarImageEntity) barItem;
                item = new ImageViewItem(titleBarView,imageitem);
                break;
            case TProgressBar:
                break;
            case TCustomView:
                BarCustomViewEntity customitem= (BarCustomViewEntity) barItem;
                item = new CustomViewItem(titleBarView,customitem);
                break;
            case TMainSubText:
                BarMainSubEntity mainSubEntity = (BarMainSubEntity) barItem;
                item = new MainSubItem(titleBarView,mainSubEntity);
                break;
        }
        return item.getItem();
    }


}
