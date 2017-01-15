package com.tencent.qcloud.suixinbo.model;

import com.alibaba.fastjson.JSONObject;

/**
 * author: Eric_luo .
 * date:   On 2016/7/12
 */
public abstract class CustomAttachment {
    protected int type;

    CustomAttachment(int type) {
        this.type = type;
    }

    public void fromJson(JSONObject data) {
        if (data != null) {
            parseData(data);
        }
    }


    public String toJson(boolean send) {
        return CustomAttachParser.packData(type, packData());
    }

    public int getType() {
        return type;
    }

    protected abstract void parseData(JSONObject data);
    protected abstract JSONObject packData();
}
