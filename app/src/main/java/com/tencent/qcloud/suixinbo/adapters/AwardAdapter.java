package com.tencent.qcloud.suixinbo.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.model.AwardsCups;

import java.util.List;

/**
 * author: Eric_luo .
 * date:   On 2016/6/24
 */
public class AwardAdapter extends BasicAdapter<AwardsCups> {
    private int type;

    public AwardAdapter(Context context, List<AwardsCups> list) {
        super(context, list);
    }

    public AwardAdapter(Context context, List<AwardsCups> list, int type) {
        super(context, list);
        this.type = type;
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.award_item, null);
            viewHolder = new ViewHolder();
            viewHolder.v_big_award = (ImageView) convertView.findViewById(R.id.v_big_award);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        AwardsCups item = list.get(position);
//        String type = item.getName();
//       if (type.equals("冠军奖杯")){
//           Glide.with(context).load(R.mipmap.award11).into(viewHolder.v_big_award);
//       }
        return convertView;
    }

    public class ViewHolder{
        public ImageView v_big_award;
    }
}
