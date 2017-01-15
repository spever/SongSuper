package com.tencent.qcloud.suixinbo.views.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.tencent.qcloud.suixinbo.utils.LogUtil;

/**
 * author: Eric_luo .
 * date:   On 2016/6/30
 */
public class MyScrollView extends ScrollView {

    private float xDistance, yDistance, xLast, yLast;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {


//        return super.onInterceptTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;
                LogUtil.e("sss---==",xDistance+"");
                LogUtil.e("sss+++==",yDistance+"");
                if(xDistance > yDistance){
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return true;
                }
        }

        return super.onInterceptTouchEvent(ev);
    }
}
