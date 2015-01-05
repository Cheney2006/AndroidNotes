package com.keertech.androidnotes.application;

import android.app.Application;
import android.content.Context;

import com.yftools.exception.CustomCrashHandler;

/**
 * BaseApplication
 */
public abstract class BaseApplication extends Application {
    protected static Context context;
    protected static String packageName;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        packageName = getPackageName();
        //设置异常处理类
        CustomCrashHandler.getInstance().setCustomCrashHandler(this);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 获取一个应用上下文
     *
     * @return Context
     */
    public static Context getContext() {
        return context;
    }

    public static String getPackName() {
        return BaseApplication.packageName;
    }

}
