package com.yjt.frame.widget.titlebar;

/**
 * 顶部栏回调接口
 * @author yujiangtao
 *
 */
public interface TitlebarCallback {
	/**
	 * 左边图片点击
	 * @param backable 是否可返回
     */
	void left_1_click(boolean backable);
	void left_2_click();
	void right_1_click();
	void right_2_click();
	void right_3_click();
	void center_click();
}
