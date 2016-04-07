package com.yjt.frame.frg;

import android.support.v4.BuildConfig;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.yjt.frame.R;
import com.yjt.frame.util.CommonUtil;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yujiangtao on 16/3/28.
 */
public abstract class BaseWebViewFrg extends BaseFrg{

    protected WebView mWebView=null;
    private WebSettings settings=null;
    private boolean isOnPause=false;
    protected abstract void initview();

    @Override
    protected void initView() {
        mWebView = (WebView) findViewById(R.id.webview);
        settings = mWebView.getSettings();
        CommonUtil.hardwareAccelerate(getActivity(),mWebView);
        initWebView();
    }

    /**
     * 初始化WebView的相关属性
     */
    private void initWebView() {
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.setBackgroundColor(0); //设置WebView为透明
        try {
            settings = mWebView.getSettings();
            if (settings != null) {
                settings.setJavaScriptEnabled(true); //能够执行Javascript脚本
                settings.setCacheMode(WebSettings.LOAD_NO_CACHE); //关闭webview中缓存
                settings.setRenderPriority(WebSettings.RenderPriority.HIGH); //提高渲染的优先级
//                settings.setBlockNetworkImage(true); //把图片加载放在最后来加载渲染
                settings.setUseWideViewPort(false); //双击后变大变小
                settings.setSupportZoom(false);
                settings.setBuiltInZoomControls(false); //可触摸放大缩小
                settings.setLoadWithOverviewMode(true);

                //以下两句和硬件加速有关
                settings.setPluginState(WebSettings.PluginState.ON);
                settings.setAllowFileAccess(true);
                /**
                 * 用WebView显示图片，可使用这个参数 设置网页布局类型：
                 * 1、LayoutAlgorithm.NARROW_COLUMNS : 适应内容大小
                 * 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
                 */
                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
                settings.setUseWideViewPort(true);
            }
        } catch (Exception ex) {
        }
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
        initview();
    }

    /**
     * 释放掉webviwe所占内存
     */
    public void releaseAllWebViewCallback() {
        if (android.os.Build.VERSION.SDK_INT < 16) {
            try {
                Field field = WebView.class.getDeclaredField("mWebViewCore");
                field = field.getType().getDeclaredField("mBrowserFrame");
                field = field.getType().getDeclaredField("sConfigCallback");
                field.setAccessible(true);
                field.set(null, null);
            } catch (NoSuchFieldException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                Field sConfigCallback = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                if (sConfigCallback != null) {
                    sConfigCallback.setAccessible(true);
                    sConfigCallback.set(null, null);
                }
            } catch (NoSuchFieldException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            if (mWebView != null) {
                mWebView.getClass().getMethod("onPause").invoke(mWebView, (Object[]) null);
                isOnPause = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        try {
            settings.setJavaScriptEnabled(true);
            if (isOnPause) {
                if (mWebView != null) {
                    mWebView.getClass().getMethod("onResume").invoke(mWebView, (Object[]) null);
                }
                isOnPause  = false;
            }
        } catch (Exception e) { }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseAllWebViewCallback();
        if (mWebView != null) {
            mWebView.getSettings().setBuiltInZoomControls(true);
            mWebView.setVisibility(View.GONE);
            long delayTime = ViewConfiguration.getZoomControlsTimeout();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mWebView.destroy();
                    mWebView = null;
                }
            }, delayTime);

        }
        isOnPause = false;

    }

    public WebView getmWebView(){
        return mWebView;
    }



}
