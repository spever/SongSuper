package com.tencent.qcloud.suixinbo.presenters;

import android.os.AsyncTask;

import com.tencent.qcloud.suixinbo.model.MySelfInfo;
import com.tencent.qcloud.suixinbo.model.ProfileData;
import com.tencent.qcloud.suixinbo.presenters.viewinface.ProfileDataView;

/**
 * author: Eric_luo .
 * date:   On 2016/7/19
 */
public class GetProfileDataHelper extends Presenter {
    public GetProfileDataHelper(ProfileDataView mProfileDataView) {
        this.mProfileDataView = mProfileDataView;
    }

    private ProfileDataView mProfileDataView;
    private GetProfileDataTask mGetProfileData;

    public void getProfileData() {
        mGetProfileData = new GetProfileDataTask();
        mGetProfileData.execute(MySelfInfo.getInstance().getId());
    }


    class GetProfileDataTask extends AsyncTask<String, Void, ProfileData> {

        @Override
        protected ProfileData doInBackground(String... params) {
            return OKhttpHelper.getInstance().GetProfileData(params[0]);
        }

        @Override
        protected void onPostExecute(ProfileData data) {
            mProfileDataView.showProfileData(data);
        }
    }



    @Override
    public void onDestory() {

    }
}
