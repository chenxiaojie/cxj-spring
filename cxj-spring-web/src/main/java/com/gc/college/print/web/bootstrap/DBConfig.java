package com.gc.college.print.web.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by chenxiaojie on 18/8/06.
 */
@Slf4j
@Configuration
public class DBConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/GC_Print?useUnicode=true&amp;characterEncoding=UTF-8&amp;useAffectedRows=true");
        dataSource.setUsername("root");
        dataSource.setPassword("qwe888888");
        return dataSource;
    }

    @Bean
    public Boolean dataSourceIsSame() {
        DataSource dataSource1 = this.dataSource();
        DataSource dataSource2 = this.dataSource();
        log.info("dataSource1 == dataSource2 : " + (dataSource1 == dataSource2));
        return dataSource1 == dataSource2;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.dataSource());
        return sqlSessionFactoryBean;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(this.dataSource());
        return dataSourceTransactionManager;
    }

}
