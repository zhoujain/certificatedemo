package com.zhoujian.domain;

import java.io.Serializable;
import java.util.Date;

public class CertificateVo implements Serializable {
    private String cid;
    private String cnumber;
    private String ccompany;
    private String ctoolname;
    private String cmodel;
    private String coutnumber;
    private String cmanfacturer;
    private String cdelegate;
    private String ccheckdate;
    private String ccheckdepartment;
    private String uname;
    private String puname;
    private String cprintdate;
    private String cmoney;
    private String actions;

    public CertificateVo(String cid, String cnumber, String ccompany, String ctoolname, String cmodel, String coutnumber, String cmanfacturer, String cdelegate, String ccheckdate, String ccheckdepartment, String uname, String puname, String cprintdate, String cmoney, String actions) {
        this.cid = cid;
        this.cnumber = cnumber;
        this.ccompany = ccompany;
        this.ctoolname = ctoolname;
        this.cmodel = cmodel;
        this.coutnumber = coutnumber;
        this.cmanfacturer = cmanfacturer;
        this.cdelegate = cdelegate;
        this.ccheckdate = ccheckdate;
        this.ccheckdepartment = ccheckdepartment;
        this.uname = uname;
        this.puname = puname;
        this.cprintdate = cprintdate;
        this.cmoney = cmoney;
        this.actions = actions;
    }

    public CertificateVo() {
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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

    public String getCmanfacturer() {
        return cmanfacturer;
    }

    public void setCmanfacturer(String cmanfacturer) {
        this.cmanfacturer = cmanfacturer;
    }

    public String getCdelegate() {
        return cdelegate;
    }

    public void setCdelegate(String cdelegate) {
        this.cdelegate = cdelegate;
    }

    public String getCcheckdate() {
        return ccheckdate;
    }

    public void setCcheckdate(String ccheckdate) {
        this.ccheckdate = ccheckdate;
    }

    public String getCcheckdepartment() {
        return ccheckdepartment;
    }

    public void setCcheckdepartment(String ccheckdepartment) {
        this.ccheckdepartment = ccheckdepartment;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPuname() {
        return puname;
    }

    public void setPuname(String puname) {
        this.puname = puname;
    }

    public String getCprintdate() {
        return cprintdate;
    }

    public void setCprintdate(String cprintdate) {
        this.cprintdate = cprintdate;
    }

    public String getCmoney() {
        return cmoney;
    }

    public void setCmoney(String cmoney) {
        this.cmoney = cmoney;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "CertificateVo{" +
                "cid=" + cid +
                ", cnumber='" + cnumber + '\'' +
                ", ccompany='" + ccompany + '\'' +
                ", ctoolname='" + ctoolname + '\'' +
                ", cmodel='" + cmodel + '\'' +
                ", coutnumber='" + coutnumber + '\'' +
                ", cmanfacturer='" + cmanfacturer + '\'' +
                ", cdelegate='" + cdelegate + '\'' +
                ", ccheckdate='" + ccheckdate + '\'' +
                ", ccheckdepartment='" + ccheckdepartment + '\'' +
                ", uname='" + uname + '\'' +
                ", puname='" + puname + '\'' +
                ", cprintdate='" + cprintdate + '\'' +
                ", cmoney=" + cmoney +
                ", actions='" + actions + '\'' +
                '}';
    }
}
