package com.zhoujian.domain;

public class User {
    private int uid;
    private String username;
    private String upwd;
    private int utid;
    private String uaccess;
    private int ustate;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public int getUtid() {
        return utid;
    }

    public void setUtid(int utid) {
        this.utid = utid;
    }

    public String getUaccess() {
        return uaccess;
    }

    public void setUaccess(String uaccess) {
        this.uaccess = uaccess;
    }

    public int getUstate() {
        return ustate;
    }

    public void setUstate(int ustate) {
        this.ustate = ustate;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", upwd='" + upwd + '\'' +
                ", utid=" + utid +
                ", uaccess='" + uaccess + '\'' +
                ", ustate=" + ustate +
                '}';
    }
}
