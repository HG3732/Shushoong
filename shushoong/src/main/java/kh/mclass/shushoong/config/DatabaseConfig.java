package kh.mclass.shushoong.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.activation.DataSource;

@Configuration
@PropertySource("classpath:/keyfiles/apikey.properties")
public class DatabaseConfig {
	
	@Value("${spring.datasource.hikari.jdbc-log4j2-driver}")
	private String driverClassName;
	
	@Value("${spring.datasource.hikari.jdbc-log4j2-url}")
	private String dataSourceUrl;
	
	@Value("${spring.datasource.hikari.username}")
	private String dataSourceUserName;
	
	@Value("${spring.datasource.hikari.password}")
	private String dataSourcePassword;
	
	@Bean 
	HikariDataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driverClassName);
		hikariConfig.setJdbcUrl(dataSourceUrl);
		hikariConfig.setUsername(dataSourceUserName);
		hikariConfig.setPassword(dataSourcePassword);

		return new HikariDataSource(hikariConfig);
	}
	
}
