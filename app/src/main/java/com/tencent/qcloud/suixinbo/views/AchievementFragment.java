package com.tencent.qcloud.suixinbo.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.views.customviews.BaseFragment;

/**
 * author: Eric_luo .
 * date:   On 2016/6/23
 */
public class AchievementFragment extends BaseFragment {
    private static AchievementFragment instance = null;
    private View view;

    public static AchievementFragment newInstance() {
        if (instance == null) {
            instance = new AchievementFragment();
        }
        return instance;
    }

    public AchievementFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_achievement,null);
        return view;
    }
}
