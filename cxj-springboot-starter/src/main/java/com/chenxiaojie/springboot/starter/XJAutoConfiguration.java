package com.chenxiaojie.springboot.starter;

import com.chenxiaojie.springboot.starter.properties.XJProperties;
import com.chenxiaojie.springboot.starter.sqlsession.XJSqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(XJProperties.class)
@ConditionalOnClass(XJSqlSession.class)
@ConditionalOnProperty
        (
                prefix = "xiaojie",
                name = "url",
                matchIfMissing = true
        )
public class XJAutoConfiguration {

    @Autowired
    private XJProperties xjProperties;

    @Bean
    @ConditionalOnMissingBean(XJSqlSession.class)
    public XJSqlSession xjSqlSession() {
        XJSqlSession xjSqlSession = new XJSqlSession();
        xjSqlSession.setMaster(xjProperties.isMaster());
        xjSqlSession.setUrl(xjProperties.getUrl());
        return xjSqlSession;
    }

}
