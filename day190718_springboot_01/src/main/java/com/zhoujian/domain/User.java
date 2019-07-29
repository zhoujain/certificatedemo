package com.zhoujian.domain;

import java.io.Serializable;

public class User implements Serializable {
    private int uid;
    private String username;
    private String upwd;
    private int utid;
    private String uaccess;
    private int ustate;

    public User(int uid, String username, String upwd, int utid, String uaccess, int ustate) {
        this.uid = uid;
        this.username = username;
        this.upwd = upwd;
        this.utid = utid;
        this.uaccess = uaccess;
        this.ustate = ustate;
    }

    public User() {
    }

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
