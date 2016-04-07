package com.yjt.frame.crash;

/**
 * Created by yujiangtao on 2016/1/6.
 */

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.yjt.frame.app.MyApplication;

import java.io.File;


public abstract class AbstractCrashReportHandler implements CrashListener {
    private Context mContext;

    public AbstractCrashReportHandler(Context context) {
        mContext = context;
    }
    public void registerUncautExceptionHandler(){
        CrashHandler handler = CrashHandler.getInstance();
        handler.init(getLogDir(mContext), this);
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }

    protected File getLogDir(Context context) {
        return new File(MyApplication.instance.FILE_SAVEPATH+"log/", "crash.txt");
    }

    protected abstract void sendReport(File file,String exmsg);

    @Override
    public void afterSaveCrash(File file,Throwable tx) {
        sendReport(file,getCrashContent(mContext,tx));
    }

    private String getCrashContent(Context mcontext,Throwable ex){
        StringBuilder sb = new StringBuilder();
        sb.append(buildTitle(mcontext));
        sb.append(getCrashReport(mcontext,ex));
        sb.append(getDeviceInfo(mcontext));
        return sb.toString();
    }
    public String buildTitle(Context context) {
        StringBuilder sb = new StringBuilder();
        PackageManager pm = context.getPackageManager();
        ApplicationInfo ai = context.getApplicationInfo();
        sb.append("Crash Log: ").append('\n');
        sb.append(context.getPackageManager().getApplicationLabel(context.getApplicationInfo()));

        try {
            PackageInfo pi = pm.getPackageInfo(ai.packageName, 0);
            sb.append("Version Code: ").append(pi.versionCode).append('\n');
            sb.append("Version Name: ").append(pi.versionName).append('\n');
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public String getDeviceInfo(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append('\n').append("设备相关信息：").append('\n');
        sb.append("Board: ").append(Build.BOARD).append('\n');
        sb.append("BOOTLOADER: ").append(Build.BOOTLOADER).append('\n');
        sb.append("BRAND: ").append(Build.BRAND).append('\n');
        sb.append("CPU_ABI: ").append(Build.CPU_ABI).append('\n');
        sb.append("CPU_ABI2: ").append(Build.CPU_ABI2).append('\n');
        sb.append("DEVICE: ").append(Build.DEVICE).append('\n');
        sb.append("DISPLAY: ").append(Build.DISPLAY).append('\n');
        sb.append("FINGERPRINT: ").append(Build.FINGERPRINT).append('\n');
        sb.append("HARDWARE: ").append(Build.HARDWARE).append('\n');
        sb.append("HOST: ").append(Build.HOST).append('\n');
        sb.append("ID: ").append(Build.ID).append('\n');
        sb.append("MANUFACTURER: ").append(Build.MANUFACTURER).append('\n');
        sb.append("PRODUCT: ").append(Build.PRODUCT).append('\n');
        sb.append("TAGS: ").append(Build.TAGS).append('\n');
        sb.append("TYPE: ").append(Build.TYPE).append('\n');
        sb.append("USER: ").append(Build.USER).append('\n');
        return sb.toString();
    }
    /**
     * 获取APP崩溃异常报告
     *
     * @param ex
     * @return
     */
    private String getCrashReport(Context context, Throwable ex) {
        StringBuilder exceptionStr = new StringBuilder();
        exceptionStr.append("Android: " + android.os.Build.VERSION.RELEASE
                + " (" + android.os.Build.MODEL + ")\n\n");
        exceptionStr.append("异常信息: \n");
        exceptionStr.append("Exception: " + ex.getMessage() + "\n\n");
        exceptionStr.append("堆栈信息: \n");
        StackTraceElement[] elements = ex.getStackTrace();
        for (int i = 0; i < elements.length; i++) {
            exceptionStr.append(elements[i].toString() + "\n");
        }
        return exceptionStr.toString();
    }
}
