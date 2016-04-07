package com.yjt.frame.crash;

/**
 * Created by yujiangtao on 2016/1/6.
 */
import android.os.Looper;


import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CrashHandler implements UncaughtExceptionHandler {
    private static final CrashHandler sHandler = new CrashHandler();
    private static final UncaughtExceptionHandler sDefaultHandler = Thread
            .getDefaultUncaughtExceptionHandler();
    private static final ExecutorService THREAD_POOL = Executors.newSingleThreadExecutor();
    private Future<?> future;
    private CrashListener mListener;
    private File mLogFile;

    public static CrashHandler getInstance() {
        return sHandler;
    }

    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        CrashLogUtil.writeLog(mLogFile, "CrashHandler", ex.getMessage(), ex);
        future = THREAD_POOL.submit(new Runnable() {
            public void run() {
                Looper.prepare();
                if (mListener != null) {
                    mListener.afterSaveCrash(mLogFile,ex);
                }
                Looper.loop();
            };
        });
        if (!future.isDone()) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sDefaultHandler.uncaughtException(thread, ex);
    }

    public void init(File logFile, CrashListener listener) {
        mLogFile = logFile;
        mListener = listener;
    }

}
