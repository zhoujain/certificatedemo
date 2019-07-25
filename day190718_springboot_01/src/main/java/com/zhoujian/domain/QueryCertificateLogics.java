package com.zhoujian.domain;

public class QueryCertificateLogics {
    private String lg;
    private String ret;
    private String com;
    private String term;

    public QueryCertificateLogics(String lg, String ret, String com, String term) {
        this.lg = lg;
        this.ret = ret;
        this.com = com;
        this.term = term;
    }

    public QueryCertificateLogics() {
    }

    @Override
    public String toString() {
        return "QueryCertificateLogics{" +
                "lg='" + lg + '\'' +
                ", ret='" + ret + '\'' +
                ", com='" + com + '\'' +
                ", term='" + term + '\'' +
                '}';
    }

    public String getLg() {
        return lg;
    }

    public void setLg(String lg) {
        this.lg = lg;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
