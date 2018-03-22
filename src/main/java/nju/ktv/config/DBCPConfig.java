package nju.ktv.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBCPConfig {

	@Bean
	@ConfigurationProperties(prefix="spring.datasource.dbcp2")
	public DataSource basicDataSource() {
		return new BasicDataSource();
	}
}
