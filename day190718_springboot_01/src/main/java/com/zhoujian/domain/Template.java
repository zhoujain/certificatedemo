package com.zhoujian.domain;

import java.io.Serializable;

public class Template implements Serializable {
    private Integer tid;
    private String tname;
    private String turl;
    private int tpid;
    private int ttype;

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

    public int getTpid() {
        return tpid;
    }

    public void setTpid(int tpid) {
        this.tpid = tpid;
    }

    public int getTtype() {
        return ttype;
    }

    public void setTtype(int ttype) {
        this.ttype = ttype;
    }

    @Override
    public String toString() {
        return "Template{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", turl='" + turl + '\'' +
                ", tpid=" + tpid +
                ", ttype=" + ttype +
                '}';
    }
}
