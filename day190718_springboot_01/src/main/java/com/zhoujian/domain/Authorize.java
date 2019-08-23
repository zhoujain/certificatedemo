package com.zhoujian.domain;

import java.io.Serializable;
//委托表
public class Authorize implements Serializable {
    private Integer id;
    private String toolname;//器具名称
    private String model; //器具规格
    private String outnumber; //出厂编号
    private String toolId;//设备编号
    private String manufacturer;//制造厂商
    private Integer number;//套数
    private String cnumber;//登记号 插入时自动生成无重复 时间戳
    private Company company;//单位表关联
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToolname() {
        return toolname;
    }

    public void setToolname(String toolname) {
        this.toolname = toolname;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOutnumber() {
        return outnumber;
    }

    public void setOutnumber(String outnumber) {
        this.outnumber = outnumber;
    }

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCnumber() {
        return cnumber;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
