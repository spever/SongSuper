package com.tencent.qcloud.suixinbo.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.model.DynamicEntity;

import java.util.List;

/**
 * author: Eric_luo .
 * date:   On 2016/6/24
 */
public class DynamicAdapter extends BasicAdapter<DynamicEntity> {
    public DynamicAdapter(Context context, List<DynamicEntity> list) {
        super(context, list);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.listview_item_dynamic, null);
        }
        return convertView;
    }
}
