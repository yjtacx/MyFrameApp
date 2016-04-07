package com.yjt.frame.app;

import android.app.Application;
import android.os.Environment;

import com.yjt.frame.crash.CrashApp;
import com.yjt.frame.db.tables.ACDaoImpl;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by yujiangtao on 2015/12/28.
 */
public class MyApplication extends Application{
    public static  MyApplication instance=null;
    public String FILE_SAVEPATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/myframeapp/";
    public String FILE_LOG_PATH=FILE_SAVEPATH+"log/";

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        new CrashApp(this).registerUncautExceptionHandler();
    }


    public Set<ACDaoImpl.DatabaseChangeListener> sets = new HashSet<ACDaoImpl.DatabaseChangeListener>();

    public void addlistener(ACDaoImpl.DatabaseChangeListener listener) {
        if (!sets.contains(listener)) {
            sets.add(listener);
        }
    }

    public void removelistener(ACDaoImpl.DatabaseChangeListener listener) {
        if (sets.contains(listener)) {
            sets.remove(listener);
        }
    }

}
