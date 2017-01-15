package com.tencent.qcloud.suixinbo.utils;

import android.widget.Toast;

import com.tencent.qcloud.suixinbo.QavsdkApplication;

public class ToastUtil {
    private static Toast toast;
    public static boolean isDebug = true;

    /**
     * 可以连续弹吐司，不用等到上个吐司消失
     *
     * @param text
     */
    public static void showDebugToast(String text) {
        if (isDebug) {
            if (toast == null) {
                toast = Toast.makeText(QavsdkApplication.getContext(), text, Toast.LENGTH_SHORT);
            }
            toast.setText(text);
            toast.show();
        }

    }

    public static void showToast(String text) {

        if (toast == null) {
            toast = Toast.makeText(QavsdkApplication.getContext(), text, Toast.LENGTH_SHORT);
        }

        toast.setText(text);
        toast.show();
    }
}


