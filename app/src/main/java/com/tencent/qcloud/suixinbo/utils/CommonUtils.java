package com.tencent.qcloud.suixinbo.utils;


import com.tencent.qcloud.suixinbo.QavsdkApplication;

public class CommonUtils {

	public static float getDimens(int resId){
		return QavsdkApplication.getContext().getResources().getDimension(resId);
	}
	
	public static String getString(int resId){
		return QavsdkApplication.getContext().getResources().getString(resId);
	}
}
