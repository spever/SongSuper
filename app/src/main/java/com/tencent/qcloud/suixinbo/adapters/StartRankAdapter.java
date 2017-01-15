package com.tencent.qcloud.suixinbo.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.model.RankEntity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * author: Eric_luo .
 * date:   On 2016/6/22
 */
public class StartRankAdapter extends BasicAdapter<RankEntity> {

    public StartRankAdapter(Context context, List<RankEntity> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.horizontial_item_listview, null);
        }

        RankEntity  entity = list.get(position);
//        Glide
//                .with(context)
//                .load("")
//                .centerCrop()
//                .placeholder(R.drawable.default_avatar)
//                .crossFade()
//                .into(ViewHolder.getHolder(convertView).iv_circle_icon);
        Glide.with(context).load(entity.getUrlImage()).into(ViewHolder.getHolder(convertView).iv_circle_icon);
        ViewHolder.getHolder(convertView).tv_name.setText(entity.getName());
        ViewHolder.getHolder(convertView).tv_people.setText(entity.getCount());
        return convertView;
    }



    static class ViewHolder {

        public CircleImageView iv_circle_icon;
        public TextView tv_name;
        public TextView tv_people;
        public Button btn_support;

        public ViewHolder(View convertView) {
            iv_circle_icon = (CircleImageView) convertView.findViewById(R.id.iv_circle_icon);
            tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            tv_people = (TextView) convertView.findViewById(R.id.tv_people);
            btn_support = (Button) convertView.findViewById(R.id.btn_support);
        }

        public static ViewHolder getHolder(View convertView) {

            ViewHolder holder = (ViewHolder) convertView.getTag();
            if (holder == null) {
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }

            return holder;
        }
    }
}
