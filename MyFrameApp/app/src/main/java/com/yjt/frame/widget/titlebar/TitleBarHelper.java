package com.yjt.frame.widget.titlebar;

import android.content.Context;
import android.view.View;

import com.yjt.frame.R;
import com.yjt.frame.widget.titlebar.baritem.BarItemFactory;
import com.yjt.frame.widget.titlebar.entity.BaseBarEntity;

/**
 * Created by yujiangtao on 16/3/30.
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
        View view=getItemView(item);
        titleBarView.addView(view);
    }

    public View getItemView(BaseBarEntity item){
        return barItemFactory.createBarItem(titleBarView,item);
    }


    /**
     * 获取ITEM的ID
     * @return
     */
    public int getId(BarPosition bp){
        switch (bp){
            case Left:
                return getLeftId();
            case Center:
                return R.id.titlebar_center;
            case Right:
                return getRightId();
        }
        return -1;
    }
    /**
     * 获取右侧ITEM的ID
     * @return
     */
    private int getRightId(){
        int id;
        if(titleBarView.findViewById(R.id.titlebar_right_1)==null)id= R.id.titlebar_right_1;
        else if(titleBarView.findViewById(R.id.titlebar_right_2)==null)id=R.id.titlebar_right_2;
        else if(titleBarView.findViewById(R.id.titlebar_right_3)==null)id=R.id.titlebar_right_3;
        else return -1;
        return id;
    }
    /**
     * 获取左侧ITEM的ID
     * @return
     */
    private int getLeftId(){
        if (titleBarView.findViewById(R.id.titlebar_left_1) == null)return R.id.titlebar_left_1;
        else if(titleBarView.findViewById(R.id.titlebar_left_2)==null)return R.id.titlebar_left_2;
        return -1;
    }
}
