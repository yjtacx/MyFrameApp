package com.yjt.frame.widget.titlebar.baritem;

import android.view.View;

import com.yjt.frame.widget.titlebar.BarPosition;
import com.yjt.frame.widget.titlebar.TitleBarView;
import com.yjt.frame.widget.titlebar.baritem.BackTextViewItem;
import com.yjt.frame.widget.titlebar.baritem.BarItem;
import com.yjt.frame.widget.titlebar.baritem.BarMainSubItem;
import com.yjt.frame.widget.titlebar.baritem.CustomViewItem;
import com.yjt.frame.widget.titlebar.baritem.ImageViewItem;
import com.yjt.frame.widget.titlebar.baritem.TextViewItem;
import com.yjt.frame.widget.titlebar.entity.BarCustomViewEntity;
import com.yjt.frame.widget.titlebar.entity.BarImageEntity;
import com.yjt.frame.widget.titlebar.entity.BarMainSubEntity;
import com.yjt.frame.widget.titlebar.entity.BarTextEntity;
import com.yjt.frame.widget.titlebar.entity.BaseBarEntity;

/**
 * Created by yujiangtao on 16/4/11.
 */
public class BarItemFactory {

    public View createBarItem(TitleBarView titleBarView, BaseBarEntity barItem){
        BarItem item=null;
        switch (barItem.itemtype){
            case TTextView:
                BarTextEntity textitem= (BarTextEntity) barItem;
                item= new TextViewItem(titleBarView, textitem);
                break;
            case TImageView:
                BarImageEntity imageitem = (BarImageEntity) barItem;
                item = new ImageViewItem(titleBarView,imageitem);
                break;
            case TBackText:
                BarTextEntity backtextitem= (BarTextEntity) barItem;
                backtextitem.backable=true;
                item = new BackTextViewItem(titleBarView,backtextitem);
                break;
            case TProgressBar:
                break;
            case TCustomView:
                BarCustomViewEntity customitem= (BarCustomViewEntity) barItem;
                item = new CustomViewItem(titleBarView,customitem);
                break;
            case TMainSubText:
                BarMainSubEntity mainSubEntity = (BarMainSubEntity) barItem;
                item = new BarMainSubItem(titleBarView,mainSubEntity);
                break;
        }

        return item.getItem();
    }


}
