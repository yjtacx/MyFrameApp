package com.yjt.frame.widget.titlebar.barentity;

import android.content.Context;
import android.view.View;

import com.yjt.frame.widget.titlebar.TitleBarView;
import com.yjt.frame.widget.titlebar.barHelper.BarPosition;
import com.yjt.frame.widget.titlebar.barHelper.BarType;
import com.yjt.frame.widget.titlebar.barHelper.TitleBarIDManager;

/**
 * Created by yujiangtao on 16/4/11.
 * 得到指定类型的View类型的对象BarEntity
 */
public class BarEntityFactory {

    private Context mcontext = null;
    private TitleBarView titleBarView;

    public BarEntityFactory(TitleBarView titleBarView) {
        this.mcontext = titleBarView.getContext();
        this.titleBarView = titleBarView;
    }


    public BarTextEntity getBarEntityText(BarPosition bp, String text, int textcolor,
                                          boolean isbacktext, boolean clickable) {
        BarTextEntity itemtext = new BarTextEntity();
        if (textcolor != 0) {
            textcolor = mcontext.getResources().getColor(textcolor);
            itemtext.textColor = textcolor;
        }
        itemtext.text = text;
        itemtext.isbacktext = isbacktext;
        itemtext.clickable = clickable;
        itemtext.barPosition = bp;
        if (isbacktext) itemtext.itemtype = BarType.TBackText;
        else itemtext.itemtype = BarType.TTextView;
        int id = getEntityId(bp);
        if (id == -1) return null;
        itemtext.id = id;
        return itemtext;
    }

    public BarImageEntity getBarEntityImage(BarPosition bp, int imgres) {
        BarImageEntity itemimage = new BarImageEntity();
        itemimage.itemtype = BarType.TImageView;
        itemimage.src = imgres;
        itemimage.barPosition = bp;
        int id = getEntityId(bp);
        if (id == -1) return null;
        itemimage.id = id;
        return itemimage;
    }

    public BarCustomViewEntity getBarEntityCustom(BarPosition bp, View view) {
        BarCustomViewEntity entity = new BarCustomViewEntity();
        entity.itemtype = BarType.TCustomView;
        entity.view = view;
        entity.barPosition = bp;
        int id = getEntityId(bp);
        if (id == -1) return null;
        entity.id = id;
        return entity;
    }

    public BarMainSubEntity getBarEntityMainSub(BarPosition bp,
                                                String maintext,
                                                String subtext,
                                                int textcolor) {
        return getBarEntityMainSub(bp, maintext, subtext, textcolor, false);
    }

    public BarMainSubEntity getBarEntityMainSub(BarPosition bp,
                                                String maintext,
                                                String subtext,
                                                int textcolor,
                                                boolean clickable) {
        BarMainSubEntity entity = new BarMainSubEntity();
        entity.itemtype = BarType.TMainSubText;
        entity.maintitletext = maintext;
        entity.subtitletext = subtext;
        entity.clickable = clickable;
        entity.barPosition = bp;
        if (textcolor != 0) {
            textcolor = mcontext.getResources().getColor(textcolor);
            entity.textColor = textcolor;
        }
        int id = getEntityId(bp);
        if (id == -1) return null;
        entity.id = id;
        return entity;
    }

    public int getEntityId(BarPosition position) {
        int id = -1;
        switch (position) {
            case Left:
                id = TitleBarIDManager.assignedId(titleBarView, BarPosition.Left);
                break;
            case Center:
                id = TitleBarIDManager.assignedId(titleBarView, BarPosition.Center);
                break;
            case Right:
                id = TitleBarIDManager.assignedId(titleBarView, BarPosition.Right);
                break;
        }
        return id;
    }
}
