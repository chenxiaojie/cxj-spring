package com.chenxiaojie.springboot.starter.sqlsession;

public class XJSqlSession {

    private String url;

    private boolean master;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    public void execute(String sql) {
        if (this.master) {
            System.out.println("在主库 : " + this.url + "上执行sql : " + sql);
        } else {
            System.out.println("在从库 : " + this.url + "上执行sql : " + sql);
        }
    }
}
