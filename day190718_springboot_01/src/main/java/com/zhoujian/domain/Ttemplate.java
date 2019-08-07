package com.zhoujian.domain;

import java.io.Serializable;

public class Ttemplate implements Serializable {
    private Integer ttid;
    private String ttname;
    private String tturl;
    private Integer ttpid;
    private Integer tttype;
    private Integer ttisdel;

    public Integer getTtid() {
        return ttid;
    }

    public void setTtid(Integer ttid) {
        this.ttid = ttid;
    }

    public String getTtname() {
        return ttname;
    }

    public void setTtname(String ttname) {
        this.ttname = ttname;
    }

    public String getTturl() {
        return tturl;
    }

    public void setTturl(String tturl) {
        this.tturl = tturl;
    }

    public Integer getTtpid() {
        return ttpid;
    }

    public void setTtpid(Integer ttpid) {
        this.ttpid = ttpid;
    }

    public Integer getTttype() {
        return tttype;
    }

    public void setTttype(Integer tttype) {
        this.tttype = tttype;
    }

    public Integer getTtisdel() {
        return ttisdel;
    }

    public void setTtisdel(Integer ttisdel) {
        this.ttisdel = ttisdel;
    }

    @Override
    public String toString() {
        return "Ttemplate{" +
                "ttid=" + ttid +
                ", ttname='" + ttname + '\'' +
                ", tturl='" + tturl + '\'' +
                ", ttpid=" + ttpid +
                ", tttype=" + tttype +
                ", ttisdel=" + ttisdel +
                '}';
    }
}
