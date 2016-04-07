package com.yjt.frame.act;

import android.os.Bundle;

import com.yjt.frame.R;
import com.yjt.frame.app.First;
import com.yjt.frame.config.Constants;
import com.yjt.frame.util.FragmentUtil;
import com.yjt.frame.util.IntentUtil;
import com.yjt.frame.util.SharePrefUtil;

/**
 * Created by yujiangtao on 2016/1/5.
 */
public class FirstActivity extends BaseActivity {

    @Override
    protected void initControl(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
//        if (SharePrefUtil.getLoginStatus()) {
//            IntentUtil.start_activity(this, MainActivity.class,
//                    null, true, Constants.Animate_left_right);
//        } else
            FragmentUtil.toFragmentPush(getFrgManager(), First.LOGIN, null, true, Constants.Animate_none);
    }

    @Override
    protected boolean hasTitleBar() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        // TODO Auto-generated method stub
        return R.layout.container_layout;
    }
}

