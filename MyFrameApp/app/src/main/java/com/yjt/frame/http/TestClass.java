package com.yjt.frame.http;

import com.yjt.frame.bean.BaseBean;
import com.yjt.frame.bean.LoginBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yujiangtao on 2015/12/30.
 */
public class TestClass extends BaseWebClass{

    public TestClass(){
        super();
    }

    public void getData(){
        httptool.doGet();
//        httptool.doPost();
    }


    @Override
    public void doError(int requestcode, Exception e) {
        System.out.println("exception:"+e.getMessage());
    }

    @Override
    public void doSuccess(int requestcode, BaseBean bean, String data) {
        LoginBean lb= (LoginBean) bean;
        System.out.println("result:"+lb.toString());
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
        Map<String,String> map=new HashMap<String,String>();
        map.put("username", "13381177226");
        map.put("password", "123456");
        return map;
    }
}
