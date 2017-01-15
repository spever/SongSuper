package com.tencent.qcloud.suixinbo.views;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.adapters.RankLiveShowAdapter;
import com.tencent.qcloud.suixinbo.model.CurLiveInfo;
import com.tencent.qcloud.suixinbo.model.LiveInfoJson;
import com.tencent.qcloud.suixinbo.model.MySelfInfo;
import com.tencent.qcloud.suixinbo.presenters.LiveListViewHelper;
import com.tencent.qcloud.suixinbo.presenters.viewinface.LiveListView;
import com.tencent.qcloud.suixinbo.utils.Constants;
import com.tencent.qcloud.suixinbo.utils.LogUtil;
import com.tencent.qcloud.suixinbo.utils.PicassoLoader;

import java.util.ArrayList;

import cn.lightsky.infiniteindicator.InfiniteIndicator;
import cn.lightsky.infiniteindicator.page.OnPageClickListener;
import cn.lightsky.infiniteindicator.page.Page;

/**
 * author: Eric_luo .
 * date:   On 2016/6/21
 */
public class RankFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener, OnPageClickListener, ViewPager.OnPageChangeListener, LiveListView {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<LiveInfoJson> liveList = new ArrayList<LiveInfoJson>();
    private ListView mLiveList;
    private static RankFragment instance = null;
    private View view;
    private View header;
    private LiveListViewHelper mLiveListViewHelper;
    private ArrayList<Page> pageViews;
    private InfiniteIndicator mAnimCircleIndicator;
    private RankLiveShowAdapter adapter;


    public static RankFragment newInstance() {
        if (instance == null) {
            instance = new RankFragment();
        }
        return instance;
    }

    public RankFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mLiveListViewHelper = new LiveListViewHelper(this);
        view = inflater.inflate(R.layout.fragment_rank, container, false);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    v.removeOnLayoutChangeListener(this);
                    Animator animator = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, 0, (float) Math.hypot(view.getWidth(), view.getHeight()));
                    animator.setInterpolator(new AccelerateInterpolator());
                    animator.setDuration(1500);
                    animator.start();
                }
            });

        }

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout_list);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mLiveList = (ListView) view.findViewById(R.id.live_list);
        header = View.inflate(getActivity(), R.layout.fragment_rank_header, null);
        mLiveList.addHeaderView(header);
        adapter = new RankLiveShowAdapter(getActivity(), R.layout.rank_live_item, liveList);
        mLiveList.setAdapter(adapter);

        mLiveList.setOnItemClickListener(this);

        initData();

        return view;
    }

    @Override
    public void onRefresh() {
        mLiveListViewHelper.getPageData();
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mHandler.sendEmptyMessage(1);
//            }
//        }, 3 * 1000);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//        startActivity(new Intent(getContext(),liveTestActivity.class));

        LiveInfoJson item = liveList.get(position - 1);
        //如果是自己
        if (item.getHost().getUid().equals(MySelfInfo.getInstance().getId())) {
            Toast.makeText(getActivity(), "this room don't exist", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(getActivity(), LiveActivity.class);
        intent.putExtra(Constants.ID_STATUS, Constants.MEMBER);
        MySelfInfo.getInstance().setIdStatus(Constants.MEMBER);
        CurLiveInfo.setHostID(item.getHost().getUid());
        CurLiveInfo.setHostName(item.getHost().getUsername());
        CurLiveInfo.setHostAvator(item.getHost().getAvatar());
        CurLiveInfo.setRoomNum(item.getAvRoomId());
        CurLiveInfo.setMembers(item.getWatchCount() + 1); // 添加自己
        CurLiveInfo.setAdmires(item.getAdmireCount());
        CurLiveInfo.setAddress(item.getLbs().getAddress());
        startActivity(intent);


    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    };


    protected void initData() {

        mAnimCircleIndicator = (InfiniteIndicator) view.findViewById(R.id.indicator_default_circle);


        pageViews = new ArrayList<>();

        pageViews.add(new Page("", R.drawable.banner1));
        pageViews.add(new Page("", R.drawable.banner2));

        mAnimCircleIndicator.setImageLoader(new PicassoLoader());
        mAnimCircleIndicator.addPages(pageViews);
        mAnimCircleIndicator.setPosition(InfiniteIndicator.IndicatorPosition.Center_Bottom);
        mAnimCircleIndicator.setOnPageChangeListener(this);
//        pageViews.add(new Page("A ", "https://raw.githubusercontent.com/lightSky/InfiniteIndicator/master/res/a.jpg", this));
//        pageViews.add(new Page("B ", "https://raw.githubusercontent.com/lightSky/InfiniteIndicator/master/res/b.jpg", this));
//        pageViews.add(new Page("C ", "https://raw.githubusercontent.com/lightSky/InfiniteIndicator/master/res/c.jpg", this));
//        pageViews.add(new Page("D ", "https://raw.githubusercontent.com/lightSky/InfiniteIndicator/master/res/d.jpg", this));

    }

    @Override
    public void onStart() {
        mLiveListViewHelper.getPageData();
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        mLiveListViewHelper.onDestory();
        super.onDestroy();
    }

    @Override
    public void showFirstPage(ArrayList<LiveInfoJson> result) {
        LogUtil.e("liveresult",result+"");
        mSwipeRefreshLayout.setRefreshing(false);
        liveList.clear();
        if (null != result) {
            for (LiveInfoJson item : result) {
                liveList.add(item);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPageClick(int position, Page page) {

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
}
