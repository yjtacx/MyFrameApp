package com.yjt.frame.bean;

import java.io.Serializable;

/**
 * Created by yujiangtao on 16/3/27.
 */
public class BaseBean implements Serializable {
    private int returncode;
    private String returnInfo;

    public int getReturncode() {
        return returncode;
    }

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    public String getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(String returnInfo) {
        this.returnInfo = returnInfo;
    }
}
