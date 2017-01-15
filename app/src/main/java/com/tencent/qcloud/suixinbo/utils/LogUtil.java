package com.tencent.qcloud.suixinbo.utils;

import android.util.Log;

/**
 * @author luo
 *
 */
public class LogUtil {

    public static boolean isDebug = true;// 开发完毕将isDebug置为false

    /**
     * 打印i级别的Log
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    /**
     * 打印e级别的Log
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    /**
     * 方便打Log
     * @param object
     * @param msg
     */
    public static void i(Object object, String msg) {
        if (isDebug) {
            Log.i(object.getClass().getSimpleName(), msg);
        }
    }

    /**
     * 方便打Log
     * @param object
     * @param msg
     */
    public static void e(Object object, String msg) {
        if (isDebug) {
            Log.e(object.getClass().getSimpleName(), msg);
        }
    }
}
