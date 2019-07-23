package com.zhoujian.domain;

import java.io.Serializable;

public class Menu implements Serializable {
    private Integer mid;
    private String mname;
    private String micon;
    private String murl;
    private Integer mpid;

    public Menu(Integer mid, String mname, String micon, String murl, Integer mpid) {
        this.mid = mid;
        this.mname = mname;
        this.micon = micon;
        this.murl = murl;
        this.mpid = mpid;
    }

    public Menu() {
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMicon() {
        return micon;
    }

    public void setMicon(String micon) {
        this.micon = micon;
    }

    public String getMurl() {
        return murl;
    }

    public void setMurl(String murl) {
        this.murl = murl;
    }

    public Integer getMpid() {
        return mpid;
    }

    public void setMpid(Integer mpid) {
        this.mpid = mpid;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "mid=" + mid +
                ", mname='" + mname + '\'' +
                ", micon='" + micon + '\'' +
                ", murl='" + murl + '\'' +
                ", mpid=" + mpid +
                '}';
    }
}
