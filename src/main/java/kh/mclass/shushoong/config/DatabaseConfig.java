package kh.mclass.shushoong.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.siot.IamportRestClient.IamportClient;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.activation.DataSource;

@Configuration
@PropertySource("classpath:/keyfiles/apikey.properties")
public class DatabaseConfig {

//	@Value("${spring.datasource.driver-class-name}")
//	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String dataSourceUrl;

	@Value("${spring.datasource.username}")
	private String dataSourceUserName;

	@Value("${spring.datasource.password}")
	private String dataSourcePassword;

	@Bean
	HikariDataSource getDataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(dataSourceUrl);
		hikariConfig.setUsername(dataSourceUserName);
		hikariConfig.setPassword(dataSourcePassword);

		return new HikariDataSource(hikariConfig);
	}
//	
//	#-2-1-1 maximum pool size (pool에 유지시킬 수 있는 최대 커넥션 수 default:10)
//	#spring.datasource.hikari.maximum-pool-size=50
//	#-2-1-2 연결되었는지 확인을 위한 초기 쿼리
//	#spring.datasource.hikari.connection-init-sql=SELECT 1 FROM DUAL
//	#-2-1-3 pool에서 일을 안하는 커넥션을 유지하는 시간 (최솟값 : 10000ms / default : 600000ms(10minutes))
//	#spring.datasource.hikari.idleTimeout=10000
//	#-2-1-4 pool에서 커넥션을 얻어오기 전까지 기다리는 최대시간 (최솟값 : 250ms / default : 30000ms(30s))
//	#spring.datasource.hikari.connection-timeout=10000
//	#-2-1-5 valid 쿼리를 통해 커넥션이 유효한지 검사할 때 사용되는 timeout (최솟값 : 250ms / default : 5000ms)
//	#spring.datasource.hikari.validation-timeout=10000
//	#-2-1-6 커넥션 풀에서 살아있을 수 있는 커넥션의 최대 수명 시간 (default : 1800000ms(30minutes))
//	#spring.datasource.hikari.maxLifetime=580000
}
