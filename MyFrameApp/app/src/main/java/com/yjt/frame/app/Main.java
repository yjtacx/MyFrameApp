package com.yjt.frame.app;


import com.yjt.frame.R;
import com.yjt.frame.frg.GuandianFrg;
import com.yjt.frame.frg.NewFrg;
import com.yjt.frame.frg.UserFrg;

/**
 * Created by yujiangtao on 2016/1/5.
 */
public enum  Main implements BaseEnum{
    // 新闻
    NEWTAB(0, R.string.main_tab_new, R.drawable.main_tab_icon_zixuan,
            NewFrg.class),
    // 观点
    GUANDIANTAB(1, R.string.main_tab_gd, R.drawable.main_tab_icon_lcs,
            GuandianFrg.class),
    // 自选
    ZIXUANTAB(2, R.string.main_tab_zx, R.drawable.main_tab_icon_zixuan,
            NewFrg.class),
    // 行情
    HANGQINGTAB(3, R.string.main_tab_hq, R.drawable.main_tab_icon_hangqing,
            NewFrg.class),
    //个人
    GERENTAB(4,R.string.main_tab_gr,R.drawable.main_tab_icon_personal, UserFrg.class);

    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    private Main(int idx, int resName,int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.clz = clz;
        this.resIcon = resIcon;
    }


    public void setIdx(int idx) {
        this.idx = idx;
    }


    public void setResName(int resName) {
        this.resName = resName;
    }


    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    @Override
    public Class<?> getClz() {
        return clz;
    }

    @Override
    public int getIdx() {
        return idx;
    }

    @Override
    public int getResName() {
        return resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    @Override
    public Main getPageByValue(int val) {
        for (Main p : values()) {
            if (p.getIdx() == val)
                return p;
        }
        return null;
    }
}
