package com.tencent.qcloud.suixinbo.model;

import java.util.List;

/**
 * author: Eric_luo .
 * date:   On 2016/7/19
 */
public class ProfileCups {
    private List<AwardsCups> cups ;

    private String has_next;

    private String total;

    public void setCups(List<AwardsCups> cups){
        this.cups = cups;
    }
    public List<AwardsCups> getCups(){
        return this.cups;
    }
    public void setHas_next(String has_next){
        this.has_next = has_next;
    }
    public String getHas_next(){
        return this.has_next;
    }
    public void setTotal(String total){
        this.total = total;
    }
    public String getTotal(){
        return this.total;
    }
}
