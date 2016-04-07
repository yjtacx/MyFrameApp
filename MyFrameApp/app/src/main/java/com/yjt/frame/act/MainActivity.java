package com.yjt.frame.act;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.yjt.frame.R;
import com.yjt.frame.app.DoubleClickExitHelper;
import com.yjt.frame.app.Main;
import com.yjt.frame.app.Sub;
import com.yjt.frame.config.Constants;
import com.yjt.frame.frg.BaseWebViewFrg;
import com.yjt.frame.frg.GuandianFrg;
import com.yjt.frame.util.SharePrefUtil;
import com.yjt.frame.widget.SwitchTV;
import com.yjt.frame.widget.titlebar.BarOrder;


/**
 * Created by yujiangtao on 2015/12/30.
 */
public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener{
    private DoubleClickExitHelper mDoubleClickExitHelper;
    public FragmentTabHost mTabhost = null;

    private int currenttab = 0;

    @Override
    protected void initControl(Bundle savedInstanceState) {
        mDoubleClickExitHelper = new DoubleClickExitHelper(this);
        SharePrefUtil.setLoginStatus(true);
        titleBarView.setBackable(false);
        initTabHost();
        initTabs();
        if (savedInstanceState != null) {
//            currenttab = bundle.getInt("TAB", 0);
            onTabChanged(currenttab + "");
        } else {
            onTabChanged(currenttab + "");
        }
        mTabhost.setCurrentTab(currenttab);
        mTabhost.setOnTabChangedListener(this);

    }

    @Override
    protected boolean hasTitleBar() {
        return true;
    }

    private void initTabHost(){
        mTabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.tabcontent);
        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabhost.getTabWidget().setShowDividers(0);
        }
    }
    private void initTabs() {
        Main[] tabs = Main.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            Main mainTab = tabs[i];
            TabHost.TabSpec tab = mTabhost.newTabSpec(mainTab.getIdx() + "");
            View indicator = LayoutInflater.from(this)
                    .inflate(R.layout.tab_indicator, null);
            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            Drawable drawable = this.getResources().getDrawable(
                    mainTab.getResIcon());
            title.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null,
                    null);
            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(indicator);
            tab.setContent(new TabHost.TabContentFactory() {

                @Override
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);
                }
            });
            mTabhost.addTab(tab, mainTab.getClz(), null);
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    public void onTabChanged(String tabId) {
        // TODO Auto-generated method stub
        final int size = mTabhost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v = mTabhost.getTabWidget().getChildAt(i);
            if (i == mTabhost.getCurrentTab()) {
                v.setSelected(true);
            } else {
                v.setSelected(false);
            }
        }
        // if (tabId.equals(getString(Main.MAINTAB.getResName()))
        int idx = Integer.parseInt(tabId);
        currenttab = idx;
        if(titleBarView.getChildCount()>0)titleBarView.clearViews();

        switch (idx) {
            case 0:
                titleBarView.setCenterText(Main.values()[0].getResName());
                titleBarView.setLeftText(R.string.title_set);
                titleBarView.setLeftImage(R.mipmap.search_icon);
                titleBarView.setRightImage(R.mipmap.search_icon);
                titleBarView.setRightImage(R.mipmap.actionbar_icon_add);
                break;
            case 1: {
                titleBarView.setLeftText( R.string.title_set);
                titleBarView.setLeftImage(R.mipmap.search_icon);
                titleBarView.setRightImage( R.mipmap.search_icon);
                titleBarView.setRightImage( R.mipmap.actionbar_icon_add);
                SwitchTV switchv = new SwitchTV(this);
                switchv.setSwitchClickCallback(new SwitchTV.SwitchClickCallback() {
                    @Override
                    public void callback(int p) {

                    }
                });
                switchv.downBtn.performClick();
                titleBarView.setCenterView(switchv);
            }
                break;
            case 2:
                titleBarView.setCenterText(Main.values()[2].getResName());
                titleBarView.setRightImage(R.mipmap.fku);
                titleBarView.setRightImage(R.mipmap.gwe);
                break;
            case 3: {
                SwitchTV switchv = new SwitchTV(this);
                switchv.setSwitchClickCallback(new SwitchTV.SwitchClickCallback() {
                    @Override
                    public void callback(int p) {

                    }
                });
                switchv.downBtn.performClick();
                titleBarView.setCenterView(switchv);
                titleBarView.setRightText( R.string.title_set);
            }
                break;
            case 4:
                titleBarView.setCenterText(Main.values()[4].getResName());
                titleBarView.setRightText(R.string.title_set);
                titleBarView.setRightText(R.string.goon);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //如果是webviewfrg,能返回就返回
            if(getCurrentFragment() instanceof BaseWebViewFrg){
                BaseWebViewFrg webViewFrg= (BaseWebViewFrg) getCurrentFragment();
                if(webViewFrg.getmWebView().canGoBack()){
                    webViewFrg.getmWebView().goBack();
                    return true;
                }
            }
            // 是否退出应用
            if (Constants.KeyDoubleExit) {
                return mDoubleClickExitHelper.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }



    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void left_1_click(boolean backable) {
        super.left_1_click(backable);
        //按下面这种方式去写
        if(getCurrentFragment() instanceof GuandianFrg){
            toSubPage(Sub.TestTitleBar,null);
        }else
            toSubPage(Sub.SYSTEMSET,null);
    }

    @Override
    public void right_1_click() {
        super.right_1_click();
        //按下面这种方式去写
        if(getCurrentFragment() instanceof GuandianFrg){
            toSubPage(Sub.ABOUT,null);
        }else{
            toSubPage(Sub.SYSTEMSET,null);
        }
    }

    @Override
    public void right_2_click() {
        super.right_2_click();
        if(getCurrentFragment() instanceof GuandianFrg){
            toSubPage(Sub.ABOUT,null);
        }else{
            toSubPage(Sub.SYSTEMSET,null);
        }
    }


    public Fragment getCurrentFragment() {
        return Fragment.instantiate(this, Main.values()[currenttab].getClz().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
