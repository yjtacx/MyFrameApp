package com.yjt.frame.frg;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.yjt.frame.R;
import com.yjt.frame.util.CommonUtil;
import com.yjt.frame.widget.titlebar.TitleBarView;
import com.yjt.frame.widget.titlebar.TitlebarCallback;


/**
 * fragment基类，处理一些公用的操作，比如设置view显示，处理titlebar的回调和显示。
 *
 * @author yujiangtao
 * @create 2015-05-02
 */
public abstract class BaseFrg extends Fragment{

    /**
     * 返回layout文件的id
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view
     */
    protected abstract void initView();

//    private RightSlidLinearLayout linearLayout;
    /**
     * 标题栏
     */
    protected TitleBarView titlebar;

    private View rootView = null;
    protected InputMethodManager imm;
    protected Handler mHandler = null;

    public View getRootView() {
        return rootView;
    }

    protected View findViewById(int id) {
        return rootView.findViewById(id);
    }

    /**
     * 是否显示标题栏
     *
     * @param
     */
    protected abstract boolean hasTitleBar() ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    /**
     * 缓存的rootView需要判断是否已经被加过parent，
     * 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), null);
            if(hasTitleBar()){
                addTitleBar();
            }
//            if (rootView.findViewById(R.id.titlebar) != null) {
//                titlebar = (TitleBarView) rootView.findViewById(R.id.titlebar);
//                TitlebarCallback callback = (TitlebarCallback) getActivity();
//                titlebar.setTitleBarCall(callback);
//            }
//            ((BaseActivity) getActivity()).tintManager.setStatusBarTintResource(R.color.title_blue_color);
            initView();
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        return rootView;

    }
    private void addTitleBar(){
        titlebar = new TitleBarView(getActivity());
        ((ViewGroup)rootView).addView(titlebar,0);
        TitlebarCallback callback = (TitlebarCallback) getActivity();
        titlebar.setTitleBarCall(callback);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // 将上层触摸事件拦截
        rootView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return true;
            }
        });
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            CommonUtil.hideSoftKeyboard(getActivity().getCurrentFocus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }

    }

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
    }


}
