package com.tencent.qcloud.suixinbo.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.adapters.StartRankAdapter;
import com.tencent.qcloud.suixinbo.model.RankEntity;
import com.tencent.qcloud.suixinbo.views.customviews.BaseFragment;
import com.tencent.qcloud.suixinbo.views.customviews.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 百强赛
 * author: Eric_luo .
 * date:   On 2016/6/22
 */
public class FragmentHundred extends BaseFragment implements View.OnClickListener {
    private HorizontalListView horizon_listview_star;
    private HorizontalListView horizon_listview_popular;
    private HorizontalListView horizon_listview_ktv;
    private View view;

    private List<RankEntity> list = new ArrayList<>();
    private TextView tv_type;
    private LinearLayout ll_time_hour, ll_time_week, ll_time_month, ll_time_hundred;
    private ArrayList<String> hundred;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_competition, null);

        initId();

        return view;    }

    private void initId() {
        tv_type = (TextView) view.findViewById(R.id.tv_type);
        view.findViewById(R.id.tv_month);
        horizon_listview_star = (HorizontalListView) view.findViewById(R.id.horizon_listview_star);
        horizon_listview_popular = (HorizontalListView) view.findViewById(R.id.horizon_listview_popular);
        horizon_listview_ktv = (HorizontalListView) view.findViewById(R.id.horizon_listview_ktv);
        ImageView im_more_star = (ImageView) view.findViewById(R.id.im_more_star);
        ImageView im_more_popular = (ImageView) view.findViewById(R.id.im_more_popular);
        ImageView im_more_ktv = (ImageView) view.findViewById(R.id.im_more_ktv);
        ll_time_hundred = (LinearLayout) view.findViewById(R.id.ll_time_hundred);
        ll_time_hundred.setVisibility(View.VISIBLE);
        im_more_star.setOnClickListener(this);
        im_more_popular.setOnClickListener(this);
        im_more_ktv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initRankItemsData();
        tv_type.setText("百强赛");
        horizon_listview_star.setAdapter(new StartRankAdapter(getActivity(), list));
        horizon_listview_popular.setAdapter(new StartRankAdapter(getActivity(), list));
        horizon_listview_ktv.setAdapter(new StartRankAdapter(getActivity(), list));
        hundred = new ArrayList<>();
        for (int i = 1;i<101;i++){
            hundred.add(i+"");
        }
    }

    private void initRankItemsData() {
        list = new ArrayList<>();
        int[] urls = {R.mipmap.girl1, R.mipmap.girl2, R.mipmap.girl3, R.mipmap.girl4};
        RankEntity rank1 = new RankEntity();
        rank1.setName("小无双");
        rank1.setUrlImage(urls[0]);
        rank1.setCount("234525");
        RankEntity rank2 = new RankEntity();
        rank2.setName("小水仙");
        rank2.setUrlImage(urls[1]);
        rank2.setCount("212122");
        RankEntity rank3 = new RankEntity();
        rank3.setName("小木花");
        rank3.setUrlImage(urls[2]);
        rank3.setCount("45452");
        RankEntity rank4 = new RankEntity();
        rank4.setName("小妹儿");
        rank4.setUrlImage(urls[3]);
        rank4.setCount("43223");
        RankEntity rank5 = new RankEntity();
        rank5.setName("小话");
        rank5.setUrlImage(urls[0]);
        rank5.setCount("37452");
        RankEntity rank6 = new RankEntity();
        rank6.setName("小水");
        rank6.setUrlImage(urls[1]);
        rank6.setCount("23452");
        RankEntity rank7 = new RankEntity();
        rank7.setName("甜昙花");
        rank7.setUrlImage(urls[2]);
        rank7.setCount("23423");
        RankEntity rank8= new RankEntity();
        rank8.setName("老妹儿");
        rank8.setUrlImage(urls[3]);
        rank8.setCount("2342");
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
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.im_more_star:
                intent.putExtra("type",15);
                intent.putStringArrayListExtra("hundred",hundred);
                intent.setClass(getActivity(), RankDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.im_more_popular:
                intent.putExtra("type",25);
                intent.putStringArrayListExtra("hundred",hundred);
                intent.setClass(getActivity(), RankDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.im_more_ktv:
                intent.putExtra("type",35);
                intent.putStringArrayListExtra("hundred",hundred);
                intent.setClass(getActivity(), RankDetailActivity.class);
                startActivity(intent);
                break;
        }
    }
}
