package com.chenxiaojie.spring.property.placeholder;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2019-05-31
 * Time: 17:24
 */
public class A {
    private String jdbcRef;

    public String getJdbcRef() {
        return jdbcRef;
    }

    public void setJdbcRef(String jdbcRef) {
        this.jdbcRef = jdbcRef;
    }

    @Override
    public String toString() {
        return "A{" +
                "jdbcRef='" + jdbcRef + '\'' +
                '}';
    }
}
