package com.tencent.qcloud.suixinbo.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.model.LiveInfoJson;
import com.tencent.qcloud.suixinbo.utils.GlideCircleTransform;
import com.tencent.qcloud.suixinbo.utils.LogUtil;
import com.tencent.qcloud.suixinbo.utils.SxbLog;
import com.tencent.qcloud.suixinbo.utils.UIUtils;

import java.util.ArrayList;


/**
 * 直播列表的Adapter
 */
public class RankLiveShowAdapter extends ArrayAdapter<LiveInfoJson> {
    private static String TAG = "LiveShowAdapter";
    private int resourceId;
    private Activity mActivity;

    public RankLiveShowAdapter(Activity activity, int resource, ArrayList<LiveInfoJson> objects) {
        super(activity, resource, objects);
        resourceId = resource;
        mActivity = activity;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder)convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rank_live_item, null);

            holder = new ViewHolder();
            holder.ivCover = (ImageView) convertView.findViewById(R.id.cover);
            holder.tvHost = (TextView) convertView.findViewById(R.id.host_name);
            holder.tvLocation = (TextView) convertView.findViewById(R.id.live_location);
            holder.tvMembers = (TextView) convertView.findViewById(R.id.tv_members);
            holder.tvDes = (TextView) convertView.findViewById(R.id.textView3);
            holder.ivAvatar = (ImageView) convertView.findViewById(R.id.avatar);

            convertView.setTag(holder);
        }

        LiveInfoJson data = getItem(position);
        LogUtil.e("data",data.toString());
        if (!TextUtils.isEmpty(data.getHost().getAvatar())){
            SxbLog.d(TAG, "load cover: " + data.getCover());
            RequestManager req = Glide.with(mActivity);
            req.load(data.getHost().getAvatar()).into(holder.ivCover);
        }else{
            holder.ivCover.setImageResource(R.mipmap.girl2);
        }

        if (null == data.getHost() || TextUtils.isEmpty(data.getHost().getAvatar())){
            // 显示默认图片
            Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.default_avatar);
            Bitmap cirBitMap = UIUtils.createCircleImage(bitmap, 0);
            holder.ivAvatar.setImageBitmap(cirBitMap);
        }else{
            SxbLog.d(TAG, "user avator: " + data.getHost().getAvatar());
            RequestManager req = Glide.with(mActivity);
            req.load(data.getHost().getAvatar()).transform(new GlideCircleTransform(mActivity)).into(holder.ivAvatar);
        }

        if (!TextUtils.isEmpty(data.getHost().getUsername())){
            holder.tvHost.setText(" " + UIUtils.getLimitString(data.getHost().getUsername(), 10));
        }else{
            holder.tvHost.setText("@" + UIUtils.getLimitString(data.getHost().getUid(), 10));
        }
        holder.tvMembers.setText(""+data.getWatchCount());
        if (!TextUtils.isEmpty(data.getLbs().getAddress())) {
            holder.tvLocation.setText(UIUtils.getLimitString(data.getLbs().getAddress(), 9));
        }else{
            holder.tvLocation.setText(getContext().getString(R.string.live_unknown));
        }

        return convertView;
    }

    private class ViewHolder{
        TextView tvHost;
        TextView tvMembers;
        TextView tvDes;
        TextView tvLocation;
        ImageView ivCover;
        ImageView ivAvatar;
    }

}
