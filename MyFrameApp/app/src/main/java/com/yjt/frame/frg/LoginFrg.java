package com.yjt.frame.frg;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.yjt.frame.R;
import com.yjt.frame.act.BaseActivity;
import com.yjt.frame.act.MainActivity;
import com.yjt.frame.app.First;
import com.yjt.frame.bean.BaseBean;
import com.yjt.frame.bean.LoginBean;
import com.yjt.frame.config.Constants;
import com.yjt.frame.http.URLS;
import com.yjt.frame.util.FragmentUtil;
import com.yjt.frame.util.IntentUtil;
import com.yjt.frame.util.SharePrefUtil;
import com.yjt.frame.util.StringUtils;
import com.yjt.frame.util.ToastUtil;
import com.yjt.frame.widget.ResizeLayout;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆页面
 *
 * @author yujiangt
 * @created 2015-06-12
 */
public class LoginFrg extends BaseWebFrg implements OnClickListener {
    EditText namelogin_;
    EditText pwdlogin_;
    private String username;
    private String pwd;
    private static final int BIGGER = 1;
    private static final int SMALLER = 2;
    private static final int MSG_RESIZE = 1;

    int flag = 0;
    private int screenheight;

    /***
     * 初始化handler
     */
    private void initHandler(){
        if(mHandler!=null){
            mHandler.removeCallbacksAndMessages(null);
            mHandler=null;
        }
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_RESIZE: {
                        if (msg.arg1 == BIGGER) {
                            findViewById(R.id.bottom_login_linear).setVisibility(View.VISIBLE);
                        } else {
                            findViewById(R.id.bottom_login_linear).setVisibility(View.GONE);
                        }
                    }
                    break;

                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    @Override
    public void doError(int requestcode, Exception e) {

    }



    @Override
    public void doSuccess(int requestcode, BaseBean bean, String data) {
        LoginBean loginres = (LoginBean) bean;
        if (loginres == null) {
            ToastUtil.showToastShort("解析数据失败");
        } else {
            if (loginres.getReturncode() == 0) {
                ToastUtil.showToastShort("登陆成功");
                if (loginres.getUserid() == null) {
                    ToastUtil.showToastShort("该接口还没有发布");
                }
                SharePrefUtil.setTocken(loginres.getUserid());
                SharePrefUtil.setUserPWD(null);
                if (!username.equals(SharePrefUtil.getUserName())) {
                    SharePrefUtil.setUserName(username);
                }
                IntentUtil.start_activity(getActivity(), MainActivity.class,
                        null, true, Constants.Animate_left_right);
            } else {
                ToastUtil.showToastShort(loginres.getReturnInfo());
            }
        }
    }

    @Override
    public Class<?> getParserClass(int requestcode) {
        return LoginBean.class;
    }

    @Override
    public String getUrlSuffix(int requestcode) {
        return URLS.URL_LOGIN;
    }

    @Override
    public Map<String, String> getParmas(int requestcode) {
        username = namelogin_.getText().toString().trim();
        pwd = pwdlogin_.getText().toString().trim();
        if (StringUtils.isEmpty(username)) {
            ToastUtil.showToastShort(getResources().getString(
                    R.string.name_not_empty));
            return null;
        }
        if (StringUtils.isEmpty(pwd)) {
            ToastUtil.showToastShort(getResources().getString(
                    R.string.pwd_not_empty));
            return null;
        }
        Map<String, String> param = new HashMap<String, String>();
        param.put("username", username);
        param.put("password", pwd);
        return param;
    }



    @Override
    protected int getLayoutId() {
        // TODO Auto-generated method stub
        return R.layout.frg_login;
    }

    @Override
    protected void initView() {
        // TODO Auto-generated method stub
        titlebar.setCenterText(R.string.title_first_login);

        namelogin_ = (EditText) findViewById(R.id.name_login);
        pwdlogin_ = (EditText) findViewById(R.id.pwd_login);
        namelogin_.setText(SharePrefUtil.getUserName());
        pwdlogin_.setText(SharePrefUtil.getUserPWD());
        initHandler();
        setListener();
    }

    @Override
    protected boolean hasTitleBar() {
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setListener() {
        findViewById(R.id.login_images).setOnClickListener(this);
        findViewById(R.id.register_login).setOnClickListener(this);
        findViewById(R.id.forgetpwd_login).setOnClickListener(this);

        namelogin_.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;//监听前的文本
            private int editStart;
            private int editEnd;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            @Override
            public void afterTextChanged(Editable mEditText) {
                editStart = namelogin_.getSelectionStart();
                editEnd = namelogin_.getSelectionEnd();
                if (temp.length() == 0) {
                    pwdlogin_.setText("");
                }
            }
        });

        ((ResizeLayout) findViewById(R.id.sizelayout)).setOnResizeListener(new ResizeLayout.OnResizeListener() {
            @Override
            public void OnResize(int w, int h, int oldw, int oldh) {
                int change = BIGGER;
                if (screenheight == 0) screenheight = h;
                if (h < screenheight) {
                    change = SMALLER;
                }
                Message msg = new Message();
                msg.what = MSG_RESIZE;
                msg.arg1 = change;
                mHandler.sendMessage(msg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.login_images:
                IntentUtil.start_activity(getActivity(), MainActivity.class,
                        null, true, Constants.Animate_left_right);
//                httptool.doPost();
                break;
            case R.id.register_login:
                FragmentUtil.toFragmentPush(((BaseActivity) getActivity()).frgmanager, First.REGISTER, true);
                break;
            case R.id.forgetpwd_login:
//                ((FirstActivity) getActivity()).toFragmentPush(((BaseActivity) getActivity()).getFrgManager(),First.FORGET,true);
                break;
        }
    }


}
