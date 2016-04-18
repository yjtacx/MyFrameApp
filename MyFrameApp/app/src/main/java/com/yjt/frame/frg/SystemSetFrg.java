package com.yjt.frame.frg;

import android.view.View;

import com.yjt.frame.R;
import com.yjt.frame.act.SubpageActivity;
import com.yjt.frame.app.Sub;
import com.yjt.frame.config.Constants;
import com.yjt.frame.util.FragmentUtil;

/**
 * Created by yujiangtao on 16/3/27.
 */
public class SystemSetFrg extends BaseFrg implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.frg_setting;
    }

    @Override
    protected void initView() {
//        titlebar.setCenterText(R.string.title_set);
        titlebar.setLeftBackText(R.string.title_set);
//        titlebar.setLeftImage(BarOrder.First,R.mipmap.back_icon_normal);
        findViewById(R.id.pwd_set_item).setOnClickListener(this);
        findViewById(R.id.gesture_set_item).setOnClickListener(this);
    }

    @Override
    protected boolean hasTitleBar() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pwd_set_item:
                FragmentUtil.toFragmentPush(((SubpageActivity)getActivity()).frgmanager,
                        Sub.PWDSET, null, true, Constants.Animate_left_right);
                break;
            case R.id.gesture_set_item:
                FragmentUtil.toFragmentPush(((SubpageActivity)getActivity()).frgmanager,
                        Sub.GETTURESET, null, true, Constants.Animate_left_right);
                break;
        }
    }
}
