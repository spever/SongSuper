package com.tencent.qcloud.suixinbo.model;

import java.io.Serializable;

/**
 * author: Eric_luo .
 * date:   On 2016/6/22
 */
public class RankEntity implements Serializable{
    private String name;
    private int urlImage;
    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(int urlImage) {
        this.urlImage = urlImage;
    }
}
