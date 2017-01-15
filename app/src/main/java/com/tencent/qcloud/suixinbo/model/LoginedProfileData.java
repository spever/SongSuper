package com.tencent.qcloud.suixinbo.model;

/**
 * author: Eric_luo .
 * date:   On 2016/7/27
 */
public class LoginedProfileData extends ProfileData{

    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
