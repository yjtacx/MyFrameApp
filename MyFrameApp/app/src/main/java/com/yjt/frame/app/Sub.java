package com.yjt.frame.app;

import com.yjt.frame.R;
import com.yjt.frame.frg.AboutFrg;
import com.yjt.frame.frg.GetstureFrg;
import com.yjt.frame.frg.PwdSetFrg;
import com.yjt.frame.frg.SystemSetFrg;
import com.yjt.frame.frg.TestTitleBarFrg;

/**
 * Created by yujiangtao on 2016/1/5.
 */
public enum Sub implements BaseEnum {
    SYSTEMSET(0, R.string.title_sub_add, SystemSetFrg.class),
    ABOUT(1,R.string.title_about, AboutFrg.class),
    PWDSET(2,R.string.title_leftmain,PwdSetFrg.class),
    GETTURESET(3,R.string.title_about,GetstureFrg.class),
    TestTitleBar(4,R.string.title_about,TestTitleBarFrg.class);
//    GETSTURESET(2,R.string.title_set, GestureSettingFrg.class);


    private int idx;
    private int resName;
    private Class<?> clz;

    private Sub(int idx, int resName, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.clz = clz;
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
    public BaseEnum getPageByValue(int val) {
        for (Sub p : values()) {
            if (p.getIdx() == val)
                return p;
        }
        return null;
    }

    @Override
    public int getIdx() {
        return idx;
    }

    @Override
    public int getResName() {
        return resName;
    }

}
