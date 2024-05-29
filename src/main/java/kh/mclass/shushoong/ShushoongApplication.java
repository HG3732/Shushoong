package kh.mclass.shushoong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/keyproperties/apikey.properties")
public class ShushoongApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShushoongApplication.class, args);
	}

}
