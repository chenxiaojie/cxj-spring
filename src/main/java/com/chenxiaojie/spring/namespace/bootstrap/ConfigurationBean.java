package com.chenxiaojie.spring.namespace.bootstrap;

import com.chenxiaojie.spring.namespace.annotation.XiaojieConfiguration;
import org.springframework.context.annotation.ComponentScan;

@XiaojieConfiguration(id = "xiaojieId", name = "xiaojieName", maxTimes = 1000)
@ComponentScan(value = "com.chenxiaojie.spring.namespace")
public class ConfigurationBean {

}
