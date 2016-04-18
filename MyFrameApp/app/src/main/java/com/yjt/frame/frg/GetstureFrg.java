package com.yjt.frame.frg;

import com.yjt.frame.R;

/**
 * Created by yujiangtao on 16/4/5.
 */
public class GetstureFrg extends BaseFrg {
    @Override
    protected int getLayoutId() {
        return R.layout.frg_gesture;
    }

    @Override
    protected void initView() {
        titlebar.setLeftImage(R.mipmap.back_icon_normal);
        titlebar.setRightImage(R.mipmap.actionbar_icon_add);
        titlebar.setRightImage(R.mipmap.actionbar_icon_add);
        titlebar.setRightImage(R.mipmap.actionbar_icon_add);
    }

    @Override
    protected boolean hasTitleBar() {
        return true;
    }
}
