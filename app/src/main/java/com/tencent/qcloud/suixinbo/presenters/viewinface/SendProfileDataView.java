package com.tencent.qcloud.suixinbo.presenters.viewinface;

import com.tencent.qcloud.suixinbo.model.LoginedProfileData;

/**
 * author: Eric_luo .
 * date:   On 2016/7/27
 */
public interface SendProfileDataView extends MvpView {
    void sendProfileData(LoginedProfileData profileData);
}
