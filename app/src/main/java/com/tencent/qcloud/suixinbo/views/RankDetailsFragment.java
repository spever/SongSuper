package com.tencent.qcloud.suixinbo.views;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.adapters.RankDetailsAdapter;
import com.tencent.qcloud.suixinbo.model.RankDetailsEntity;
import com.tencent.qcloud.suixinbo.utils.LogUtil;
import com.tencent.qcloud.suixinbo.views.customviews.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Eric_luo .
 * date:   On 2016/6/27
 */
public class RankDetailsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private int position;
    private View view;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mLiveList;
    private List<RankDetailsEntity> list;
    private List<Integer> mSeparators;


    public static RankDetailsFragment newInstance(int position) {
        RankDetailsFragment f = new RankDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position");

        LogUtil.e("position", position + "");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_rank_details, null);

        initId();
        return view;
    }


    private void initId() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout_list);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mLiveList = (ListView) view.findViewById(R.id.live_list);
    }


    @Override
    protected void initData() {
        initRankItemsData();
        mLiveList.setAdapter(new RankDetailsAdapter(getContext(), list, mSeparators));
    }

    private void initRankItemsData() {
        list = new ArrayList<>();
        int[] urls = {R.mipmap.girl1, R.mipmap.girl2, R.mipmap.girl3, R.mipmap.girl4};
        RankDetailsEntity rank1 = new RankDetailsEntity();
        rank1.setName("小无双");
        rank1.setUrlImage(urls[0]);
        rank1.setCount("234525");
        RankDetailsEntity rank2 = new RankDetailsEntity();
        rank2.setName("小水仙");
        rank2.setUrlImage(urls[1]);
        rank2.setCount("212122");
        RankDetailsEntity rank3 = new RankDetailsEntity();
        rank3.setName("小木花");
        rank3.setUrlImage(urls[2]);
        rank3.setCount("45452");
        RankDetailsEntity rank4 = new RankDetailsEntity();
        rank4.setName("小妹儿");
        rank4.setUrlImage(urls[3]);
        rank4.setCount("43223");
        RankDetailsEntity rank5 = new RankDetailsEntity();
        rank5.setName("小话");
        rank5.setUrlImage(urls[0]);
        rank5.setCount("37452");
        RankDetailsEntity rank6 = new RankDetailsEntity();
        rank6.setName("小水");
        rank6.setUrlImage(urls[1]);
        rank6.setCount("23452");
        RankDetailsEntity rank7 = new RankDetailsEntity();
        rank7.setName("甜昙花");
        rank7.setUrlImage(urls[2]);
        rank7.setCount("23423");
        RankDetailsEntity rank8= new RankDetailsEntity();
        rank8.setName("老妹儿");
        rank8.setUrlImage(urls[3]);
        rank8.setCount("2342");
        mSeparators = new ArrayList<>();
        mSeparators.add(0, 0);
        mSeparators.add(1, 1);
        mSeparators.add(2, 2);
        list.add(rank1);
        list.add(rank2);
        list.add(rank3);
        list.add(rank4);
        list.add(rank5);
        list.add(rank6);
        list.add(rank7);
        list.add(rank8);
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(1);
            }
        }, 3 * 1000);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    };

}
