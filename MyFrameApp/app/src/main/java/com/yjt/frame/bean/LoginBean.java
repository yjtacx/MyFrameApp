package com.yjt.frame.bean;

/**
 * Created by yujiangtao on 16/3/27.
 */
public class LoginBean extends BaseBean {

    private String userid=null;
    private String picurl=null;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
}
