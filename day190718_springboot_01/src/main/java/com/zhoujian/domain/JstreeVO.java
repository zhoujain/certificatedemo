package com.zhoujian.domain;

import java.io.Serializable;

public class JstreeVO implements Serializable {
    //自己的id
    public String id;
    //自己的名字
    public String text;
    //父亲的id
    public String parent;
    //模板文件的url
    public String turl;
    //节点类型
    public String ttype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getTurl() {
        return turl;
    }

    public void setTurl(String turl) {
        this.turl = turl;
    }

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }

    @Override
    public String toString() {
        return "JstreeVO{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", parent='" + parent + '\'' +
                ", turl='" + turl + '\'' +
                ", ttype='" + ttype + '\'' +
                '}';
    }
}
