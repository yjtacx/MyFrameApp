package com.yjt.frame.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yjt.frame.app.BaseEnum;
import com.yjt.frame.config.Constants;
import com.yjt.frame.manager.FrgManager;

/**
 * Created by yujiangtao on 2016/1/5.
 * fragment切换帮助类
 */
public class FragmentUtil {

    public static void toFragmentPush(FrgManager frgManager, BaseEnum enumcls, Boolean backable) {
        toFragmentPush(frgManager, enumcls, null, backable);
    }
    public static void toFragmentPush(FrgManager frgManager, BaseEnum enumcls, Bundle bundle, Boolean backable) {
        toFragmentPush(frgManager, enumcls, bundle, backable, Constants.Animate_left_right);
    }
    /***
     * push fragment
     * @param frgmanager
     * @param enumcls
     * @param backable
     * @param anim
     * @param bundle
     */
    public static void toFragmentPush(FrgManager frgmanager, BaseEnum enumcls,
                                      Bundle bundle, Boolean backable, int anim) {
        try {
            Fragment fragment = (Fragment) enumcls.getClz().newInstance();
            frgmanager.pushFragment(fragment, bundle, backable, anim);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "generate fragment error. by value:");
        }
    }



    public static void toFragmentAdd(FrgManager frgManager, BaseEnum enumcls, Boolean backable) {
        toFragmentAdd(frgManager, enumcls, null, backable, Constants.Animate_left_right);
    }
    public static void toFragmentAdd(FrgManager frgManager, BaseEnum enumcls, Bundle bundle, Boolean backable) {
        toFragmentAdd(frgManager, enumcls, bundle, backable, Constants.Animate_left_right);
    }
    /***
     * add fragment
     * @param frgmanager
     * @param enumcls
     * @param backable
     * @param anim
     * @param bundle
     */
    public static void toFragmentAdd(FrgManager frgmanager, BaseEnum enumcls, Bundle bundle, boolean backable, int anim) {
        try {
            Fragment fragment = (Fragment) enumcls.getClz().newInstance();
            frgmanager.addFragment(fragment, bundle, backable,anim);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "generate fragment error. by value:");
        }
    }

}
