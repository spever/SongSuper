package com.tencent.qcloud.suixinbo.utils;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.lightsky.infiniteindicator.loader.ImageLoader;

/**
 * Created by lightsky on 16/1/31.
 */
public class GlideLoader implements ImageLoader {

    @Override
    public void initLoader(Context context) {

    }

    @Override
    public void load(Context context, ImageView targetView, Object res) {

        if (res instanceof String){
            Glide.with(context)
                    .load((String) res)
                    .centerCrop()
//                .placeholder(R.drawable.a)
                    .crossFade()
                    .into(targetView);
        }
    }
}
