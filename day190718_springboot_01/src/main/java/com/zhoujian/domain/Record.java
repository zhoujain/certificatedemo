package com.zhoujian.domain;

import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable {
    private Integer scid;
    private String scname;
    private String scnumber;
    private String sccompany;
    private String sctoolname;
    private String scmodel;
    private String scoutnumber;
    private String scmanufacturer;
    private String scdelegate;
    private Date sccheckdate;
    private String sccheckdepartment;
    private Integer uid;
    private Integer puid;
    private Date scprintdate;
    private Double scmoney;
    private Integer tid;
    private Integer scstate;

    public Record() {
    }

    public Record(Integer scid, String scname, String scnumber, String sccompany, String sctoolname, String scmodel, String scoutnumber, String scmanufacturer, String scdelegate, Date sccheckdate, String sccheckdepartment, Integer uid, Integer puid, Date scprintdate, Double scmoney, Integer tid, Integer scstate) {
        this.scid = scid;
        this.scname = scname;
        this.scnumber = scnumber;
        this.sccompany = sccompany;
        this.sctoolname = sctoolname;
        this.scmodel = scmodel;
        this.scoutnumber = scoutnumber;
        this.scmanufacturer = scmanufacturer;
        this.scdelegate = scdelegate;
        this.sccheckdate = sccheckdate;
        this.sccheckdepartment = sccheckdepartment;
        this.uid = uid;
        this.puid = puid;
        this.scprintdate = scprintdate;
        this.scmoney = scmoney;
        this.tid = tid;
        this.scstate = scstate;
    }

    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
    }

    public String getScname() {
        return scname;
    }

    public void setScname(String scname) {
        this.scname = scname;
    }

    public String getScnumber() {
        return scnumber;
    }

    public void setScnumber(String scnumber) {
        this.scnumber = scnumber;
    }

    public String getSccompany() {
        return sccompany;
    }

    public void setSccompany(String sccompany) {
        this.sccompany = sccompany;
    }

    public String getSctoolname() {
        return sctoolname;
    }

    public void setSctoolname(String sctoolname) {
        this.sctoolname = sctoolname;
    }

    public String getScmodel() {
        return scmodel;
    }

    public void setScmodel(String scmodel) {
        this.scmodel = scmodel;
    }

    public String getScoutnumber() {
        return scoutnumber;
    }

    public void setScoutnumber(String scoutnumber) {
        this.scoutnumber = scoutnumber;
    }

    public String getScmanufacturer() {
        return scmanufacturer;
    }

    public void setScmanufacturer(String scmanufacturer) {
        this.scmanufacturer = scmanufacturer;
    }

    public String getScdelegate() {
        return scdelegate;
    }

    public void setScdelegate(String scdelegate) {
        this.scdelegate = scdelegate;
    }

    public Date getSccheckdate() {
        return sccheckdate;
    }

    public void setSccheckdate(Date sccheckdate) {
        this.sccheckdate = sccheckdate;
    }

    public String getSccheckdepartment() {
        return sccheckdepartment;
    }

    public void setSccheckdepartment(String sccheckdepartment) {
        this.sccheckdepartment = sccheckdepartment;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPuid() {
        return puid;
    }

    public void setPuid(Integer puid) {
        this.puid = puid;
    }

    public Date getScprintdate() {
        return scprintdate;
    }

    public void setScprintdate(Date scprintdate) {
        this.scprintdate = scprintdate;
    }

    public Double getScmoney() {
        return scmoney;
    }

    public void setScmoney(Double scmoney) {
        this.scmoney = scmoney;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getScstate() {
        return scstate;
    }

    public void setScstate(Integer scstate) {
        this.scstate = scstate;
    }

    @Override
    public String toString() {
        return "Record{" +
                "scid=" + scid +
                ", scname='" + scname + '\'' +
                ", scnumber='" + scnumber + '\'' +
                ", sccompany='" + sccompany + '\'' +
                ", sctoolname='" + sctoolname + '\'' +
                ", scmodel='" + scmodel + '\'' +
                ", scoutnumber='" + scoutnumber + '\'' +
                ", scmanufacturer='" + scmanufacturer + '\'' +
                ", scdelegate='" + scdelegate + '\'' +
                ", sccheckdate=" + sccheckdate +
                ", sccheckdepartment='" + sccheckdepartment + '\'' +
                ", uid=" + uid +
                ", puid=" + puid +
                ", scprintdate=" + scprintdate +
                ", scmoney=" + scmoney +
                ", tid=" + tid +
                ", scstate=" + scstate +
                '}';
    }
}
