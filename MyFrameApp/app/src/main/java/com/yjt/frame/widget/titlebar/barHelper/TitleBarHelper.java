package com.yjt.frame.widget.titlebar.barHelper;

import android.content.Context;
import android.view.View;
import com.yjt.frame.widget.titlebar.TitleBarView;
import com.yjt.frame.widget.titlebar.baritem.BarItemFactory;
import com.yjt.frame.widget.titlebar.barentity.BaseBarEntity;

/**
 * Created by yujiangtao on 16/3/30.
 * 负责根据原料BaseBarEntity,
 * 调度barItemFactory，得到正确的View，并添加到标题栏
 *
 */
public class TitleBarHelper {
    private Context mcontext = null;
    private TitleBarView titleBarView;
    private BarItemFactory barItemFactory=null;


    public TitleBarHelper(TitleBarView titlebar) {
        this.titleBarView = titlebar;
        this.mcontext = titlebar.getContext();
        barItemFactory = new BarItemFactory();

    }

    /**
     * 添加标题栏ITEM
     * @param item
     */
    public void addView(BaseBarEntity item){
        if(item==null)return;
        View view=getItemView(item);
        titleBarView.addView(view);
    }

    public View getItemView(BaseBarEntity item){
        if(item ==null)return null;
        return barItemFactory.createBarItem(titleBarView,item);
    }



}
