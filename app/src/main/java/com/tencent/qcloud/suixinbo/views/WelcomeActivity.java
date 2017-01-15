package com.tencent.qcloud.suixinbo.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.utils.SharePreferUtils;

/**
 * author: Eric_luo .
 * date:   On 2016/6/30
 */
public class WelcomeActivity extends FragmentActivity {

    private ImageView iv_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome);
        iv_welcome = (ImageView) findViewById(R.id.iv_welcome);
        AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(1000);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                boolean isFirstLogin = SharePreferUtils.getBoolean("isFirstLogin", false, getApplicationContext());
                if (!isFirstLogin) {
                    startActivity(new Intent(new Intent(WelcomeActivity.this, SplashActivity.class)));
                } else {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                }

                overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }


        });

        iv_welcome.startAnimation(animation);
    }

}
