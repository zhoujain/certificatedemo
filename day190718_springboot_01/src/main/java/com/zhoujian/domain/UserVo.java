package com.zhoujian.domain;

public class UserVo {
    private Integer uid;
    private String username;
    private String usertype;
    private String userstate;
    private String actions;

    public UserVo(Integer uid, String username, String usertype, String userstate, String actions) {
        this.uid = uid;
        this.username = username;
        this.usertype = usertype;
        this.userstate = userstate;
        this.actions = actions;
    }

    public UserVo() {
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUserstate() {
        return userstate;
    }

    public void setUserstate(String userstate) {
        this.userstate = userstate;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }
}
