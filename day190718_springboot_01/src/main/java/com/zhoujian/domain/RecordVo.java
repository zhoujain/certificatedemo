package com.zhoujian.domain;

import java.util.Date;

public class RecordVo{
    private Integer scid;
    private String scnumber;
    private String sccompany;
    private String sctoolname;
    private String scmodel;
    private String scoutnumber;
    private String scmanufacturer;
    private String scdelegate;
    private String sccheckdepartment;
    private String uname;
    private String scprintdate;
    private String action;

    public RecordVo(Integer scid,  String scnumber, String sccompany, String sctoolname, String scmodel, String scoutnumber, String scmanufacturer, String scdelegate, String sccheckdepartment, String uname, String scprintdate, String action) {
        this.scid = scid;
        this.scnumber = scnumber;
        this.sccompany = sccompany;
        this.sctoolname = sctoolname;
        this.scmodel = scmodel;
        this.scoutnumber = scoutnumber;
        this.scmanufacturer = scmanufacturer;
        this.scdelegate = scdelegate;
        this.sccheckdepartment = sccheckdepartment;
        this.uname = uname;
        this.scprintdate = scprintdate;
        this.action = action;
    }

    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
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

    public String getSccheckdepartment() {
        return sccheckdepartment;
    }

    public void setSccheckdepartment(String sccheckdepartment) {
        this.sccheckdepartment = sccheckdepartment;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getScprintdate() {
        return scprintdate;
    }

    public void setScprintdate(String scprintdate) {
        this.scprintdate = scprintdate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}