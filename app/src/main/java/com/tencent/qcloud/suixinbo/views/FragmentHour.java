package com.tencent.qcloud.suixinbo.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.adapters.StartRankAdapter;
import com.tencent.qcloud.suixinbo.model.RankEntity;
import com.tencent.qcloud.suixinbo.views.customviews.BaseFragment;
import com.tencent.qcloud.suixinbo.views.customviews.HorizontalListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 整点赛
 * author: Eric_luo .
 * date:   On 2016/6/22
 */
public class FragmentHour extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.tv_name3)
    TextView tvName3;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_name4)
    TextView tvName4;
    @BindView(R.id.ll_time_hour)
    LinearLayout llTimeHour;
    @BindView(R.id.tv_months_week)
    TextView tvMonthsWeek;
    @BindView(R.id.tv_name1s_week)
    TextView tvName1sWeek;
    @BindView(R.id.tv_weeks_week)
    TextView tvWeeksWeek;
    @BindView(R.id.tv_name3s_week)
    TextView tvName3sWeek;
    @BindView(R.id.ll_time_day)
    LinearLayout llTimeDay;
    @BindView(R.id.tv_months)
    TextView tvMonths;
    @BindView(R.id.tv_name1s)
    TextView tvName1s;
    @BindView(R.id.tv_weeks)
    TextView tvWeeks;
    @BindView(R.id.tv_name3s)
    TextView tvName3s;
    @BindView(R.id.ll_time_week)
    LinearLayout llTimeWeek;
    @BindView(R.id.tv_months_month)
    TextView tvMonthsMonth;
    @BindView(R.id.tv_name1s_month)
    TextView tvName1sMonth;
    @BindView(R.id.ll_time_month)
    LinearLayout llTimeMonth;
    @BindView(R.id.tv_months_hundred)
    TextView tvMonthsHundred;
    @BindView(R.id.ll_time_hundred)
    LinearLayout llTimeHundred;
    @BindView(R.id.tv_tilte)
    TextView tvTilte;
    @BindView(R.id.im_more_star)
    ImageView imMoreStar;
    @BindView(R.id.horizon_listview_star)
    HorizontalListView horizonListviewStar;
    @BindView(R.id.tv_tilte1)
    TextView tvTilte1;
    @BindView(R.id.im_more_popular)
    ImageView imMorePopular;
    @BindView(R.id.horizon_listview_popular)
    HorizontalListView horizonListviewPopular;
    @BindView(R.id.tv_tilte2)
    TextView tvTilte2;
    @BindView(R.id.im_more_ktv)
    ImageView imMoreKtv;
    @BindView(R.id.horizon_listview_ktv)
    HorizontalListView horizonListviewKtv;
    private HorizontalListView horizon_listview_star;
    private HorizontalListView horizon_listview_popular;
    private HorizontalListView horizon_listview_ktv;
    private View view;

    private List<RankEntity> list = new ArrayList<>();
    private TextView tv_type;
    private LinearLayout ll_time_hour;
    private DatePicker datepicker_start;
    private Calendar calendar;
    private Intent intent;
    private ArrayList<String> hour;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_competition, null);
        initId();
        ButterKnife.bind(this, view);
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
        ll_time_hour = (LinearLayout) view.findViewById(R.id.ll_time_hour);
        ll_time_hour.setVisibility(View.VISIBLE);
        ll_time_hour.setOnClickListener(this);
        im_more_star.setOnClickListener(this);
        im_more_popular.setOnClickListener(this);
        im_more_ktv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initRankItemsData();
        tv_type.setText("整点赛");
        horizon_listview_star.setAdapter(new StartRankAdapter(getActivity(), list));
        horizon_listview_popular.setAdapter(new StartRankAdapter(getActivity(), list));
        horizon_listview_ktv.setAdapter(new StartRankAdapter(getActivity(), list));

        hour = new ArrayList<>();

        hour.add("19点");
        hour.add("20点");
        hour.add("21点");
        hour.add("22点");
        hour.add("23点");
        hour.add("24点");
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
        intent = new Intent();
        switch (v.getId()) {

            case R.id.im_more_star:
                intent.putExtra("type", 11);
                intent.putStringArrayListExtra("hour", hour);
                intent.setClass(getActivity(), RankDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.im_more_popular:
                intent.putExtra("type", 21);
                intent.putStringArrayListExtra("hour", hour);
                intent.setClass(getActivity(), RankDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.im_more_ktv:
                intent.putExtra("type", 31);
                intent.putStringArrayListExtra("hour", hour);
                intent.setClass(getActivity(), RankDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_time_hour:
//                selectHourTime();
                break;
            default:
                break;
        }

    }

    private void selectHourTime() {
        getActivity().setTheme(android.R.style.Theme_Holo_Light);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = View.inflate(getActivity(), R.layout.alert_hour, null);
        TimePicker date_time= (TimePicker) view.findViewById(R.id.date_time);
        DatePicker date_day= (DatePicker) view.findViewById(R.id.date_day);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        ((LinearLayout) ((ViewGroup)date_day.getChildAt(0)).getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
        ((LinearLayout)((ViewGroup)date_time.getChildAt(0)).getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
        ((LinearLayout)((ViewGroup)date_time.getChildAt(0)).getChildAt(1)).getChildAt(1).setVisibility(View.GONE);
        ((LinearLayout)((ViewGroup)date_time.getChildAt(0)).getChildAt(1)).getChildAt(2).setVisibility(View.GONE);
        date_time.setIs24HourView(true);
        date_day.init(0, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            }
        });
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
