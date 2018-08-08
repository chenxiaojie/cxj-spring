package com.chenxiaojie.spring.namespace.hander;

import com.chenxiaojie.spring.namespace.parser.XiaojieBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class XiaojieNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("annotation-driven", new XiaojieBeanDefinitionParser());
    }
}