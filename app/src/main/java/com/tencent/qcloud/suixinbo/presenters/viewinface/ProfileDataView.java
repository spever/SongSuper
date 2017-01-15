package com.tencent.qcloud.suixinbo.presenters.viewinface;

import com.tencent.qcloud.suixinbo.model.ProfileData;

/**
 * author: Eric_luo .
 * date:   On 2016/7/19
 */
public interface ProfileDataView extends MvpView{
    void showProfileData(ProfileData object);
}
