package kh.mclass.shushoong.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
	
	@Value("${cloudinary.name}")
	private String cloudName;

	@Value("${cloudinary.api.key}")
	private String cloudKey;
	
	@Value("${cloudinary.api.secret}")
	private String cloudSecret;
	
	@Bean
	public Cloudinary cloudinary() {
		return new Cloudinary(ObjectUtils.asMap(
				"cloud_name", cloudName,
				"api_key", cloudKey,
				"api_secret", cloudSecret,
				"secure", true));
	}
}
