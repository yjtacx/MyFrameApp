package com.yjt.frame.act;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yjt.frame.R;
import com.yjt.frame.app.Sub;
import com.yjt.frame.config.Constants;
import com.yjt.frame.frg.AboutFrg;
import com.yjt.frame.frg.SystemSetFrg;
import com.yjt.frame.util.FragmentUtil;

/**
 * Created by yujiangtao on 2016/1/6.
 * 二级Activity 除了主Acitivity和FirstActivity，其他的Fragment都用SubpageActivity来管理
 */
public class SubpageActivity extends BaseActivity {
    @Override
    protected void initControl(Bundle savedInstanceState) {
        Sub sub=null;
        if(savedInstanceState!=null){
            sub = (Sub) savedInstanceState.getSerializable("Sub");
        }else{
            sub = (Sub) getIntent().getBundleExtra("Bundle").getSerializable("Sub");
        }
//        Fragment frg = Fragment.instantiate(this, sub.getClz().getName());
        FragmentUtil.toFragmentPush(frgmanager, sub, getIntent().getExtras(), true, Constants.Animate_none);
    }

    @Override
    protected boolean hasTitleBar() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.container_layout;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        try {
            outState.putSerializable("Sub",
                    (Sub) getIntent().getBundleExtra("Bundle").getSerializable("Sub"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent == null) return;
        setIntent(intent);
        //其他处理
    }

    @Override
    public void right_1_click() {
        super.right_1_click();
        if(frgmanager.getCurrentFrg() instanceof AboutFrg){

        }else if(frgmanager.getCurrentFrg() instanceof SystemSetFrg){

        }
    }
}
