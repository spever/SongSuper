package com.tencent.qcloud.suixinbo.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.adapters.WorksAdapter;
import com.tencent.qcloud.suixinbo.model.WorksEntity;
import com.tencent.qcloud.suixinbo.views.customviews.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作品
 * author: Eric_luo .
 * date:   On 2016/6/23
 */
public class WorksFragment extends BaseFragment {
    private static WorksFragment instance = null;
    private View view;
    private ListView lv_work;

    private List<WorksEntity> list = new ArrayList<>();

    public static WorksFragment newInstance() {
        if (instance == null) {
            instance = new WorksFragment();
        }
        return instance;
    }

    public WorksFragment() {
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_work,null);
        initId();
        return view;    }

    private void initId() {
        lv_work = (ListView) view.findViewById(R.id.lv_work);
    }

    @Override
    protected void initData() {
        lv_work.setAdapter(new WorksAdapter(getActivity(),list));
        lv_work.setDivider(null);
//        setListViewHeightBasedOnChildren(lv_work);
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
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}
