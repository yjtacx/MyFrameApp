package com.yjt.frame.frg;

import android.os.Bundle;

import com.yjt.frame.R;

/**
 * Created by yujiangtao on 16/3/28.
 */
public class GuandianFrg extends BaseWebViewFrg {

    @Override
    protected int getLayoutId() {
        return R.layout.frg_gd;
    }

    @Override
    protected boolean hasTitleBar() {
        return false;
    }

    @Override
    protected void initview() {
        mWebView.loadUrl("https://www.baidu.com/");
    }
}
