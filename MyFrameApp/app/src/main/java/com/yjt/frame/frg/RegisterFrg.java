package com.yjt.frame.frg;

import com.yjt.frame.R;
import com.yjt.frame.bean.BaseBean;
import java.util.Map;

/**
 * Created by yujiangtao on 16/3/27.
 */
public class RegisterFrg extends BaseWebFrg {


    @Override
    protected int getLayoutId() {
        return R.layout.frg_register;
    }

    @Override
    protected void initView() {
        titlebar.setCenterText(R.string.title_first_register);
        titlebar.setLeftImage(R.mipmap.back_icon_normal);
    }

    @Override
    protected boolean hasTitleBar() {
        return true;
    }

    @Override
    public void doError(int requestcode, Exception e) {

    }

    @Override
    public void doSuccess(int requestcode, BaseBean bean, String data) {

    }

    @Override
    public Class<?> getParserClass(int requestcode) {
        return null;
    }

    @Override
    public String getUrlSuffix(int requestcode) {
        return null;
    }

    @Override
    public Map<String, String> getParmas(int requestcode) {
        return null;
    }
}
