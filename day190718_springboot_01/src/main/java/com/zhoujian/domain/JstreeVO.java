package com.zhoujian.domain;

import java.io.Serializable;

public class JstreeVO implements Serializable {
    //自己的id
    public String id;
    //自己的名字
    public String text;
    //父亲的id
    public String parent;
    //随便定义的存放数据对象
    public User data;

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

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    @Override
    public String  toString() {
        return "JstreeVO{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", parent='" + parent + '\'' +
                ", data=" + data +
                '}';
    }
}
