package com.zhoujian.domain;

import com.zhoujian.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

public class Company implements Serializable {
    private Integer id;
    private String name;
    private String linkMan;
    private String linkPhone;
    private Date adate; //委托日期
    private String adateStr;//委托日期字符串
    private Date pdate;//打印日期
    private String pdateStr;//打印日期字符串
    private String adress;
    private String aid;//委托号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public Date getAdate() {
        return adate;
    }

    public void setAdate(Date adate) {

        this.adate = adate;
    }

    public String getAdateStr() {
        if(adate !=null){
            adateStr = DateUtil.date2String(adate,"yyyy-MM-dd");
        }
        return adateStr;
    }

    public void setAdateStr(String adateStr) {
        this.adateStr = adateStr;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public String getPdateStr() {
        if(pdate !=null){
            pdateStr = DateUtil.date2String(pdate,"yyyy-MM-dd");
        }
        return pdateStr;
    }

    public void setPdateStr(String pdateStr) {
        this.pdateStr = pdateStr;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", linkMan='" + linkMan + '\'' +
                ", linkPhone='" + linkPhone + '\'' +
                ", adate=" + adate +
                ", adateStr='" + adateStr + '\'' +
                ", pdate=" + pdate +
                ", pdateStr='" + pdateStr + '\'' +
                ", adress='" + adress + '\'' +
                ", aid=" + aid +
                '}';
    }
}