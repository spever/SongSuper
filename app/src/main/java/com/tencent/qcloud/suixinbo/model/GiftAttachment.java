package com.tencent.qcloud.suixinbo.model;

import com.tencent.qcloud.suixinbo.giftconstant.GiftType;

/**
 * 礼物附件
 *
 */
public class GiftAttachment extends CustomAttachment{
    private final String KEY_PRESENT = "present";
    private final String KEY_COUNT = "count";

    private GiftType giftType;
    private int count;

    GiftAttachment() {
        super(0);
    }

    public GiftAttachment(GiftType giftType, int count) {
        super(0);
        this.giftType = giftType;
        this.count = count;
    }


    @Override
    protected void parseData(com.alibaba.fastjson.JSONObject data) {
            this.giftType = GiftType.typeOfValue(data.getIntValue(KEY_PRESENT));
            this.count = data.getIntValue(KEY_COUNT);
    }

    protected com.alibaba.fastjson.JSONObject packData() {
        com.alibaba.fastjson.JSONObject data = new com.alibaba.fastjson.JSONObject();

            data.put(KEY_PRESENT, giftType.getValue());
            data.put(KEY_COUNT, count);

        return data;
    }

    public GiftType getGiftType() {
        return giftType;
    }

    public void setGiftType(GiftType giftType) {
        this.giftType = giftType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
