package com.tencent.qcloud.suixinbo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Eric_luo .
 * date:   On 2016/6/21
 */
public class HomeTabAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragment = new ArrayList<>();                         //fragment列表
    private List<String> list_Title = new ArrayList<>();                              //tab名的列表


    public HomeTabAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * @param fragment      添加Fragment
     * @param fragmentTitle Fragment的标题，即TabLayout中对应Tab的标题
     */
    public void addFragment(Fragment fragment, String fragmentTitle) {
        list_fragment.add(fragment);
        list_Title.add(fragmentTitle);
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {

        return list_Title.get(position);
    }
}
