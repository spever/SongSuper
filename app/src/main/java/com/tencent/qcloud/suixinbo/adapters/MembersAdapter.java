package com.tencent.qcloud.suixinbo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.model.MemberInfo;
import com.tencent.qcloud.suixinbo.presenters.viewinface.LiveView;
import com.tencent.qcloud.suixinbo.utils.SxbLog;
import com.tencent.qcloud.suixinbo.views.customviews.MembersDialog;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 成员列表适配器
 */
public class MembersAdapter extends ArrayAdapter<MemberInfo> {
    private static final String TAG = MembersAdapter.class.getSimpleName();
    private LiveView mLiveView;
    private MembersDialog membersDialog;
    private int mType;

    public MembersAdapter(Context context, int resource, ArrayList<MemberInfo> objects, LiveView liveView, MembersDialog dialog, int type) {
        super(context, resource, objects);
        mLiveView = liveView;
        membersDialog = dialog;
        mType = type;
    }

    public MembersAdapter(Context context, int resourse, ArrayList<MemberInfo> objects, LiveView liveView, int type) {
        super(context, resourse, objects);
        mLiveView = liveView;
        mType = type;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.members_item_layout, null);
            holder = new ViewHolder();
            holder.item_avatar = (CircleImageView) convertView.findViewById(R.id.item_avatar);
            holder.id = (TextView) convertView.findViewById(R.id.item_name);
            holder.videoCtrl = (TextView) convertView.findViewById(R.id.video_chat_ctl);
            if (mType ==1){
                holder.id.setVisibility(View.VISIBLE);
                holder.videoCtrl.setVisibility(View.VISIBLE);
            }
            if (mType ==2){
                holder.id.setVisibility(View.GONE);
                holder.videoCtrl.setVisibility(View.GONE);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final MemberInfo data = getItem(position);
        final String selectId = data.getUserId();

        Glide.with(getContext()).load(data.getAvatar()).placeholder(R.mipmap.girl2).into(holder.item_avatar);
        holder.id.setText(selectId);
        if (data.isOnVideoChat()) {
            holder.videoCtrl.setBackgroundResource(R.drawable.btn_video_disconnect);

        } else {
            holder.videoCtrl.setBackgroundResource(R.drawable.btn_video_connection);

        }
        holder.videoCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SxbLog.i(TAG, "select item:  " + selectId);

                if (!data.isOnVideoChat()) {//不在房间中，发起邀请
                    if (mLiveView.showInviteView(selectId)) {
//                        data.setIsOnVideoChat(true);
                        view.setBackgroundResource(R.drawable.btn_video_disconnect);

                    }
                } else {
                    mLiveView.cancelInviteView(selectId);
//                    data.setIsOnVideoChat(false);
                    view.setBackgroundResource(R.drawable.btn_video_connection);
                    mLiveView.cancelMemberView(selectId);
                }
                membersDialog.dismiss();

            }
        });


        return convertView;
    }

    public final class ViewHolder {
        public CircleImageView item_avatar;
        public TextView id;
        public TextView videoCtrl;
    }

}
