package com.tencent.qcloud.suixinbo.views.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

import com.tencent.qcloud.suixinbo.utils.LogUtil;

/**
 * author: Eric_luo .
 * date:   On 2016/6/24
 */
public class MyGridView extends GridView {
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();


        switch (ev.getAction()) {

            case MotionEvent.ACTION_MOVE:
                float x1 = ev.getX();
                float s = Math.abs(x - x1);
                LogUtil.e("grid", Math.abs(x - x1) + "");

                if (s > 10) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

}
