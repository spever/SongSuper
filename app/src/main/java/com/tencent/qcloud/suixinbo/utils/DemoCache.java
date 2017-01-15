package com.tencent.qcloud.suixinbo.utils;

import android.content.Context;

import com.tencent.qcloud.suixinbo.model.MySelfInfo;
import com.tencent.qcloud.suixinbo.utils.image.ImageLoaderKit;

/**
 *
 */
public class DemoCache {

    private static Context context;

    private static String account;

    private static MySelfInfo userInfo;

    // 图片加载、缓存与管理组件
    private static ImageLoaderKit imageLoaderKit;

    public static void clear() {
        account = null;
        userInfo = null;
    }

    public static String getAccount() {
        return account;
    }

    public static void setAccount(String account) {
        DemoCache.account = account;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        DemoCache.context = context.getApplicationContext();
    }

    public static MySelfInfo getUserInfo() {
        if (userInfo == null) {
            userInfo = new MySelfInfo();
        }

        return userInfo;
    }

    public static ImageLoaderKit getImageLoaderKit() {
        return imageLoaderKit;
    }

    public static void initImageLoaderKit() {
        imageLoaderKit = new ImageLoaderKit(context, null);
    }
}
