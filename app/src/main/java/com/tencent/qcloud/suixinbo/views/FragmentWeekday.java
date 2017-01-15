package com.tencent.qcloud.suixinbo.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.adapters.StartRankAdapter;
import com.tencent.qcloud.suixinbo.model.RankEntity;
import com.tencent.qcloud.suixinbo.views.customviews.BaseFragment;
import com.tencent.qcloud.suixinbo.views.customviews.HorizontalListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * author: Eric_luo .
 * date:   On 2016/6/22
 */
public class FragmentWeekday extends BaseFragment implements View.OnClickListener {
    private HorizontalListView horizon_listview_star;
    private HorizontalListView horizon_listview_popular;
    private HorizontalListView horizon_listview_ktv;
    private View view;

    private List<RankEntity> list = new ArrayList<>();
    private TextView tv_type;
    private LinearLayout ll_time_week;
    private ArrayList<String> weekday;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_competition, null);

        initId();

        return view;
    }

    private void initId() {
        tv_type = (TextView) view.findViewById(R.id.tv_type);
        horizon_listview_star = (HorizontalListView) view.findViewById(R.id.horizon_listview_star);
        horizon_listview_popular = (HorizontalListView) view.findViewById(R.id.horizon_listview_popular);
        horizon_listview_ktv = (HorizontalListView) view.findViewById(R.id.horizon_listview_ktv);
        ImageView im_more_star = (ImageView) view.findViewById(R.id.im_more_star);
        ImageView im_more_popular = (ImageView) view.findViewById(R.id.im_more_popular);
        ImageView im_more_ktv = (ImageView) view.findViewById(R.id.im_more_ktv);
        ll_time_week = (LinearLayout) view.findViewById(R.id.ll_time_week);
        ll_time_week.setVisibility(View.VISIBLE);
        ll_time_week.setOnClickListener(this);
        im_more_star.setOnClickListener(this);
        im_more_popular.setOnClickListener(this);
        im_more_ktv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initRankItemsData();
        tv_type.setText("星期赛");
        horizon_listview_star.setAdapter(new StartRankAdapter(getActivity(), list));
        horizon_listview_popular.setAdapter(new StartRankAdapter(getActivity(), list));
        horizon_listview_ktv.setAdapter(new StartRankAdapter(getActivity(), list));

        weekday = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            weekday.add("第" + i + "周");
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
                intent.putExtra("type", 13);
                intent.putStringArrayListExtra("weekday", weekday);
                intent.setClass(getActivity(), RankDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.im_more_popular:
                intent.putExtra("type", 23);
                intent.putStringArrayListExtra("weekday", weekday);
                intent.setClass(getActivity(), RankDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.im_more_ktv:
                intent.putExtra("type", 33);
                intent.putStringArrayListExtra("weekday", weekday);
                intent.setClass(getActivity(), RankDetailActivity.class);
                startActivity(intent);
                break;

            case R.id.ll_time_week:
                selectMonthAndDay();
                break;
        }
    }

    private void selectMonthAndDay() {

        getActivity().setTheme(android.R.style.Theme_Holo_Light);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = View.inflate(getActivity(), R.layout.alert_week, null);

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        NumberPicker monthPicker = (NumberPicker) view.findViewById(R.id.monthpicker);
        NumberPicker weekPicker = (NumberPicker) view.findViewById(R.id.weekpicker);
        monthPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                String tmpStr = String.valueOf(value);
                if (value < 10) {
                    tmpStr = "0" + tmpStr;
                }
                return tmpStr;
            }
        });
        monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });
        monthPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {

            }
        });
        monthPicker.setMaxValue(12);
        monthPicker.setMinValue(1);
        monthPicker.setValue(month+1);

        weekPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                String tmpStr = String.valueOf(value);
                return tmpStr;
            }
        });
        weekPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });
        weekPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {

            }
        });
        weekPicker.setMaxValue(5);
        weekPicker.setMinValue(1);
        weekPicker.setValue(week);


        builder.setView(view);
//        builder.setTitle("选取时间:");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
