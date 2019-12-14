//package com.chenxiaojie.college.print.dao.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
///**
// * Desc: Hello World
// * ------------------------------------
// * Author: chenxiaojie
// * Date: 2019-12-11
// * Time: 22:02
// */
//@Configuration
//public class DataSourceAutoConfiguration {
//
//    /**
//     * driver-class-name: com.mysql.jdbc.Driver
//     * url: jdbc:mysql://localhost:3306/GC_Print?useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true&useSSL=false
//     * username: root
//     * password: qwe888888
//     *
//     * @return
//     */
//    @Bean
//    @Primary
//    public DataSource getDataSource() {
//        HikariDataSource hikariDataSource = new HikariDataSource();
//        hikariDataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/GC_Print?useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true&useSSL=false");
//        hikariDataSource.setUsername("root");
//        hikariDataSource.setPassword("qwe888888");
//        return hikariDataSource;
//    }
//}
