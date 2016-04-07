package com.yjt.frame.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yjt.frame.R;
import com.yjt.frame.act.BaseActivity;
import com.yjt.frame.config.Constants;
import com.yjt.frame.manager.AppManager;

/**
 * Intent工具，处理Activity跳转，及跳转动画，数据传递等
 * @author yujiangtao
 *
 */
public class IntentUtil {
	/***
	 * 需要返回结果的跳转
	 * @param frg
	 * @param cls
	 * @param bundle
	 * @param request
     * @param animabletype
     */

	public static void start_activityForResult(Fragment frg, Class<?> cls,
											   Bundle bundle, int request, int animabletype) {
//		start_activityForResult(frg.getActivity(),cls,bundle,request,animabletype);
		Intent intent = new Intent();
		intent.putExtra("ANIMATETYPE",animabletype);
		intent.setClass(frg.getActivity(), cls);
		if (bundle == null) {
			bundle=new Bundle();
		}
		//传过去起始Activity的名称比如：”FirstActivity“
		bundle.putString("FROM", frg.getActivity().getClass().getSimpleName());
		intent.putExtra("Bundle",bundle);
		frg.startActivityForResult(intent, request);
		setAnimation(frg.getActivity(), animabletype);
	}
	public static void start_activityForResult(Activity activity, Class<?> cls,
											   Bundle bundle, int request, int animabletype) {
		Intent intent = new Intent();
		intent.putExtra("ANIMATETYPE",animabletype);
		intent.setClass(activity, cls);
		if (bundle == null) {
			bundle=new Bundle();
		}
		//传过去起始Activity的名称比如：”FirstActivity“
		bundle.putString("FROM", activity.getClass().getSimpleName());
		intent.putExtra("Bundle",bundle);
		activity.startActivityForResult(intent, request);
		setAnimation(activity, animabletype);
	}
	/***
	 * 不需要返回结果的跳转
	 * @param activity
	 * @param cls
	 * @param bundle
	 * @param finishable
	 * @param animabletype
	 */

	public static void start_activity(Activity activity, Class<?> cls,
									  Bundle bundle, boolean finishable, int animabletype) {
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		intent.putExtra("ANIMATETYPE",animabletype);
		if (bundle == null) {
			bundle=new Bundle();
		}
		//传过去起始Activity的名称比如：”FirstActivity“
		bundle.putString("FROM", activity.getClass().getSimpleName());
		intent.putExtra("Bundle",bundle);
		activity.startActivity(intent);
		setAnimation(activity, animabletype);
		if (finishable) {
			AppManager.getAppManager().finishActivity(activity);
		}
	}

	/****
	 * startActivity的动画
	 * 设定enterAnimation和exitAnimation
	 * @param activity
	 * @param atype
     */

	public static void setAnimation(Activity activity, int atype) {
		switch (atype) {
			case Constants.Animate_left_right:
				activity.overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
				break;
			case Constants.Animate_bottom_up:
				activity.overridePendingTransition(R.anim.pop_bottom_in,
						R.anim.alpha_nomal);
				break;
			case Constants.Animate_apha_out:
				activity.overridePendingTransition(R.anim.alpha_nomal,
						R.anim.alpha_out);

				break;
			case Constants.Animate_right_left:
				activity.overridePendingTransition(R.anim.push_right_in,
						R.anim.push_right_out);
				break;
			default:
				activity.overridePendingTransition(R.anim.alpha_nomal,
						R.anim.alpha_out);
				break;

		}

	}

	/**
	 * finishActivity的动画
	 * @param activity
	 * @param atype
     */
	public static void popAnimation(Activity activity, int atype) {

		switch (atype) {
			case Constants.Animate_left_right:
				activity.overridePendingTransition(R.anim.push_right_in,
						R.anim.push_right_out);
				break;
			case Constants.Animate_bottom_up:
				activity.overridePendingTransition(R.anim.alpha_nomal,
						R.anim.pop_bottom_out);
				break;
			case Constants.Animate_apha_out:
				activity.overridePendingTransition(R.anim.alpha_nomal,
						R.anim.alpha_out);

				break;
			case Constants.Animate_right_left:
				activity.overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
				break;
			default:
				activity.overridePendingTransition(R.anim.alpha_nomal,
						R.anim.alpha_out);
				break;

		}
	}


}
