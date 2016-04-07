package com.yjt.frame.act;


import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.yjt.frame.app.Sub;
import com.yjt.frame.config.Constants;
import com.yjt.frame.manager.AppManager;
import com.yjt.frame.manager.FrgManager;
import com.yjt.frame.manager.SystemBarTintManager;
import com.yjt.frame.util.IntentUtil;
import com.yjt.frame.util.SharePrefUtil;
import com.yjt.frame.widget.titlebar.TitleBarView;
import com.yjt.frame.widget.titlebar.TitlebarCallback;

/**
 * FragmentActivity基类，
 * @version 1.0
 * @author yujiangtao
 *
 */
public abstract class BaseActivity extends FragmentActivity implements
		TitlebarCallback {
	public FrgManager frgmanager;
	public SystemBarTintManager tintManager=null;
	protected TitleBarView titleBarView=null;
	@Override
	protected void onCreate(final Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(null);
		AppManager.getAppManager().addActivity(this);
		frgmanager = new FrgManager(this);
		View rootview= LayoutInflater.from(this).inflate(getLayoutID(),null);
		setContentView(rootview);
		if(hasTitleBar()){
			titleBarView = new TitleBarView(this);
			((ViewGroup)rootview).addView(titleBarView,0);
			titleBarView.setTitleBarCall(this);
		}
		initSystemBar(this);
		if(bundle!=null) {
		}
		initControl(bundle);
	}


	public void initSystemBar(Activity activity) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(activity, true);
		}
		tintManager = new SystemBarTintManager(activity);
		tintManager.setStatusBarTintEnabled(true);
		// 使用颜色资源
        tintManager.setStatusBarTintResource(Color.TRANSPARENT);
	}

	@TargetApi(19)
	private static void setTranslucentStatus(Activity activity, boolean on) {
		Window win = activity.getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	protected abstract void initControl(Bundle savedInstanceState);
	protected abstract  boolean hasTitleBar();


	@Override
	protected void onStart() {
		super.onStart();
	}

//	protected void beforeSetContentView(){};

	protected abstract int getLayoutID();

	protected void titlebar_leftimgclick() {
	}
	protected void titlebar_lefttvclick(){
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStop() {
		super.onStop();

	}

	@Override
	protected void onPause() {
		super.onPause();
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			left_1_click(true);
		} else if (KeyEvent.KEYCODE_HOME == keyCode) {
			SharePrefUtil.setLoginStatus(false);
			AppManager.getAppManager().AppExit(this);
			return super.onKeyDown(keyCode, event);
		}
		return true;
	}
	
	public FrgManager getFrgManager(){
		return this.frgmanager;
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

	public void toSubPage(Sub sub, Bundle bundle) {
		toSubPage(sub,bundle, Constants.Animate_left_right);
	}
	public void toSubPage(Sub sub, Bundle bundle, int animatetype) {
		if(bundle==null)bundle=new Bundle();
		bundle.putSerializable("Sub", sub);
		IntentUtil.start_activity(this, SubpageActivity.class, bundle, false,
				animatetype);
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
	}


	@Override
	public void left_1_click(boolean backable) {
		titlebar_leftimgclick();
		if (backable)
			frgmanager.pop();
	}
	@Override
	public void left_2_click() {
	}

	@Override
	public void right_1_click() {

	}

	@Override
	public void right_2_click() {

	}

	@Override
	public void center_click() {

	}

	@Override
	public void right_3_click() {

	}
}
