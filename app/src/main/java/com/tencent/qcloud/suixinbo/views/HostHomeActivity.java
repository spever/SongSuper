package com.tencent.qcloud.suixinbo.views;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.views.customviews.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 主播个人中心详情页面
 * author: Eric_luo .
 * date:   On 2016/7/6
 */
public class HostHomeActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_circle_icon)
    CircleImageView ivCircleIcon;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.textView6)
    TextView tvAttention;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.textView7)
    TextView tvFans;
    @BindView(R.id.tv_user_signature)
    TextView tvUserSignature;
    @BindView(R.id.tv_verify)
    TextView tvVerify;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_home);
        ButterKnife.bind(this);
        initTabPager();
    }

    private void initTabPager() {
        list = new ArrayList<>();
        list.add("主页");
        list.add("直播");
        HostAdapter adapter = new HostAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    class HostAdapter extends FragmentPagerAdapter {

        private List<String> list_data;
        private Fragment fragment;

        public HostAdapter(FragmentManager fm, List<String> list_data) {
            super(fm);
            this.list_data = list_data;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    fragment = new HostHomeFragment();
                    break;
                case 1:
                    fragment = new HostLiveFragment();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return list_data.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list_data.get(position);
        }
    }
}
