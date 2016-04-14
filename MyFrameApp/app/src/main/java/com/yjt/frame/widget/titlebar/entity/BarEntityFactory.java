package com.yjt.frame.widget.titlebar.entity;

import android.view.View;

import com.yjt.frame.widget.titlebar.BarPosition;
import com.yjt.frame.widget.titlebar.BarType;
import com.yjt.frame.widget.titlebar.TitleBarHelper;

/**
 * Created by yujiangtao on 16/4/11.
 */
public class BarEntityFactory {
    private TitleBarHelper titlebarHelper = null;
    
    public BarEntityFactory (TitleBarHelper titlebarHelper){
        this.titlebarHelper = titlebarHelper;
    }

//    public BaseBarEntity getBarEntity(BarType type) {
//        switch (type) {
//            case TTextView:
//                return new BarTextEntity();
//            case TImageView:
//                return new BarImageEntity();
//            case TCustomView:
//                return new BarCustomViewEntity();
//            case TProgressBar:
//                return new BaseBarEntity();
//            case TBackText:
//                BarTextEntity it = new BarTextEntity();
//                it.backable = true;
//                return it;
//            case TMainSubText:
//                return new BarMainSubEntity();
//            default:
//                return new BarTextEntity();
//        }
//    }
    public BarTextEntity getBarEntityText(BarPosition bp,String text,int textcolor,
                                          boolean backable){
        BarTextEntity itemtext= new BarTextEntity();
        itemtext.text=text;
        if(textcolor!=0){
            itemtext.textColor=textcolor;
        }
       itemtext.backable=backable;
        if(backable)itemtext.itemtype = BarType.TBackText;
        else itemtext.itemtype=BarType.TTextView;
        int id=-1;
        switch (bp){
            case Left:
                id=titlebarHelper.getId(BarPosition.Left);
                if(id==-1)return null;
                itemtext.barPosition=BarPosition.Left;
                break;
            case Center:
                id=titlebarHelper.getId(BarPosition.Center);
                if(id==-1)return null;
                itemtext.barPosition=BarPosition.Center;
                break;
            case Right:
                id = titlebarHelper.getId(BarPosition.Right);
                if(id==-1)return null;
                itemtext.barPosition=BarPosition.Right;
                break;
        }
        itemtext.id=id;
        return itemtext;
    }

    public BarImageEntity getBarEntityImage(BarPosition bp,int imgres){
        BarImageEntity itemimage= new BarImageEntity();
        itemimage.itemtype=BarType.TImageView;
        itemimage.src=imgres;
        int id=-1;
        switch (bp){
            case Left:
                id=titlebarHelper.getId(BarPosition.Left);
                if(id==-1)return null;
                itemimage.barPosition=BarPosition.Left;
                break;
            case Center:
                id=titlebarHelper.getId(BarPosition.Center);
                if(id==-1)return null;
                itemimage.barPosition=BarPosition.Center;
                break;
            case Right:
                id = titlebarHelper.getId(BarPosition.Right);
                if(id==-1)return null;
                itemimage.barPosition=BarPosition.Right;
                break;
        }
        itemimage.id=id;
        return itemimage;
    }

    public BarCustomViewEntity getBarEntityCustom(BarPosition bp,View view){
        BarCustomViewEntity entity = new BarCustomViewEntity();
        entity.itemtype=BarType.TCustomView;
        entity.view=view;
        int id=-1;
        switch (bp){
            case Left:
                id=titlebarHelper.getId(BarPosition.Left);
                if(id==-1)return null;
                entity.barPosition=BarPosition.Left;
                break;
            case Center:
                id=titlebarHelper.getId(BarPosition.Center);
                if(id==-1)return null;
                entity.barPosition=BarPosition.Center;
                break;
            case Right:
                id = titlebarHelper.getId(BarPosition.Right);
                if(id==-1)return null;
                entity.barPosition=BarPosition.Right;
                break;
        }
        entity.id=id;
        return entity;
    }

    public BarMainSubEntity getBarEntityMainSub(BarPosition bp,
                                                String maintext,String subtext,int textcolor){
        BarMainSubEntity entity = new BarMainSubEntity();
        entity.itemtype=BarType.TMainSubText;
        entity.maintitletext=maintext;
        entity.subtitletext=subtext;
        entity.clickable=false;
        if(textcolor != 0){
            entity.textColor=textcolor;
        }
        int id=-1;
        switch (bp){
            case Left:
                id=titlebarHelper.getId(BarPosition.Left);
                if(id==-1)return null;
                entity.barPosition=BarPosition.Left;
                break;
            case Center:
                id=titlebarHelper.getId(BarPosition.Center);
                if(id==-1)return null;
                entity.barPosition=BarPosition.Center;
                break;
            case Right:
                id = titlebarHelper.getId(BarPosition.Right);
                if(id==-1)return null;
                entity.barPosition=BarPosition.Right;
                break;
        }
        entity.id=id;
        return entity;
    }

}
