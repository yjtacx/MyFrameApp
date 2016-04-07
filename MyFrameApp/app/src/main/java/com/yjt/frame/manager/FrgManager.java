package com.yjt.frame.manager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;

import com.yjt.frame.R;
import com.yjt.frame.act.BaseActivity;
import com.yjt.frame.config.Constants;
import com.yjt.frame.util.IntentUtil;


/**
 * fragment管理类，用于管理fragment入栈出栈，切换效果等操作。
 * @version 1.0
 * @author yujiangtao
 *
 */
public class FrgManager implements OnBackStackChangedListener {
	private FragmentActivity activity;
	private FragmentManager manager;

	private Fragment currentFragment = null;
	/**
	 *得到当前的fragment
	 * */
	public Fragment getCurrentFrg(){
		return currentFragment;
	}

	public FrgManager(FragmentActivity act) {
		this.activity = act;
		manager = activity.getSupportFragmentManager();
		manager.addOnBackStackChangedListener(this);
	}

	public void pushFragment(Fragment frg, Bundle bundle) {
		pushFragment(frg, bundle, true);
	}

	public void pushFragment(Fragment frg, boolean backable) {
		pushFragment(frg, null, backable);
	}

	public void pushFragment(Fragment frg, Bundle bundle, boolean backable) {
		pushFragment(frg, bundle, backable, Constants.Animate_left_right);
	}

	/**
	 * add的方式添加fragment显示
	 * @param frg
	 * @param bundle
	 * @param backable
	 * @param animatetype
     */
	public void addFragment(Fragment frg, Bundle bundle, boolean backable,
							int animatetype) {
		FragmentTransaction trasaction = manager.beginTransaction();
		setAnimation(trasaction, animatetype);
		if(bundle==null){
			bundle = new Bundle();
		}
		bundle.putString("FROM",frg.getClass().getSimpleName());
		frg.setArguments(bundle);
		trasaction
				.add(R.id.container_view, frg, frg.getClass().getSimpleName())
		;
		if (backable) {
			trasaction.addToBackStack(null);
		}
		trasaction.commit();
		currentFragment = frg;
	}

	/**
	 * fragment至于栈顶显示
	 * @param frg
	 * @param bundle
	 * @param backable
	 * @param animatetype
     */

	public void pushFragment(Fragment frg, Bundle bundle, boolean backable,
							 int animatetype) {
		FragmentTransaction trasaction = manager.beginTransaction();
		setAnimation(trasaction, animatetype);
		if(bundle==null){
			bundle = new Bundle();
		}
		bundle.putString("FROM",frg.getClass().getSimpleName());
		frg.setArguments(bundle);
		trasaction.replace(R.id.container_view, frg,
				frg.getClass().getSimpleName());
		if (backable) {
			trasaction.addToBackStack(null);
		}
		trasaction.commitAllowingStateLoss();
		currentFragment = frg;
	}

	/**
	 * fragment之间跳转和回退的动画
	 * @param trasanction
	 * @param atype
     */
	private void setAnimation(FragmentTransaction trasanction, int atype) {
		switch (atype) {
			case Constants.Animate_left_right:
				trasanction.setCustomAnimations(R.anim.push_left_in,
						R.anim.push_left_out, R.anim.push_right_in,
						R.anim.push_right_out);
				break;
			case Constants.Animate_bottom_up:
				trasanction.setCustomAnimations(R.anim.pop_bottom_in,
						R.anim.pop_bottom_out, R.anim.pop_bottom_in,
						R.anim.pop_bottom_out);
				break;
			case Constants.Animate_apha_out:
				trasanction.setCustomAnimations(R.anim.alpha_nomal,
						R.anim.alpha_out, R.anim.alpha_nomal, R.anim.alpha_out);

				break;
			default:
//			trasanction.setCustomAnimations(R.anim.push_left_in,
//					R.anim.push_left_out, R.anim.push_left_in,
//					R.anim.push_left_out);
				break;

		}
	}

	/**
	 * 返回到上一个fragment，若是最后一个，actiity finish掉
	 */
	public void pop() {
		if (manager.getBackStackEntryCount() > 1) {
			currentFragment = manager.getFragments().get(
					manager.getFragments().size() - 2);
			manager.popBackStack();
		} else {
//			int animatetype=((BaseActivity) activity).animatetype;
			int animatetype = activity.getIntent().getIntExtra("ANIMATETYPE",0);
			AppManager.getAppManager().finishActivity();
			IntentUtil.popAnimation(activity, animatetype);
		}
	}

	/**
	 * 会退到TAG为tag的fragment
	 * @param tag
     */
	public void popto(String tag) {
		Fragment frg = manager.findFragmentByTag(tag);
		if (frg != null) {
			manager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
			currentFragment = frg;
		}
	}

	/**
	 *移除所有的fragment，退出当前Activity
	 */
	public void exist(){
		manager.popBackStack(null, 0);
		AppManager.getAppManager().finishActivity();
	}


	@Override
	public void onBackStackChanged() {
		// TODO Auto-generated method stub

	}

}
