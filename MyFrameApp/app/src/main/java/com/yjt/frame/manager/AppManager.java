package com.yjt.frame.manager;

import java.util.Stack;
import android.app.Activity;
import android.content.Context;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * @version 1.0
 * @author yujiangtao
 */
public class AppManager {

	private static Stack<Activity> activityStack;
	private static AppManager instance;
	private static final Object syncRoot = new Object();

	private AppManager() {
	}

	/**
	 * 单一实例
	 */
	public static AppManager getAppManager() {
		if (instance == null) {
			synchronized (syncRoot) {
				if(instance==null){
					instance = new AppManager();
				}
			}
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity() {
		if(activityStack==null)return null;
		Activity activity = activityStack.lastElement();
		return activity;
	}

	public Activity secondActivity() {
		int size = activityStack.size();
		if (size > 1) {
			return activityStack.elementAt(size - 2);
		}
		return null;
	}

	/**
	 * 获取指定的Activity
	 *
	 * @author kymjs
	 */
	public static Activity getActivity(Class<?> cls) {
		if (activityStack != null)
			for (Activity activity : activityStack) {
				if (activity.getClass().equals(cls)) {
					return activity;
				}
			}
		return null;
	}


	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		if(activityStack==null)return;
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				Activity activity = activityStack.get(i);
				if (!activity.isFinishing()) {
					activity.finish();
				}
			}
		}
		activityStack.clear();
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity(Class<?> cls) {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				Activity activity = activityStack.get(i);
				if (!activity.getClass().getSimpleName()
						.equals(cls.getSimpleName())) {
					if (!activity.isFinishing())
						activity.finish();
				}
			}
		}
		activityStack.clear();

	}
	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			/*
			 * Intent intent = new Intent(context, MainActivity.class);
			 * PendingIntent restartIntent = PendingIntent.getActivity( context,
			 * 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK); //退出程序 AlarmManager
			 * mgr =
			 * (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
			 * mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
			 * restartIntent); // 1秒钟后重启应用
			 */
			// 杀死该应用进程
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
		} catch (Exception e) {
		}
	}
}