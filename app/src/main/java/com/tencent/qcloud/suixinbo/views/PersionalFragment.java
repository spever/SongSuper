package com.tencent.qcloud.suixinbo.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.tencent.TIMUserProfile;
import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.adapters.AwardAdapter;
import com.tencent.qcloud.suixinbo.model.AwardsCups;
import com.tencent.qcloud.suixinbo.model.LoginedProfileData;
import com.tencent.qcloud.suixinbo.model.MySelfInfo;
import com.tencent.qcloud.suixinbo.presenters.GetCupsListHelper;
import com.tencent.qcloud.suixinbo.presenters.GetProfileDataHelper;
import com.tencent.qcloud.suixinbo.presenters.ProfileInfoHelper;
import com.tencent.qcloud.suixinbo.presenters.SendProfileDataHelper;
import com.tencent.qcloud.suixinbo.presenters.viewinface.ProfileCupsView;
import com.tencent.qcloud.suixinbo.presenters.viewinface.ProfileView;
import com.tencent.qcloud.suixinbo.presenters.viewinface.SendProfileDataView;
import com.tencent.qcloud.suixinbo.utils.LogUtil;
import com.tencent.qcloud.suixinbo.views.customviews.BaseFragment;
import com.tencent.qcloud.suixinbo.views.customviews.MyGridView;
import com.tencent.qcloud.suixinbo.views.customviews.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Eric_luo .
 * date:   On 2016/6/23
 */
public class PersionalFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener, ProfileCupsView,ProfileView, SendProfileDataView {
    private static final String TAG = "PersionalFragment";
    private View view;
    private PullToZoomScrollViewEx zoom_scroll;
    private TextView tv_achieve;
    private TextView tv_works;
    private TextView tv_dynamic;
    private TextView tv_details;
    private View v_achieve;
    private View v_works;
    private View v_dynamic;
    private View v_details;
    private FrameLayout fl_container;
    private AchievementFragment achievementFragment;
    private WorksFragment worksFragment;
    private DynamicFragment dynamicFragment;
    private DetailsFragment detailsFragment;
    private MyGridView honor_award;

    private List<AwardsCups> list = new ArrayList<>();
    private ImageView iv_user_set;
    private ImageView iv_user_msg;
    private PagerSlidingTabStrip tabs;
    private ViewPager viewpager;

    boolean isClick = false;
    private PopupWindow popWindow;
    private AwardAdapter adapter;
    private GetCupsListHelper mGetCupsHelper;
    private ImageView iVZoom;
    private TextView tv_user_name;
    private TextView tv_user_signature;
    private TextView tv_user_start;
    private ImageView iv_people;
    private ProfileInfoHelper mProfileInfoHelper;

    private List<String> list_user;
    private GetProfileDataHelper mGetProfileDataHelper;
    private static final int USERINFO_CODE = 10000;
    private SendProfileDataHelper mSendProfileDataHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_persional, null);
        loadViewForCode();
        zoom_scroll = (PullToZoomScrollViewEx) view.findViewById(R.id.zoom_scroll);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        zoom_scroll.setHeaderLayoutParams(localObject);

//        mGetCupsHelper = new GetCupsListHelper(this);
        mProfileInfoHelper = new ProfileInfoHelper(this);
//        mSendProfileDataHelper = new SendProfileDataHelper(this);
        initTab(view);
//        initHeaderData();
        list_user = new ArrayList<>();
        list_user.add(MySelfInfo.getInstance().getId());
        setDefaultFragment();
        return view;
    }

    private void initHeaderData() {
        Glide.with(getActivity()).load(MySelfInfo.getInstance().getAvatar()).placeholder(R.mipmap.ic_img_profile_bg).into(iVZoom);
        tv_user_name.setText(MySelfInfo.getInstance().getNickName());
        tv_user_signature.setText(TextUtils.isEmpty(MySelfInfo.getInstance().getSign())?"这家伙很懒，啥都木有":MySelfInfo.getInstance().getSign());
//        tv_user_start.setText(MySelfInfo.getInstance().get);
    }

    private void setDefaultFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        achievementFragment = new AchievementFragment();
        transaction.replace(R.id.fl_container, achievementFragment);
        transaction.commit();

        tv_achieve.setTextColor(getResources().getColor(R.color.red));
        tv_works.setTextColor(getResources().getColor(R.color.black));
        tv_dynamic.setTextColor(getResources().getColor(R.color.black));
        tv_details.setTextColor(getResources().getColor(R.color.black));
        v_achieve.setBackgroundColor(getResources().getColor(R.color.red));
        v_works.setBackgroundColor(getResources().getColor(R.color.transparent));
        v_dynamic.setBackgroundColor(getResources().getColor(R.color.transparent));
        v_details.setBackgroundColor(getResources().getColor(R.color.transparent));
    }

    private void initTab(View view) {
        fl_container = (FrameLayout) view.findViewById(R.id.fl_container);
        tv_achieve = (TextView) view.findViewById(R.id.tv_achieve);
        tv_works = (TextView) view.findViewById(R.id.tv_works);
        tv_dynamic = (TextView) view.findViewById(R.id.tv_dynamic);
        tv_details = (TextView) view.findViewById(R.id.tv_details);
        v_achieve = view.findViewById(R.id.v_achieve);
        v_works = view.findViewById(R.id.v_works);
        v_dynamic = view.findViewById(R.id.v_dynamic);
        v_details = view.findViewById(R.id.v_details);

        iv_user_set = (ImageView) view.findViewById(R.id.iv_user_set);
        iv_user_msg = (ImageView) view.findViewById(R.id.iv_user_msg);

        honor_award = (MyGridView) view.findViewById(R.id.honor_award);

        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        tv_achieve.setOnClickListener(this);
        tv_works.setOnClickListener(this);
        tv_dynamic.setOnClickListener(this);
        tv_details.setOnClickListener(this);
        v_achieve.setOnClickListener(this);
        v_works.setOnClickListener(this);
        v_dynamic.setOnClickListener(this);
        v_details.setOnClickListener(this);

        honor_award.setOnItemClickListener(this);

        iv_user_set.setOnClickListener(this);
        iv_user_msg.setOnClickListener(this);

    }

    private void loadViewForCode() {
        PullToZoomScrollViewEx scrollView = (PullToZoomScrollViewEx) view.findViewById(R.id.zoom_scroll);
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_head_view, null, false);
        View zoomView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_zoom_view, null, false);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.profile_content_view, null, false);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);

        tv_user_name = (TextView) headView.findViewById(R.id.tv_user_name);
        tv_user_signature = (TextView) headView.findViewById(R.id.tv_user_signature);
        tv_user_start = (TextView) headView.findViewById(R.id.tv_user_start);
        iv_people = (ImageView) headView.findViewById(R.id.iv_people);

        iVZoom = (ImageView) zoomView.findViewById(R.id.iv_zoom);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.tv_achieve:
            case R.id.v_achieve:
                tv_achieve.setTextColor(getResources().getColor(R.color.red));
                tv_works.setTextColor(getResources().getColor(R.color.black));
                tv_dynamic.setTextColor(getResources().getColor(R.color.black));
                tv_details.setTextColor(getResources().getColor(R.color.black));
                v_achieve.setBackgroundColor(getResources().getColor(R.color.red));
                v_works.setBackgroundColor(getResources().getColor(R.color.transparent));
                v_dynamic.setBackgroundColor(getResources().getColor(R.color.transparent));
                v_details.setBackgroundColor(getResources().getColor(R.color.transparent));
                if (achievementFragment == null) {
                    achievementFragment = new AchievementFragment();
                }
                transaction.replace(R.id.fl_container, achievementFragment);
                break;
            case R.id.tv_works:
            case R.id.v_works:
                tv_achieve.setTextColor(getResources().getColor(R.color.black));
                tv_works.setTextColor(getResources().getColor(R.color.red));
                tv_dynamic.setTextColor(getResources().getColor(R.color.black));
                tv_details.setTextColor(getResources().getColor(R.color.black));
                v_achieve.setBackgroundColor(getResources().getColor(R.color.transparent));
                v_works.setBackgroundColor(getResources().getColor(R.color.red));
                v_dynamic.setBackgroundColor(getResources().getColor(R.color.transparent));
                v_details.setBackgroundColor(getResources().getColor(R.color.transparent));
                if (worksFragment == null) {
                    worksFragment = new WorksFragment();
                }
                transaction.replace(R.id.fl_container, worksFragment);
                break;
            case R.id.tv_dynamic:
            case R.id.v_dynamic:
                tv_achieve.setTextColor(getResources().getColor(R.color.black));
                tv_works.setTextColor(getResources().getColor(R.color.black));
                tv_dynamic.setTextColor(getResources().getColor(R.color.red));
                tv_details.setTextColor(getResources().getColor(R.color.black));
                v_achieve.setBackgroundColor(getResources().getColor(R.color.transparent));
                v_works.setBackgroundColor(getResources().getColor(R.color.transparent));
                v_dynamic.setBackgroundColor(getResources().getColor(R.color.red));
                v_details.setBackgroundColor(getResources().getColor(R.color.transparent));
                if (dynamicFragment == null) {
                    dynamicFragment = new DynamicFragment();
                }
                transaction.replace(R.id.fl_container, dynamicFragment);
                break;
            case R.id.tv_details:
            case R.id.v_details:
                tv_achieve.setTextColor(getResources().getColor(R.color.black));
                tv_works.setTextColor(getResources().getColor(R.color.black));
                tv_dynamic.setTextColor(getResources().getColor(R.color.black));
                tv_details.setTextColor(getResources().getColor(R.color.red));
                v_achieve.setBackgroundColor(getResources().getColor(R.color.transparent));
                v_works.setBackgroundColor(getResources().getColor(R.color.transparent));
                v_dynamic.setBackgroundColor(getResources().getColor(R.color.transparent));
                v_details.setBackgroundColor(getResources().getColor(R.color.red));
                if (detailsFragment == null) {
                    detailsFragment = new DetailsFragment();
                }
                transaction.replace(R.id.fl_container, detailsFragment);
                break;

            case R.id.iv_user_set:
                startActivity(new Intent(getActivity(), SetActivity.class));
                break;
            case R.id.iv_user_msg:

                break;
            default:
                break;

        }
        transaction.commit();
    }


    @Override
    protected void initData() {
//        mGetCupsHelper.getProfileCupsData();
        adapter = new AwardAdapter(getContext(), list);
        honor_award.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.honor_award:
                if (!isClick) {
                    if (popWindow != null && popWindow.isShowing()) {
                        return;
                    } else {
                        isClick = false;
                        PopWindow(position, parent);
                    }

                }

                break;

        }
    }

    private void PopWindow(int pos, View parent) {
        LogUtil.e("pop", pos + "");

        final View popView = View.inflate(getActivity(), R.layout.pop_award, null);
        ImageView pop_cancel = (ImageView) popView.findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) popView.findViewById(R.id.imageView3);
        TextView tvContent = (TextView) popView.findViewById(R.id.tv_content);
        ImageView aWardImage = (ImageView) popView.findViewById(R.id.imageView1);
        imageView3.setImageDrawable(getResources().getDrawable(R.mipmap.award11));
        tvContent.setText("艾瑞克拉\n五月奖牌");
        popWindow = new PopupWindow(popView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popWindow.setAnimationStyle(R.style.popwin_anim_style);
        popWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);


        pop_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popWindow.isShowing()) {
                    popWindow.dismiss();

                }
            }
        });


    }

    @Override
    public void showProfileCups(List<AwardsCups> list_cups) {
        list.clear();
        if (null != list_cups) {
            for (AwardsCups item : list_cups) {
                list.add(item);
            }
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void updateProfileInfo(TIMUserProfile profile) {
        if (TextUtils.isEmpty(profile.getNickName())) {
            MySelfInfo.getInstance().setNickName(profile.getIdentifier());
        } else {
            MySelfInfo.getInstance().setNickName(profile.getNickName());
        }
        tv_user_name.setText(MySelfInfo.getInstance().getNickName());

        tv_user_signature.setText(TextUtils.isEmpty(profile.getSelfSignature())?"好像忘记写签名了，啥都木有(⊙o⊙)哦":profile.getSelfSignature());

        Glide.with(getActivity()).load(profile.getFaceUrl()).placeholder(R.mipmap.ic_img_profile_bg).into(iVZoom);

    }

    @Override
    public void updateUserInfo(int requestCode, List<TIMUserProfile> profiles) {

    }


    @Override
    public void onResume() {
        super.onResume();
        if (null!=tv_user_name){
            mProfileInfoHelper.getMyProfile();
        }
//        mSendProfileDataHelper.getServerProfileInfo(list_user);
    }

    @Override
    public void sendProfileData(LoginedProfileData profileData) {
        LogUtil.e("====",profileData.getAvatar());
    }
}
