package com.tencent.qcloud.suixinbo.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.model.RankDetailsEntity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * author: Eric_luo .
 * date:   On 2016/6/27
 */
public class RankDetailsAdapter extends BasicAdapter<RankDetailsEntity> {
    public RankDetailsAdapter(Context context, List<RankDetailsEntity> list) {
        super(context, list);
    }

    private List<Integer> mSeparators = new ArrayList<>();

    public RankDetailsAdapter(Context context, List<RankDetailsEntity> list, List<Integer> mSeparators) {
        super(context, list);
        this.mSeparators = mSeparators;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int type = getItemViewType(position);

        if (convertView == null) {

            switch (type) {
                case 0:
                    convertView = View.inflate(context, R.layout.listview_item_rankdetails, null);
                    break;
                case 1:
//                    convertView = View.inflate(context, R.layout.listview_item123_rankdetails, null);
                    if (position == 0) {
                        convertView = View.inflate(context, R.layout.listview_item1_rankdetails, null);
                        ViewHolder.getHolder(convertView).iv_num.setBackgroundResource(R.mipmap.no1gold);
                    } else if (position == 1) {
                        convertView = View.inflate(context, R.layout.listview_item2_rankdetails, null);
                        ViewHolder.getHolder(convertView).iv_num.setBackgroundResource(R.mipmap.no2vg);
                    } else if (position == 2) {
                        convertView = View.inflate(context, R.layout.listview_item3_rankdetails, null);
                        ViewHolder.getHolder(convertView).iv_num.setBackgroundResource(R.mipmap.no3cu);
                    }
                    break;

            }

        }

        if (type ==0){
            ViewHolder.getHolder(convertView).tv_num.setText(position + 1 + "");
        }

        RankDetailsEntity rank = list.get(position);

        ViewHolder.getHolder(convertView).tv_name.setText(rank.getName());
        Glide.with(context).load(rank.getUrlImage()).into(ViewHolder.getHolder(convertView).iv_circle_icon);
        ViewHolder.getHolder(convertView).tv_start_value.setText(rank.getCount());

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return mSeparators.contains(position) ? 1 : 0;
    }

    static class ViewHolder {

        public CircleImageView iv_circle_icon;
        public TextView tv_name;
        public TextView tv_start_value;
        public Button iv_support_rank;
        public ImageView iv_num;
        public ImageView imageView_back;

        public TextView tv_num;

        public ViewHolder(View convertView) {
            iv_circle_icon = (CircleImageView) convertView.findViewById(R.id.view2);
            tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            tv_start_value = (TextView) convertView.findViewById(R.id.tv_start_value);
            iv_support_rank = (Button) convertView.findViewById(R.id.iv_support_rank);
            iv_num = (ImageView) convertView.findViewById(R.id.iv_num);
            imageView_back = (ImageView) convertView.findViewById(R.id.imageView4);

            tv_num = (TextView) convertView.findViewById(R.id.tv_num);


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
