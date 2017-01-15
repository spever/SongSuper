package com.tencent.qcloud.suixinbo.model;

/**
 * author: Eric_luo .
 * date:   On 2016/7/19
 */
public class AwardsCups {
    private String id;

    private String user_id;

    private String cup_id;

    private String create_at;

    private String status_state;

    private String name;

    private String type;

    private String nick_name;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setCup_id(String cup_id) {
        this.cup_id = cup_id;
    }

    public String getCup_id() {
        return this.cup_id;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getCreate_at() {
        return this.create_at;
    }

    public void setStatus_state(String status_state) {
        this.status_state = status_state;
    }

    public String getStatus_state() {
        return this.status_state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getNick_name() {
        return this.nick_name;
    }

}
