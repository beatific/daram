package org.beatific.daram.web.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource dataSource() {
    	BasicDataSource datasource = new BasicDataSource();
    	datasource.setDriverClassName("oracle.jdbc.OracleDriver");
    	datasource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
    	datasource.setUsername("daram");
    	datasource.setPassword("daram");
    	datasource.setInitialSize(2);
    	datasource.setMaxActive(5);
    	datasource.setMaxIdle(2);
    	datasource.setMaxWait(60000);
    	datasource.setDefaultAutoCommit(true);
    	datasource.setRemoveAbandoned(true);
    	datasource.setTestWhileIdle(true);
    	datasource.setTimeBetweenEvictionRunsMillis(60000);
    	datasource.setMinEvictableIdleTimeMillis(60000);
    	datasource.setValidationQuery("SELECT 1 FROM DUAL");
    	datasource.setRemoveAbandonedTimeout(60);
    	datasource.setLogAbandoned(true);
    	return datasource;
    }
}
