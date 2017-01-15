package com.tencent.qcloud.suixinbo.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.model.FansEntity;

import java.util.List;

/**
 * author: Eric_luo .
 * date:   On 2016/6/24
 */
public class DetailsFansItemAdapter extends BasicAdapter<FansEntity> {
    public DetailsFansItemAdapter(Context context, List<FansEntity> list) {
        super(context, list);
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.detail_fans_gridview_item, null);
        }
        return convertView;
    }
}
