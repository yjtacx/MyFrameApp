package com.yjt.frame.frg;

import com.yjt.frame.R;

/**
 * Created by yujiangtao on 16/3/31.
 */
public class AboutFrg extends BaseWebViewFrg {

    @Override
    protected int getLayoutId() {
        return R.layout.frg_about;
    }

    @Override
    protected boolean hasTitleBar() {
        return true;
    }

    @Override
    protected void initview() {
        titlebar.setCenterText(R.string.title_about);
        titlebar.setLeftImage(R.mipmap.back_icon_normal);
        titlebar.setRightText(R.string.title_sub_add);
        mWebView.loadUrl("http://home.baidu.com/");
    }
}
