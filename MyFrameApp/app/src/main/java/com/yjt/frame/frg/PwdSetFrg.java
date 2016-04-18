package com.yjt.frame.frg;

import com.yjt.frame.R;

/**
 * Created by yujiangtao on 16/4/5.
 */
public class PwdSetFrg extends BaseFrg {
    @Override
    protected int getLayoutId() {
        return R.layout.frg_pwdset;
    }

    @Override
    protected void initView() {
        titlebar.setLeftImage(R.mipmap.back_icon_normal);
        titlebar.setLeftMainSubText(R.string.title_leftmain,R.string.title_leftsub);
        titlebar.setRightImage(R.mipmap.actionbar_icon_add);
        titlebar.setRightImage(R.mipmap.actionbar_icon_add);
        titlebar.setRightImage(R.mipmap.actionbar_icon_add);
    }

    @Override
    protected boolean hasTitleBar() {
        return true;
    }
}
