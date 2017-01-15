package com.tencent.qcloud.suixinbo.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.adapters.DynamicAdapter;
import com.tencent.qcloud.suixinbo.model.DynamicEntity;
import com.tencent.qcloud.suixinbo.views.customviews.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Eric_luo .
 * date:   On 2016/6/23
 */
public class DynamicFragment extends BaseFragment {
    private static DynamicFragment instance = null;
    private View view;
    private ListView lv_dynamic;
    private List<DynamicEntity> list = new ArrayList<>();

    public static DynamicFragment newInstance() {
        if (instance == null) {
            instance = new DynamicFragment();
        }
        return instance;
    }

    public DynamicFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_dynamic, null);
        initId();
        return view;
    }

    private void initId() {
        lv_dynamic = (ListView) view.findViewById(R.id.lv_dynamic);
    }

    @Override
    protected void initData() {

        lv_dynamic.setAdapter(new DynamicAdapter(getActivity(), list));
        lv_dynamic.setDivider(null);
        setListViewHeightBasedOnChildren(lv_dynamic);
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}
