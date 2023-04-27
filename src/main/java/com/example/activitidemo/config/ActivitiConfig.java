package com.example.activitidemo.config;

import javax.sql.DataSource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class ActivitiConfig {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public ProcessEngineConfiguration processEngineConfiguration() {
        StandaloneProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        configuration.setDataSource(dataSource());
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setAsyncExecutorActivate(false);
//        configuration.setMailServerHost("mail.my-corp.com");
//        configuration.setMailServerPort("5025");
        return configuration;
    }

    @Bean
    public ProcessEngine processEngine() {
        return processEngineConfiguration().buildProcessEngine();
    }

}