package com.yjt.frame.bean;

import java.io.Serializable;

/**
 * Created by yujiangtao on 2016/1/24.
 */
public class ACBean implements Comparable<ACBean>,Serializable{
    private int id;
    private String name="";
    private String account="";
    private String acpwd="";
    private String email="";
    private int typeid=0;
    private String remark="";
    private String createtime="";
    private String modifytime="";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAcpwd() {
        return acpwd;
    }

    public void setAcpwd(String acpwd) {
        this.acpwd = acpwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    @Override
    public int compareTo(ACBean another) {
//        if(this==null){
//            LogFileUtil.writeLog(MyApplication.instance.getApplicationContext(),"this==null\n");
//        }else if(this.getName()==null){
//            LogFileUtil.writeLog(MyApplication.instance.getApplicationContext(),"this.name==null\n");
//        }else if(this.getName().equals("")){
//            LogFileUtil.writeLog(MyApplication.instance.getApplicationContext(),"this.name为空\n");
//        }else{
//            LogFileUtil.writeLog(MyApplication.instance.getApplicationContext(),"this "+this.getName());
//        }
//        if(another==null){
//            LogFileUtil.writeLog(MyApplication.instance.getApplicationContext(),"another==null\n");
//        }else if(another.getName()==null){
//            LogFileUtil.writeLog(MyApplication.instance.getApplicationContext(),"another.name==null\n");
//        }else if(another.getName().equals("")){
//            LogFileUtil.writeLog(MyApplication.instance.getApplicationContext(),"another "+another.getName()+"\n");
//        }
        if(this==null||this.name==null||another==null||another.getName()==null)return 0;

        return name.compareTo(another.getName());
    }
}
