//package kh.mclass.shushoong.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//
//@Configuration
//@PropertySource("classpath:/keyfiles/apikey.properties")
//public class AppConfig {
//	
//	//PortOne
//		@Value("${portone.store.key}")
//		private String portoneStoreKey;
//	
//		@Value("${portone.channel.key}")
//		private String portoneChannelKey;
//		
//		@Value("${portone.secret.key}")
//		private String portoneSecretKey;
//	
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/images/**")
//				.addResourceLocations("classpath:/static/images/");
//		 registry.addResourceHandler("/css/**")
//		 		 .addResourceLocations("classpath:/static/css/");
//		 registry.addResourceHandler("/js/**")
//		 		  .addResourceLocations("classpath:/static/js/");
//		 registry.addResourceHandler("/html/**")
//         		  .addResourceLocations("classpath:/static/html/");
//	}
//	
//}

