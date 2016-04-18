package com.yjt.frame.widget.titlebar.barHelper;

import com.yjt.frame.R;
import com.yjt.frame.widget.titlebar.TitleBarView;

/**
 * Created by yujiangtao on 16/4/16.
 *
 * 负责给View分配ID，和返回已添加View的ID
 */
public class TitleBarIDManager {

    /**
     * 分配ITEM的ID
     * @return
     */
    public static int assignedId(TitleBarView titleBarView,BarPosition bp){
        switch (bp){
            case Left:
                return assignedIdLeftId(titleBarView);
            case Center:
                return R.id.titlebar_center;
            case Right:
                return assignedIdRightId(titleBarView);
        }
        return -1;
    }
    /**
     * 分配右侧ITEM的ID
     * @return
     */
    private static int assignedIdRightId(TitleBarView titleBarView){
        int id;
        if(titleBarView.findViewById(R.id.titlebar_right_1)==null)
            id= R.id.titlebar_right_1;
        else if(titleBarView.findViewById(R.id.titlebar_right_2)==null)
            id=R.id.titlebar_right_2;
        else if(titleBarView.findViewById(R.id.titlebar_right_3)==null)
            id=R.id.titlebar_right_3;
        else return -1;
        return id;
    }
    /**
     * 分配左侧ITEM的ID
     * @return
     */
    private static int assignedIdLeftId(TitleBarView titleBarView){
        if (titleBarView.findViewById(R.id.titlebar_left_1) == null)
            return R.id.titlebar_left_1;
        else if(titleBarView.findViewById(R.id.titlebar_left_2)==null)
            return R.id.titlebar_left_2;
        return -1;
    }


    /**
     * 获取左侧指定位置的view的id
     *
     * @param bo
     * @return
     */
    public static int getLeftId(BarOrder bo) {
        int id = R.id.titlebar_left_1;
        switch (bo) {
            case First:
                id = R.id.titlebar_left_1;
                break;
            case Second:
                id = R.id.titlebar_left_2;
                break;
        }
        return id;

    }

    /**
     * 获取右侧View的Id
     *
     * @param bo
     * @return
     */
    public static int getRightId(BarOrder bo) {
        int id = R.id.titlebar_right_1;
        switch (bo) {
            case First:
                id = R.id.titlebar_right_1;
                break;
            case Second:
                id = R.id.titlebar_right_2;
                break;
            case Third:
                id = R.id.titlebar_right_3;
                break;
        }
        return id;
    }

    /**
     * 获取中间View的Id
     *
     * @return
     */
    public static int getCenterId() {
        return R.id.titlebar_center;
    }
}
