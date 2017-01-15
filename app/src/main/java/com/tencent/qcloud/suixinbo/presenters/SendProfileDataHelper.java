package com.tencent.qcloud.suixinbo.presenters;

import android.os.AsyncTask;

import com.tencent.TIMFriendshipManager;
import com.tencent.TIMUserProfile;
import com.tencent.TIMValueCallBack;
import com.tencent.qcloud.suixinbo.model.LoginedProfileData;
import com.tencent.qcloud.suixinbo.presenters.viewinface.SendProfileDataView;
import com.tencent.qcloud.suixinbo.utils.SxbLog;

import java.util.List;

/**
 * 同步数据到自己服务器获取更多数据
 *
 * author: Eric_luo .
 * date:   On 2016/7/27
 */
public class SendProfileDataHelper extends Presenter {

    private static final String TAG = "SendProfileDataHelper";
    private SendProfileDataView mView;
    private SendProfileDataTask mSendTask;

    public SendProfileDataHelper(SendProfileDataView view) {
        mView = view;
    }

    @Override
    public void onDestory() {

    }

    public void getDataFromServer(List<TIMUserProfile> profiles){
        mSendTask  = new SendProfileDataTask();
        mSendTask.execute(profiles.get(0).getIdentifier(),profiles.get(0).getSelfSignature(),profiles.get(0).getNickName(),profiles.get(0).getFaceUrl());
    }

    public void getServerProfileInfo(List<String> users){
        TIMFriendshipManager.getInstance().getUsersProfile(users, new TIMValueCallBack<List<TIMUserProfile>>() {
            @Override
            public void onError(int i, String s) {
                SxbLog.w(TAG, "getServerProfileInfo->error:" + i + "," + s);
            }

            @Override
            public void onSuccess(List<TIMUserProfile> profiles) {
                getDataFromServer(profiles);

            }
        });
    }

    class SendProfileDataTask extends AsyncTask<String,Void,LoginedProfileData>{

        @Override
        protected LoginedProfileData doInBackground(String... params) {
            return OKhttpHelper.getInstance().SendOurServerGetMoreInfo(params[0],params[1],params[2],params[3]);
        }

        @Override
        protected void onPostExecute(LoginedProfileData profileData) {
            mView.sendProfileData(profileData);
        }
    }
}
