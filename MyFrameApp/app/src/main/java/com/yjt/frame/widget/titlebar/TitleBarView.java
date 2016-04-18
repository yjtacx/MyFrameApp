package com.yjt.frame.widget.titlebar;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.yjt.frame.R;
import com.yjt.frame.widget.titlebar.barHelper.BarOrder;
import com.yjt.frame.widget.titlebar.barHelper.BarPosition;
import com.yjt.frame.widget.titlebar.barHelper.TitleBarHelper;
import com.yjt.frame.widget.titlebar.barHelper.TitleBarIDManager;
import com.yjt.frame.widget.titlebar.barentity.BaseBarEntity;
import com.yjt.frame.widget.titlebar.statusbar.SystemBarTintManager;
import com.yjt.frame.widget.titlebar.barentity.BarCustomViewEntity;
import com.yjt.frame.widget.titlebar.barentity.BarEntityFactory;
import com.yjt.frame.widget.titlebar.barentity.BarImageEntity;
import com.yjt.frame.widget.titlebar.barentity.BarMainSubEntity;
import com.yjt.frame.widget.titlebar.barentity.BarTextEntity;
import com.yjt.frame.widget.titlebar.statusbar.SystemBarUtil;

/**
 * 自定义标题栏
 * 开放一些基本的API
 *
 * @author yujiangtao
 */
public class TitleBarView extends RelativeLayout implements OnClickListener {

    private Context mContext;
    boolean backable = true;
    private TitlebarCallback callback = null;
    private TitleBarHelper titlebarHelper = null;
    private BarEntityFactory barEntityFactory = null;
    private SystemBarTintManager tintManager=null;

    public TitleBarView(Context context) {
        this(context, null);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        RelativeLayout.LayoutParams lp = new
                RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        TitleBarConfig.DEFAULT_TITLEBAR_HEIGHT,
                        mContext.getResources().getDisplayMetrics()));
        this.setLayoutParams(lp);
        this.setBackgroundColor(Color.parseColor(TitleBarConfig.DEFAULT_TITLEBAR_COLOR));
        titlebarHelper = new TitleBarHelper(this);
        barEntityFactory = new BarEntityFactory(this);
    }

    /**
     * 设置状态栏颜色为标题栏颜色
     * @param act
     */
    public void setStatusBarEnable(Activity act){
        if(tintManager==null){
            tintManager = new SystemBarUtil(act,this).getSystemBarTintManager();
        }
        tintManager.setTintColor(Color.parseColor(TitleBarConfig.DEFAULT_TITLEBAR_COLOR));
    }

    /**
     * 恢复默认状态栏颜色为黑色
     */
    public void setStatusBarDefault(){
        tintManager.setTintColor(Color.parseColor("#000000"));
    }

    /**
     * 设置状态栏颜色
     * @param act
     * @param color
     */
    public void setStatusBarColor(Activity act,int color){
        if(tintManager==null){
            tintManager = new SystemBarUtil(act,this).getSystemBarTintManager();
        }
        tintManager.setTintColor(color);
    }
    /**
     * 设置状态栏颜色
     * @param act
     * @param res
     */
    public void setStatusBarResource(Activity act,int res){
        if(tintManager==null){
            tintManager = new SystemBarUtil(act,this).getSystemBarTintManager();
        }
        tintManager.setTintResource(res);
    }

    /**
     * 设置左边文字控件
     *
     * @param textres
     */
    public void setLeftText(int textres) {
        setLeftText(textres, 0);
    }
    /**
     * 设置左边文字控件
     * @param clickable
     * @param textres
     */
    public void setLeftText(int textres,boolean clickable){
        setLeftText(textres,0,clickable);
    }
    /**
     * 设置左边文字控件
     *
     * @param textres
     * @param textcolor
     */
    public void setLeftText(int textres, int textcolor) {
        setLeftText(textres,textcolor,true);
    }
    /**
     * 设置左边文字控件
     *
     * @param textres
     * @param textcolor
     * @param clickable
     */
    public void setLeftText(int textres, int textcolor,boolean clickable){
        BarTextEntity entity = barEntityFactory.getBarEntityText(BarPosition.Left,
                getResources().getString(textres),
                textcolor,false,clickable);
        titlebarHelper.addView(entity);
    }



    /**
     * 添加左侧有返回箭头的textView
     *
     * @param textres
     */
    public void setLeftBackText(int textres) {
        setLeftBackText(textres, 0);
    }

    /**
     * 添加左侧有返回箭头的textView
     *
     * @param textres
     * @param textcolor
     */
    public void setLeftBackText(int textres, int textcolor) {

        BarTextEntity entity = barEntityFactory.getBarEntityText(BarPosition.Left,
                getResources().getString(textres),
                textcolor, true,true);
        titlebarHelper.addView(entity);
    }

    /**
     * 设置左边图片控件
     *
     * @param imgres
     */
    public void setLeftImage(int imgres) {
        BarImageEntity barImageEntity = barEntityFactory
                .getBarEntityImage(BarPosition.Left, imgres);
        titlebarHelper.addView(barImageEntity);
    }


    /**
     * 设置左边自定义view控件
     *
     * @param view
     */
    public void setLeftView(View view) {
        BarCustomViewEntity entity = barEntityFactory
                .getBarEntityCustom(BarPosition.Left, view);
        titlebarHelper.addView(entity);
    }

    /**
     * 设置右边文字控件
     *
     * @param textres
     */
    public void setRightText(int textres) {
        setRightText(textres, 0);
    }
    /**
     * 设置右边文字控件
     * @param clickable
     * @param textres
     */
    public void setRightText(int textres,boolean clickable){
        setRightText(textres,0,clickable);
    }
    /**
     * 设置右边文字控件
     *
     * @param textres
     * @param textcolor
     */
    public void setRightText(int textres, int textcolor) {
        setRightText(textres,textcolor,true);
    }
    /**
     * 设置右边文字控件
     * @param clickable
     * @param textres
     * @param textcolor
     */
    public void setRightText(int textres, int textcolor,boolean clickable){
        BarTextEntity entity = barEntityFactory.getBarEntityText(BarPosition.Right,
                getResources().getString(textres),
                textcolor,false,clickable);
        titlebarHelper.addView(entity);
    }

    /**
     * 设置右边图片控件
     *
     * @param imgres
     */
    public void setRightImage(int imgres) {
        BarImageEntity barImageEntity = barEntityFactory
                .getBarEntityImage(BarPosition.Right, imgres);
        titlebarHelper.addView(barImageEntity);
    }


    /**
     * 设置右边自定义view控件
     *
     * @param view
     */
    public void setRightView(View view) {
        BarCustomViewEntity entity =
                barEntityFactory.getBarEntityCustom(BarPosition.Right, view);
        titlebarHelper.addView(entity);
    }

    /**
     * 设置中间文本控件
     *
     * @param textres
     */
    public void setCenterText(int textres) {
        setCenterText(textres, 0);
    }
    /**
     * 设置中间文字控件
     *
     * @param textres
     * @param textcolor
     */
    public void setCenterText(int textres, int textcolor) {
        setCenterText(textres,textcolor,false);
    }
    /**
     * 设置中间文本控件
     *
     * @param textres
     * @param clickable
     */
    public void setCenterText(int textres,boolean clickable) {
        setCenterText(textres, 0,clickable);
    }

    /**
     * 设置中间文本控件
     *
     * @param textres
     * @param textcolor
     */
    public void setCenterText(int textres, int textcolor,boolean clickable) {
        if (findViewById(R.id.titlebar_center) != null) return;
        BarTextEntity entity = barEntityFactory.getBarEntityText(BarPosition.Center,
                getResources().getString(textres),
                textcolor,false,clickable);
        titlebarHelper.addView(entity);
    }

    /**
     * 设置中间图片控件
     *
     * @param imageres
     */
    public void setCenterImage(int imageres) {
        if (findViewById(R.id.titlebar_center) != null) return;
        BarImageEntity barImageEntity = barEntityFactory
                .getBarEntityImage(BarPosition.Center, imageres);
        titlebarHelper.addView(barImageEntity);
    }

    /**
     * 设置中间自定义view控件
     *
     * @param view
     */
    public void setCenterView(View view) {
        if (findViewById(R.id.titlebar_center) != null) return;
        BarCustomViewEntity entity = barEntityFactory
                .getBarEntityCustom(BarPosition.Center, view);
        titlebarHelper.addView(entity);
    }

    /**
     * 添加左侧主次title类型的ITEM
     *
     * @param maintextres
     * @param subtextres
     */

    public void setLeftMainSubText(int maintextres, int subtextres) {
        setLeftMainSubText(maintextres, subtextres, 0);
    }
    /**
     * 添加左侧主次title类型的ITEM
     *
     * @param maintextres
     * @param subtextres
     * @param textcolor
     */
    public void setLeftMainSubText(int maintextres, int subtextres,int textcolor){
        setLeftMainSubText(maintextres,subtextres,textcolor,false);
    }
    /**
     * 添加左侧主次title类型的ITEM
     *
     * @param maintextres
     * @param subtextres
     * @param textcolor
     * @param clickable
     */

    public void setLeftMainSubText(int maintextres, int subtextres,
                                   int textcolor,boolean clickable) {
        BarMainSubEntity entity = barEntityFactory
                .getBarEntityMainSub(BarPosition.Left,
                        getResources().getString(maintextres),
                        getResources().getString(subtextres),
                        textcolor);
        titlebarHelper.addView(entity);
    }

    /**
     * 添加中间主次title类型的ITEM
     *
     * @param maintextres
     * @param subtextres
     */

    public void setCenterMainSubText(int maintextres, int subtextres) {
        setCenterMainSubText(maintextres, subtextres, 0);
    }
    /**
     * 添加中间主次title类型的ITEM
     *
     * @param maintextres
     * @param subtextres
     * @param textcolor
     */

    public void setCenterMainSubText(int maintextres, int subtextres,int textcolor) {
        setCenterMainSubText(maintextres,subtextres,textcolor,false);
    }


        /**
         * 添加中间主次title类型的ITEM
         *
         * @param maintextres
         * @param subtextres
         * @param textcolor
         * @param clickable
         */

    public void setCenterMainSubText(int maintextres, int subtextres,
                                     int textcolor,boolean clickable) {
        if (findViewById(R.id.titlebar_center) != null) return;
        BarMainSubEntity entity = barEntityFactory
                .getBarEntityMainSub(BarPosition.Center,
                        getResources().getString(maintextres),
                        getResources().getString(subtextres),
                        textcolor,clickable);

        titlebarHelper.addView(entity);
    }

    /**
     * 获取左侧View
     *
     * @param bo
     * @return
     */
    public View getLeftView(BarOrder bo) {
        int id = TitleBarIDManager.getLeftId(bo);
        if (findViewById(id) == null) {
            Toast.makeText(getContext(),
                    "none", Toast.LENGTH_SHORT).show();
            return null;
        }
        return findViewById(id);
    }

    /**
     * 获取右侧View
     *
     * @param bo
     * @return
     */
    public View getRightView(BarOrder bo) {
        int id = TitleBarIDManager.getRightId(bo);
        if (findViewById(id) == null) {
            Toast.makeText(getContext(),
                    "none", Toast.LENGTH_SHORT).show();
            return null;
        }
        return findViewById(id);
    }

    /**
     * 获取中间View
     *
     * @return
     */
    public View getCenterView() {
        int id = TitleBarIDManager.getCenterId();
        if (findViewById(id) == null){
            Toast.makeText(getContext(),
                    "none", Toast.LENGTH_SHORT).show();
            return null;
        }
        return findViewById(id);
    }

    /**
     * 设置标题栏背景色
     *
     * @param res
     */
    public void setTitleBarBackColor(int res) {
        this.setBackgroundResource(res);
    }

    /**
     * 设置标题栏事件回调类
     *
     * @param callback
     */
    public void setTitleBarCall(TitlebarCallback callback) {
        this.callback = callback;
    }

    /**
     * 设置是否返回的标识
     *
     * @param b
     */
    public void setBackable(boolean b) {
        this.backable = b;
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.titlebar_left_1:
                callback.left_1_click(backable);
                break;
            case R.id.titlebar_left_2:
                callback.left_2_click();
                break;
            case R.id.titlebar_center:
                callback.center_click();
                break;
            case R.id.titlebar_right_1:
                callback.right_1_click();
                break;
            case R.id.titlebar_right_2:
                callback.right_2_click();
                break;
            case R.id.titlebar_right_3:
                callback.right_3_click();
                break;
        }
    }

    /**
     * 移除掉左侧View
     */
    public void removeAllLeftView() {
        View left1 = findViewById(R.id.titlebar_left_1);
        if (left1 == null) return;
        removeView(left1);
        View left2 = findViewById(R.id.titlebar_left_2);
        if (left2 == null) return;
        removeView(left2);
    }

    /**
     * 移除掉右侧View
     */
    public void removeAllRightView() {
        View right1 = findViewById(R.id.titlebar_right_1);
        if (right1 == null) return;
        removeView(right1);
        View right2 = findViewById(R.id.titlebar_right_2);
        if (right2 == null) return;
        removeView(right2);
        View right3 = findViewById(R.id.titlebar_right_3);
        if (right3 == null) return;
        removeView(right3);
    }

    /**
     * 移除掉中间View
     */
    public void removeCenterView() {
        View centerview = findViewById(R.id.titlebar_center);
        if (centerview == null) return;
        removeView(centerview);
    }
    /**
     * 删除左侧View
     *
     * @param bo
     */
    public void removeLeftView(BarOrder bo) {
        switch (bo) {
            case First:
                if (findViewById(R.id.titlebar_left_1) != null)
                    removeViewAt(0);
                break;
            case Second:
                if (findViewById(R.id.titlebar_left_2) != null)
                    removeViewAt(1);
                break;
        }
    }
    /**
     * 删除左侧View
     *
     * @param bo
     */
    public void removeRightView(BarOrder bo) {
        switch (bo) {
            case First:
                if (findViewById(R.id.titlebar_right_1) != null)
                    removeViewAt(0);
                break;
            case Second:
                if (findViewById(R.id.titlebar_right_2) != null)
                    removeViewAt(1);
                break;
            case Third:
                if (findViewById(R.id.titlebar_right_3) != null)
                    removeViewAt(2);
                break;
        }
    }
    /**
     * 清空标题栏所有VIEW
     */
    public void clearViews() {
        if (getChildCount() > 0) {
            removeAllViews();
        }
    }

}
