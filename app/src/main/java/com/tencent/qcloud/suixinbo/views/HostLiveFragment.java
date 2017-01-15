package com.tencent.qcloud.suixinbo.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tencent.qcloud.suixinbo.views.customviews.BaseFragment;

/**
 * author: Eric_luo .
 * date:   On 2016/7/6
 */
public class HostLiveFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView view = new TextView(getContext());
        view.setText(getClass().getSimpleName());
        return view;
    }
}
