package com.zhoujian.domain;

import java.io.Serializable;
import java.util.Date;

public class CertificateVo implements Serializable {
    private Integer cid;
    private String cnumber;
    private String ccompany;
    private String ctoolname;
    private String cmodel;
    private String coutnumber;
    private String cmanufacturer;
    private String cdelegate;
    private Date ccheckdate;
    private String ccheckdepartment;
    private String uname;
    private String puname;
    private Date cprintdate;
    private Double cmoney;
    private String actions;

    public CertificateVo(Integer cid, String cnumber, String ccompany, String ctoolname, String cmodel, String coutnumber, String cmanufacturer, String cdelegate, Date ccheckdate, String ccheckdepartment, String uname, String puname, Date cprintdate, Double cmoney, String actions) {
        this.cid = cid;
        this.cnumber = cnumber;
        this.ccompany = ccompany;
        this.ctoolname = ctoolname;
        this.cmodel = cmodel;
        this.coutnumber = coutnumber;
        this.cmanufacturer = cmanufacturer;
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

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
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

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }
}
