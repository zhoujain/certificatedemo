package com.zhoujian.domain;

import java.io.Serializable;

public class Template implements Serializable {
    private Integer tid;
    private String tname;
    private String turl;
    private Integer tpid;
    private Integer ttype;
    private Integer tisdel;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTurl() {
        return turl;
    }

    public void setTurl(String turl) {
        this.turl = turl;
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public Integer getTtype() {
        return ttype;
    }

    public void setTtype(Integer ttype) {
        this.ttype = ttype;
    }

    public Integer getTisdel() {
        return tisdel;
    }

    public void setTisdel(Integer tisdel) {
        this.tisdel = tisdel;
    }

    @Override
    public String toString() {
        return "Template{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", turl='" + turl + '\'' +
                ", tpid=" + tpid +
                ", ttype=" + ttype +
                ", tisdel=" + tisdel +
                '}';
    }
}
