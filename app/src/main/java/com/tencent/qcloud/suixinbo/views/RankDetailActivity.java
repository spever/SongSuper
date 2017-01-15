package com.tencent.qcloud.suixinbo.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.views.customviews.BaseActivity;
import com.tencent.qcloud.suixinbo.views.customviews.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: Eric_luo .
 * date:   On 2016/6/27
 */
public class RankDetailActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private int type;
    private ArrayList<String> listTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_detail);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type", 0);
        if (type == 11) {
            listTime = getIntent().getStringArrayListExtra("hour");
            tvTitle.setText("整点赛排行");
        } else if (type == 21) {
            listTime = getIntent().getStringArrayListExtra("hour");
            tvTitle.setText("整点赛排行");
        } else if (type == 31) {
            listTime = getIntent().getStringArrayListExtra("hour");
            tvTitle.setText("整点赛排行");
        } else if (type == 12) {
            listTime = getIntent().getStringArrayListExtra("day");
            tvTitle.setText("日赛排行");
        } else if (type == 22) {
            listTime = getIntent().getStringArrayListExtra("day");
            tvTitle.setText("日赛排行");
        } else if (type == 32) {
            listTime = getIntent().getStringArrayListExtra("day");
            tvTitle.setText("日赛排行");
        } else if (type == 13) {
            listTime = getIntent().getStringArrayListExtra("weekday");
            tvTitle.setText("星期赛排行");
        } else if (type == 23) {
            listTime = getIntent().getStringArrayListExtra("weekday");
            tvTitle.setText("星期赛排行");
        } else if (type == 33) {
            listTime = getIntent().getStringArrayListExtra("weekday");
            tvTitle.setText("星期赛排行");
        } else if (type == 14) {
            listTime = getIntent().getStringArrayListExtra("month");
            tvTitle.setText("月赛排行");
        } else if (type == 24) {
            listTime = getIntent().getStringArrayListExtra("month");
            tvTitle.setText("月赛排行");
        } else if (type == 34) {
            listTime = getIntent().getStringArrayListExtra("month");
            tvTitle.setText("月赛排行");
        }else if (type == 15) {
            listTime = getIntent().getStringArrayListExtra("hundred");
            tvTitle.setText("百强赛排行");
        } else if (type == 25) {
            listTime = getIntent().getStringArrayListExtra("hundred");
            tvTitle.setText("百强赛排行");
        } else if (type == 35) {
            listTime = getIntent().getStringArrayListExtra("hundred");
            tvTitle.setText("百强赛排行");
        }

        RankAdapter adapter = new RankAdapter(getSupportFragmentManager(), listTime);
        viewpager.setAdapter(adapter);
        tabs.setViewPager(viewpager);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    class RankAdapter extends FragmentPagerAdapter {

        private List<String> list_data;

        public RankAdapter(FragmentManager fm, List<String> list_data) {
            super(fm);
            this.list_data = list_data;
        }

        @Override
        public Fragment getItem(int position) {
            return RankDetailsFragment.newInstance(position);
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
