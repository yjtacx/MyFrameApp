package com.yjt.frame.widget.titlebar;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yjt.frame.R;


/**
 * 自定义标题栏
 *
 * @author yujiangtao
 */
public class TitleBarView extends RelativeLayout implements OnClickListener {

    private Context mContext;
    boolean backable = true;
    //	private RelativeLayout rootview=null;
    private TitlebarCallback callback = null;
    private TitleBarHelper titleBarHelper = null;

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
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, TittleBarConfig.TITLEBAR_HEIGHT,
                        mContext.getResources().getDisplayMetrics()));
//		LayoutInflater.from(mContext).inflate(R.layout.titlebar, this);
        this.setLayoutParams(lp);
        this.setBackgroundColor(Color.parseColor(TittleBarConfig.TITLEBAR_BACK_COLOR));
        this.setBackgroundResource(R.color.main_blue_color);
//		rootview = this;
//		rootview= (RelativeLayout) findViewById(R.id.titlebar_rootview);
        titleBarHelper = new TitleBarHelper(this);
    }

    /**
     * 设置左边文字控件
     *
     * @param textres
     */
    public void setLeftText(int textres) {
        int id=getLeftId();
       if(id==-1)return;
        titleBarHelper.setNormalView(BarPosition.Left, id, textres, BarType.TTextView);
    }

    /**
     * 添加左侧有返回箭头的textView
     *
     * @param textres
     */
    public void setLeftBackText(int textres) {
        titleBarHelper.setNormalView(BarPosition.Left, R.id.titlebar_left_1, textres, BarType.TBackText);
    }

    /**
     * 设置左边图片控件
     *
     * @param imgres
     */
    public void setLeftImage(int imgres) {
        int id=getLeftId();
        if(id==-1)return;
        titleBarHelper.setNormalView(BarPosition.Left, id, imgres, BarType.TImageView);
    }

//    private int getLeftId(BarOrder bo){
//        int id;
//        if (bo == BarOrder.First) id = R.id.titlebar_left_1;
//        else {
//            if (findViewById(R.id.titlebar_left_1) == null) {
//                return -1;
//            }
//            id = R.id.titlebar_left_2;
//        }
//        return id;
//    }
    private int getLeftId(){
        if (findViewById(R.id.titlebar_left_1) == null)return R.id.titlebar_left_1;
        else if(findViewById(R.id.titlebar_left_2)==null)return R.id.titlebar_left_2;
        return -1;
    }
    /**
     * 设置左边自定义view控件
     *
     * @param view
     */
    public void setLeftView(View view) {
        int id=getLeftId();
        if(id==-1)return;
        titleBarHelper.setCustomView(BarPosition.Left, id, view);
    }

    /**
     * 设置右边文字控件
     *
     * @param textres
     */
    public void setRightText(int textres) {
        int id = getRightId();
        if (id == -1) return;
        titleBarHelper.setNormalView(BarPosition.Right, id, textres, BarType.TTextView);
    }

    /**
     * 可深度定制添加View
     * @param bp
     * @param item
     */
    public void addView(BarPosition bp,BarItem item){
        int id=-1;
        switch (bp){
            case Left:
                id= getLeftId();
                break;
            case Right:
                id=getRightId();
                break;
            case Center:
                id=R.id.titlebar_center;
                break;
            case LeftMainTitle:
                id=R.id.titlebar_leftmaintitle;
                break;
            case LeftSubTitle:
                id = R.id.titlebar_leftsubtitle;
                break;
        }
        if(id==-1)return;
        item.id=id;
        titleBarHelper.addView(bp,findViewById(id),item);

    }
        private int getRightId(){
            int id;
            if(findViewById(R.id.titlebar_right_1)==null)id= R.id.titlebar_right_1;
            else if(findViewById(R.id.titlebar_right_2)==null)id=R.id.titlebar_right_2;
            else if(findViewById(R.id.titlebar_right_3)==null)id=R.id.titlebar_right_3;
            else return -1;
            return id;
        }
//    private int getRightId(BarOrder p) {
//        int id;
//        switch (p) {
//            case First:
//                id = R.id.titlebar_right_1;
//                break;
//            case Second:
//                if (findViewById(R.id.titlebar_right_1) == null) {
//                    ToastUtil.showToast("请先添加第一个按钮");
//                    return -1;
//                }
//                id = R.id.titlebar_right_2;
//                break;
//            case Third:
//                if (findViewById(R.id.titlebar_right_1) == null) {
//                    ToastUtil.showToast("请先添加第一个按钮");
//                    return -1;
//                }
//                if (findViewById(R.id.titlebar_right_2) == null) {
//                    ToastUtil.showToast("请先添加第二个按钮");
//                    return -1;
//                }
//                id = R.id.titlebar_right_3;
//                break;
//            default:
//                id = R.id.titlebar_right_1;
//                break;
//        }
//        return id;
//    }

    /**
     * 设置右边图片控件
     *
     * @param imgres
     */
    public void setRightImage(int imgres) {
        int id = getRightId();
        if (id == -1) return;
        titleBarHelper.setNormalView(BarPosition.Right, id, imgres, BarType.TImageView);
    }

    /**
     * 设置右边自定义view控件
     *
     * @param view
     */
    public void setRightView(View view) {
        int id = getRightId();
        if (id == -1) return;
        titleBarHelper.setCustomView(BarPosition.Right, id, view);
    }

    /**
     * 设置中间文本控件
     *
     * @param textres
     */
    public void setCenterText(int textres) {
        titleBarHelper.setNormalView(BarPosition.Center, R.id.titlebar_center, textres, BarType.TTextView);
    }

    /**
     * 设置中间图片控件
     *
     * @param imageres
     */
    public void setCenterImage(int imageres) {
        titleBarHelper.setNormalView(BarPosition.Center, R.id.titlebar_center, imageres, BarType.TImageView);
    }

    /**
     * 设置中间自定义view控件
     *
     * @param view
     */
    public void setCenterView(View view) {
        titleBarHelper.setCustomView(BarPosition.Center, R.id.titlebar_center, view);
    }
    /**
     * 设置标题栏左侧主标题
     *
     * @param textres
     */
    public void setLeftMainText(int textres) {
        titleBarHelper.setNormalView(BarPosition.LeftMainTitle,
                R.id.titlebar_leftmaintitle, textres, BarType.TTextView);
    }

    public View getLeftView(BarOrder bo){
        int id=R.id.titlebar_left_1;
        switch (bo){
            case First:
                id=R.id.titlebar_left_1;
                break;
            case Second:
                id=R.id.titlebar_left_2;
                break;
        }
        if(findViewById(id)==null)return null;
        return findViewById(id);
    }
    public View getRightView(BarOrder bo){
        int id=R.id.titlebar_right_1;
        switch (bo){
            case First:
                id=R.id.titlebar_right_1;
                break;
            case Second:
                id=R.id.titlebar_right_2;
                break;
            case Third:
                id = R.id.titlebar_right_3;
                break;
        }
        if(findViewById(id)==null)return null;
        return findViewById(id);
    }

    public TextView getLeftMainText(){
        int id=R.id.titlebar_leftmaintitle;
        if(findViewById(id)==null)return null;
        return (TextView) findViewById(id);
    }
    public TextView getLeftSubText(){
        int id=R.id.titlebar_leftsubtitle;
        if(findViewById(id)==null)return null;
        return (TextView) findViewById(id);
    }
    public View getCenterView(){
        int id=R.id.titlebar_center;
        if(findViewById(id)==null)return null;
        return (TextView) findViewById(id);
    }
    /**
     * 设置标题栏左侧次标题
     *
     * @param textres
     */
    public void setLeftSubText(int textres) {
        titleBarHelper.setNormalView(BarPosition.LeftSubTitle,
                R.id.titlebar_leftsubtitle, textres, BarType.TTextView);
    }

    public TitleBarHelper getTitleBarHelper() {
        return this.titleBarHelper;
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
     * 删除中间View
     */
    public void removeCenterView(){
        if(findViewById(R.id.titlebar_center)!=null)
        this.removeView(findViewById(R.id.titlebar_center));
    }


    /**
     * 删除左侧View
     * @param bo
     */
    public void removeLeftView(BarOrder bo){
        switch (bo){
            case First:
                if(findViewById(R.id.titlebar_left_1)!=null)
                this.removeViewAt(0);
                break;
            case Second:
                if(findViewById(R.id.titlebar_left_2)!=null)
                this.removeViewAt(1);
                break;
        }
    }

    /**
     * 删除右侧View
     * @param bo
     */
    public void removeRightView(BarOrder bo) {
        switch (bo) {
            case First:
                this.removeView(findViewById(R.id.titlebar_right_1));
                break;
            case Second:
                this.removeView(findViewById(R.id.titlebar_right_2));
                break;
            case Third:
                this.removeView(findViewById(R.id.titlebar_right_3));
                break;
        }
    }

    public void clearViews() {
        if (getChildCount() > 0){
            removeAllViews();
        }
    }

}
