package com.tencent.qcloud.suixinbo.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.VideoView;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.model.WorksEntity;
import com.tencent.qcloud.suixinbo.utils.LogUtil;

import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * author: Eric_luo .
 * date:   On 2016/6/27
 */
public class WorksAdapter extends BasicAdapter<WorksEntity> {

    private String url = "http://live1.0763f.com/qygg/sd/live.m3u8";


    public WorksAdapter(Context context, List<WorksEntity> list) {
        super(context, list);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.listview_item_works, null);
        }

        if (position == 0) {
            ViewHolder.getHolder(convertView).view_1.setVisibility(View.INVISIBLE);
        }
        if (position % 3 == 0) {
            ViewHolder.getHolder(convertView).image.setImageDrawable(context.getResources().getDrawable(R.mipmap.orange_dot));
        }
        if (position % 3 == 1) {
            ViewHolder.getHolder(convertView).image.setImageDrawable(context.getResources().getDrawable(R.mipmap.green_dot));
        }
        if (position % 3 == 2) {
            ViewHolder.getHolder(convertView).image.setImageDrawable(context.getResources().getDrawable(R.mipmap.yellow_dot));
        }

        ViewHolder.getHolder(convertView).video_view.setMediaController(null);
        ViewHolder.getHolder(convertView).video_view.setVideoPath(url);
        final View finalConvertView = convertView;
        ViewHolder.getHolder(convertView).iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHolder.getHolder(finalConvertView).iv_backicon.setVisibility(View.GONE);
                ViewHolder.getHolder(finalConvertView).video_view.start();

            }
        });
        final View finalConvertView1 = convertView;
        ViewHolder.getHolder(convertView).iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShowPopWindow(finalConvertView1);
                showShare();
            }
        });

        ViewHolder.getHolder(convertView).iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ViewHolder.getHolder(convertView).iv_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    private void ShowPopWindow(View convertView) {
        View view = View.inflate(context, R.layout.pop_share, null);
        PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);
        popupWindow.showAtLocation(convertView, Gravity.BOTTOM,0,0);
    }

    static class ViewHolder {

        public View view_1;
        public View view_2;
        public ImageView image;
        public ImageView iv_play;
        public VideoView video_view;
        private ImageView iv_share;
        private ImageView iv_delete;
        private ImageView iv_lock;
        private ImageView iv_backicon;


        public ViewHolder(View convertView) {
            view_1 = convertView.findViewById(R.id.view_1);
            view_2 = convertView.findViewById(R.id.view_2);
            image = (ImageView) convertView.findViewById(R.id.image);
            iv_play = (ImageView) convertView.findViewById(R.id.iv_play);
            video_view = (VideoView) convertView.findViewById(R.id.video_view);
            iv_share = (ImageView) convertView.findViewById(R.id.iv_share);
            iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);
            iv_lock = (ImageView) convertView.findViewById(R.id.iv_lock);
            iv_backicon = (ImageView) convertView.findViewById(R.id.iv_backicon);
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

    private void showShare() {
        LogUtil.e("context",context+"");
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(context.getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(context);
    }
}
