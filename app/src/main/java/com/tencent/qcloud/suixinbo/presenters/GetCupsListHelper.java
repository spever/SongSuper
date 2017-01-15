package com.tencent.qcloud.suixinbo.presenters;

import android.os.AsyncTask;

import com.tencent.qcloud.suixinbo.model.AwardsCups;
import com.tencent.qcloud.suixinbo.model.MySelfInfo;
import com.tencent.qcloud.suixinbo.presenters.viewinface.ProfileCupsView;

import java.util.List;

/**
 * author: Eric_luo .
 * date:   On 2016/7/19
 */
public class GetCupsListHelper extends Presenter {
   private ProfileCupsView mProfileCupsView;
    private GetCupsListTask mGetLiveListTask;

    public GetCupsListHelper(ProfileCupsView view) {
       mProfileCupsView  = view;
    }


    public void getProfileCupsData(){
        mGetLiveListTask = new GetCupsListTask();
        mGetLiveListTask.execute(MySelfInfo.getInstance().getId(),1,10);
    }
    @Override
    public void onDestory() {

    }

    class GetCupsListTask extends AsyncTask<Object,Integer,List<AwardsCups>>{

        @Override
        protected List<AwardsCups> doInBackground(Object... params) {
            return OKhttpHelper.getInstance().getProfileCenter((String)params[0],(Integer) params[1],(Integer) params[2]);
        }

        @Override
        protected void onPostExecute(List<AwardsCups> list_cups) {
            mProfileCupsView.showProfileCups(list_cups);
        }
    }
}
