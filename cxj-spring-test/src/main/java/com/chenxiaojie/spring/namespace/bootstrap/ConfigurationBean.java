package com.chenxiaojie.spring.namespace.bootstrap;

import com.chenxiaojie.spring.namespace.annotation.EnableXiaojie;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableXiaojie(id = "xiaojieId", name = "xiaojieName", maxTimes = 1000)
@ComponentScan(value = "com.chenxiaojie.spring.namespace")
@Configuration
public class ConfigurationBean {

}
