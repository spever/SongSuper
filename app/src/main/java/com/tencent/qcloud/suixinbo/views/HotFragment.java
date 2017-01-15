package com.tencent.qcloud.suixinbo.views;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.utils.PicassoLoader;
import com.tencent.qcloud.suixinbo.views.customviews.BaseFragment;

import java.util.ArrayList;

import cn.lightsky.infiniteindicator.InfiniteIndicator;
import cn.lightsky.infiniteindicator.page.OnPageClickListener;
import cn.lightsky.infiniteindicator.page.Page;

/**
 * 热点Fragment
 * <p>
 * author: Eric_luo .
 * date:   On 2016/6/21
 */
public class HotFragment extends BaseFragment implements ViewPager.OnPageChangeListener, OnPageClickListener {
    private static HotFragment instance = null;
    private RadioGroup rg_group;
    private FragmentHour mFragmentHour;
    private FragmentDay mFragmentDay;
    private FragmentWeekday mFragmentWeekday;
    private FragmentMonth mFragmentMonth;
    private FragmentHundred mFragmentHundred;
    private View view;


    private ArrayList<Page> pageViews;
    private InfiniteIndicator mAnimCircleIndicator;
    private GestureDetector detector;


    public static HotFragment newInstance() {
        if (instance == null) {
            instance = new HotFragment();
        }
        return instance;
    }

    public HotFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_hot, null);
        setDefaultFragment();
        initRadioGroup();

        return view;
    }


    private void setDefaultFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mFragmentHour = new FragmentHour();
        transaction.replace(R.id.content_container, mFragmentHour);
        transaction.commit();
    }




    private void initRadioGroup() {
        rg_group = (RadioGroup) view.findViewById(R.id.rg_group);
        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch (checkedId) {
                    case R.id.id_auto_car:
                        if (mFragmentHour == null) {
                            mFragmentHour = new FragmentHour();
                        }
                        transaction.replace(R.id.content_container, mFragmentHour);
                        break;

                    case R.id.id_hc_car:
                        if (mFragmentDay == null) {
                            mFragmentDay = new FragmentDay();
                        }
                        transaction.replace(R.id.content_container, mFragmentDay);
                        break;

                    case R.id.id_ck_car:
                        if (mFragmentWeekday == null) {
                            mFragmentWeekday = new FragmentWeekday();
                        }
                        transaction.replace(R.id.content_container, mFragmentWeekday);
                        break;
                    case R.id.id_crowd:
                        if (mFragmentMonth == null) {
                            mFragmentMonth = new FragmentMonth();
                        }
                        transaction.replace(R.id.content_container, mFragmentMonth);
                        break;
                    case R.id.id_speed:
                        if (mFragmentHundred == null) {
                            mFragmentHundred = new FragmentHundred();
                        }
                        transaction.replace(R.id.content_container, mFragmentHundred);
                        break;

                    default:
                        break;
                }

                transaction.addToBackStack(null);
                transaction.commit();
            }

        });

    }

    @Override
    protected void initData() {

        mAnimCircleIndicator = (InfiniteIndicator) view.findViewById(R.id.indicator_default_circle);
        pageViews = new ArrayList<>();
        pageViews.add(new Page("", R.drawable.banner1));
        pageViews.add(new Page("", R.drawable.banner2));

        mAnimCircleIndicator.setImageLoader(new PicassoLoader());
        mAnimCircleIndicator.addPages(pageViews);
        mAnimCircleIndicator.setPosition(InfiniteIndicator.IndicatorPosition.Center_Bottom);
        mAnimCircleIndicator.setOnPageChangeListener(this);


//        pageViews.add(new Page("B ", "https://raw.githubusercontent.com/lightSky/InfiniteIndicator/master/res/b.jpg", this));
//        pageViews.add(new Page("C ", "https://raw.githubusercontent.com/lightSky/InfiniteIndicator/master/res/c.jpg", this));
//        pageViews.add(new Page("D ", "https://raw.githubusercontent.com/lightSky/InfiniteIndicator/master/res/d.jpg", this));

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageClick(int position, Page page) {

    }


    @Override
    public void onPause() {
        super.onPause();
        mAnimCircleIndicator.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        mAnimCircleIndicator.start();
    }


}
