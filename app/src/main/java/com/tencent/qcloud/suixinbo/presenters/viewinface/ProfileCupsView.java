package com.tencent.qcloud.suixinbo.presenters.viewinface;

import com.tencent.qcloud.suixinbo.model.AwardsCups;

import java.util.List;

/**
 * author: Eric_luo .
 * date:   On 2016/7/19
 */
public interface ProfileCupsView extends MvpView{
    void showProfileCups(List<AwardsCups> list_cups);
}
