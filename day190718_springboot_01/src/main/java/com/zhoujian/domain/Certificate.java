package com.zhoujian.domain;

import java.io.Serializable;
import java.util.Date;

public class Certificate implements Serializable {
    private Integer cid;
    private String cname;
    private String cnumber;
    private String ccompany;
    private String ctoolname;
    private String cmodel;
    private String coutnumber;
    private String cmanufacturer;
    private String cdelegate;
    private Date ccheckdate;
    private String ccheckdepartment;
    private Integer uid;
    private Integer puid;
    private Date cprintdate;
    private Double cmoney;
    private Integer tid;
    private Integer cstate;

    public Integer getCstate() {
        return cstate;
    }

    public void setCstate(Integer cstate) {
        this.cstate = cstate;
    }

    public Certificate(Integer cid, String cname, String cnumber, String ccompany, String ctoolname, String cmodel, String coutnumber, String cmanufacturer, String cdelegate, Date ccheckdate, String ccheckdepartment, Integer uid, Integer puid, Date cprintdate, Double cmoney, Integer tid, Integer cstate) {
        this.cid = cid;
        this.cname = cname;
        this.cnumber = cnumber;
        this.ccompany = ccompany;
        this.ctoolname = ctoolname;
        this.cmodel = cmodel;
        this.coutnumber = coutnumber;
        this.cmanufacturer = cmanufacturer;
        this.cdelegate = cdelegate;
        this.ccheckdate = ccheckdate;
        this.ccheckdepartment = ccheckdepartment;
        this.uid = uid;
        this.puid = puid;
        this.cprintdate = cprintdate;
        this.cmoney = cmoney;
        this.tid = tid;
        this.cstate = cstate;
    }

    public Certificate() {
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCnumber() {
        return cnumber;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber;
    }

    public String getCcompany() {
        return ccompany;
    }

    public void setCcompany(String ccompany) {
        this.ccompany = ccompany;
    }

    public String getCtoolname() {
        return ctoolname;
    }

    public void setCtoolname(String ctoolname) {
        this.ctoolname = ctoolname;
    }

    public String getCmodel() {
        return cmodel;
    }

    public void setCmodel(String cmodel) {
        this.cmodel = cmodel;
    }

    public String getCoutnumber() {
        return coutnumber;
    }

    public void setCoutnumber(String coutnumber) {
        this.coutnumber = coutnumber;
    }

    public String getCmanufacturer() {
        return cmanufacturer;
    }

    public void setCmanufacturer(String cmanufacturer) {
        this.cmanufacturer = cmanufacturer;
    }

    public String getCdelegate() {
        return cdelegate;
    }

    public void setCdelegate(String cdelegate) {
        this.cdelegate = cdelegate;
    }

    public Date getCcheckdate() {
        return ccheckdate;
    }

    public void setCcheckdate(Date ccheckdate) {
        this.ccheckdate = ccheckdate;
    }

    public String getCcheckdepartment() {
        return ccheckdepartment;
    }

    public void setCcheckdepartment(String ccheckdepartment) {
        this.ccheckdepartment = ccheckdepartment;
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

    public Date getCprintdate() {
        return cprintdate;
    }

    public void setCprintdate(Date cprintdate) {
        this.cprintdate = cprintdate;
    }

    public Double getCmoney() {
        return cmoney;
    }

    public void setCmoney(Double cmoney) {
        this.cmoney = cmoney;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", cnumber='" + cnumber + '\'' +
                ", ccompany='" + ccompany + '\'' +
                ", ctoolname='" + ctoolname + '\'' +
                ", cmodel='" + cmodel + '\'' +
                ", coutnumber='" + coutnumber + '\'' +
                ", cmanufacturer='" + cmanufacturer + '\'' +
                ", cdelegate='" + cdelegate + '\'' +
                ", ccheckdate=" + ccheckdate +
                ", ccheckdepartment='" + ccheckdepartment + '\'' +
                ", uid=" + uid +
                ", puid=" + puid +
                ", cprintdate=" + cprintdate +
                ", cmoney=" + cmoney +
                ", tid=" + tid +
                '}';
    }
}
