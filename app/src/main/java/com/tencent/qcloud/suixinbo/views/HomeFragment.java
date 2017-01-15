package com.tencent.qcloud.suixinbo.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.adapters.HomeTabAdapter;

/**
 * author: Eric_luo .
 * date:   On 2016/6/21
 */
public class HomeFragment extends Fragment {

    private TabLayout tab_Home_title;
    private ViewPager vp_Home_pager;
    private HomeTabAdapter viewPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_home, container, false);

        initControls(view);

        return view;
    }



    /**
     * 初始化各控件
     *
     * @param view
     */
    private void initControls(View view) {

        vp_Home_pager = (ViewPager) view.findViewById(R.id.vp_FindFragment_pager);
        viewPagerAdapter = new HomeTabAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(HotFragment.newInstance(), "排行");//添加Fragment
        viewPagerAdapter.addFragment(RankFragment.newInstance(), "热门");
        vp_Home_pager.setAdapter(viewPagerAdapter);//设置适配器

        tab_Home_title = (TabLayout) view.findViewById(R.id.tab_FindFragment_title);
        tab_Home_title.addTab(tab_Home_title.newTab().setText("排行"));//给TabLayout添加Tab
        tab_Home_title.addTab(tab_Home_title.newTab().setText("热门"));
        tab_Home_title.setupWithViewPager(vp_Home_pager);
    }


}
