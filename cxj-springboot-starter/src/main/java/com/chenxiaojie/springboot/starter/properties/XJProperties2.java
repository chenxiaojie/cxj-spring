package com.chenxiaojie.springboot.starter.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

//@Repository
public class XJProperties2 {

    @Value("${xiaojie.url}")
    private String url = "";

    @Value("${xiaojie.master}")
    private boolean master = true;

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
}
