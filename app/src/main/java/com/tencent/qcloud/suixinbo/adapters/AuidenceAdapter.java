package com.tencent.qcloud.suixinbo.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.model.AuidenceEntity;

import java.util.List;

/**
 * author: Eric_luo .
 * date:   On 2016/7/11
 */
public class AuidenceAdapter extends BasicAdapter<AuidenceEntity> {
    public AuidenceAdapter(Context context, List<AuidenceEntity> list) {
        super(context, list);
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = View.inflate(context, R.layout.item_auidence,null);
        }
        return  convertView;
    }
}
