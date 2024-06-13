package kh.mclass.shushoong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@EnableAspectJAutoProxy
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ShushoongApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShushoongApplication.class, args);
	}

}
