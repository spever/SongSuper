package com.tencent.qcloud.suixinbo.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.adapters.DetailsFansItemAdapter;
import com.tencent.qcloud.suixinbo.model.FansEntity;
import com.tencent.qcloud.suixinbo.model.ProfileData;
import com.tencent.qcloud.suixinbo.presenters.GetProfileDataHelper;
import com.tencent.qcloud.suixinbo.presenters.viewinface.ProfileDataView;
import com.tencent.qcloud.suixinbo.views.customviews.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * author: Eric_luo .
 * date:   On 2016/6/23
 */
public class DetailsFragment extends BaseFragment implements ProfileDataView {

    private static DetailsFragment instance = null;
    @BindView(R.id.iv_circle_icon)
    CircleImageView ivCircleIcon;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_born_from)
    TextView tvBornFrom;
    @BindView(R.id.tv_user_signature)
    TextView tvUserSignature;
    @BindView(R.id.tv_feeling)
    TextView tvFeeling;
    @BindView(R.id.tv_ktv)
    TextView tvKtv;
    @BindView(R.id.gd_fans)
    GridView gdFans;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.rl_alipay)
    RelativeLayout rlAlipay;
    @BindView(R.id.rl_vip)
    RelativeLayout rlVip;
    @BindView(R.id.tv_bindwechat)
    TextView tvBindwechat;
    @BindView(R.id.tv_bindqq)
    TextView tvBindqq;
    @BindView(R.id.tv_bindweibo)
    TextView tvBindweibo;
    @BindView(R.id.rl_forget_pass)
    RelativeLayout rlForgetPass;
    @BindView(R.id.rl_people_list)
    RelativeLayout rlPeopleList;
    private List<FansEntity> list = new ArrayList<>();
    private View view;
    private GridView gv_fans;

    private DetailsFansItemAdapter adapter;
    private GetProfileDataHelper mGetProfileDataHelper;



    public static DetailsFragment newInstance() {
        if (instance == null) {
            instance = new DetailsFragment();
        }
        return instance;
    }

    public DetailsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_details, null);
        initId();

        mGetProfileDataHelper = new GetProfileDataHelper(this);
        ButterKnife.bind(this, view);
        return view;
    }


    private void initId() {
        gv_fans = (GridView) view.findViewById(R.id.gd_fans);
    }

    @Override
    protected void initData() {
        mGetProfileDataHelper.getProfileData();
        adapter = new DetailsFansItemAdapter(getContext(), list);
        gv_fans.setAdapter(adapter);

    }


    @Override
    public void showProfileData(ProfileData data) {
        if (null != data) {
            Glide.with(getActivity()).load(data.getAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.girl2).into(ivCircleIcon);
            tvNickname.setText(data.getNick_name());
            tvId.setText(data.getUser_id());
        }
    }

}
