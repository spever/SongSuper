package com.tencent.qcloud.suixinbo.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.utils.SharePreferUtils;
import com.tencent.qcloud.suixinbo.views.customviews.linkedviewpager.MyPagerAdapter;
import com.tencent.qcloud.suixinbo.views.customviews.linkedviewpager.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Eric_luo .
 * date:   On 2016/6/29
 */
public class SplashActivity extends Activity {
    private ViewPager mPager;
    private List<View> mPageViews;
    private MyPagerAdapter mPageAdapter;

    private ViewPager mFramePager;
    private List<View> mFramePageViews;
    private MyPagerAdapter mFramePageAdapter;

    private static final boolean isFirstLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        initViewPager();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void initViewPager() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mFramePager = (ViewPager) findViewById(R.id.main_scrolllayout);

        mPageViews = new ArrayList<View>();
        mFramePageViews = new ArrayList<View>();

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View frameView1 = inflater.inflate(R.layout.transparent_layer_image, null);
        initFramePagerView(frameView1, R.mipmap.view1);
        View frameView2 = inflater.inflate(R.layout.transparent_layer_image, null);
        initFramePagerView(frameView2, R.mipmap.view2);
        View frameView3 = inflater.inflate(R.layout.transparent_layer_image, null);
        initFramePagerView(frameView3, R.mipmap.view3);


        mFramePageViews.add(frameView1);
        mFramePageViews.add(frameView2);
        mFramePageViews.add(frameView3);


        mFramePageAdapter = new MyPagerAdapter(mFramePageViews);
        mFramePager.setAdapter(mFramePageAdapter);
//      	mFramePager.setOnPageChangeListener(mFramePagerListener);
        View view1 = inflater.inflate(R.layout.transparent_layer, null);

        initPagerView(view1, "");
        View view2 = inflater.inflate(R.layout.transparent_layer2, null);

        initPagerView(view2, "");

        View view3 = inflater.inflate(R.layout.transparent_layer3, null);
        TextView textView3 = (TextView) view3.findViewById(R.id.text);
        textView3.setVisibility(View.GONE);
        Button btn = (Button) view3.findViewById(R.id.button);
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                    SharePreferUtils.putBoolean("isFirstLogin", true, getApplicationContext());
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        });

        mPageViews.add(view1);
        mPageViews.add(view2);
        mPageViews.add(view3);
        mPageAdapter = new MyPagerAdapter(mPageViews);
        mPager.setAdapter(mPageAdapter);
        mPager.setFollowViewPager(mFramePager);
        // mPager.setOnPageChangeListener();

    }

    public void initFramePagerView(View frameView, int drawable) {
        ImageView image = (ImageView) frameView.findViewById(R.id.image);
        image.setImageResource(drawable);

    }

    public void initPagerView(View view, String text) {
        TextView textView1 = (TextView) view.findViewById(R.id.text);
        textView1.setText(text);
    }

}
