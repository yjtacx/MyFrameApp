package com.yjt.frame.frg;

import com.yjt.frame.R;
import com.yjt.frame.widget.titlebar.BarOrder;
import com.yjt.frame.widget.titlebar.TitleBarView;

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
        titlebar.setLeftMainText(R.string.title_leftmain);
        titlebar.setLeftSubText(R.string.title_leftsub);
        titlebar.setRightImage(R.mipmap.actionbar_icon_add);
        titlebar.setRightImage(R.mipmap.actionbar_icon_add);
        titlebar.setRightImage(R.mipmap.actionbar_icon_add);
    }

    @Override
    protected boolean hasTitleBar() {
        return true;
    }
}
