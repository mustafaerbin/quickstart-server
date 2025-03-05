package com.tr.innova.common.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Component
public class Log4jDataSourceConnectionFactory {

    private static final BasicDataSource dataSource = new BasicDataSource();

    private final Environment environment;

    public Log4jDataSourceConnectionFactory(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
