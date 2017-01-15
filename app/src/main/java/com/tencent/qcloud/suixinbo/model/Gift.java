package com.tencent.qcloud.suixinbo.model;

import com.tencent.qcloud.suixinbo.giftconstant.GiftType;

/**
 * author: Eric_luo .
 * date:   On 2016/7/11
 */
public class Gift {
    private GiftType giftType;
    private String title;
    private int count;
    private int imageId;

    public Gift(GiftType giftType, String title, int count, int imageId) {
        super();
        this.giftType = giftType;
        this.title = title;
        this.count = count;
        this.imageId = imageId;
    }

    public void setGiftType(GiftType giftType) {
        this.giftType = giftType;
    }

    public GiftType getGiftType() {
        return giftType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
