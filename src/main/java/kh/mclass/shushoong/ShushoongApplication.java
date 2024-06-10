package kh.mclass.shushoong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
//@EnableAspectJAutoProxy
@EnableAsync
//@PropertySource("classpath:/keyfiles/apikey/properties")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ShushoongApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShushoongApplication.class, args);
	}

}
